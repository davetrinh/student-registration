/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

//package org.apache.commons.csv;
package org.apache.commons.csv;

import java.io.IOException;


import static org.apache.commons.csv.Token.Type.*;

class CSVLexer extends Lexer {

    // ctor needs to be public so can be called dynamically by PerformanceTest class
    public CSVLexer(CSVFormat format, ExtendedBufferedReader in) {
        super(format, in);
    }
    
    /**
     * Returns the next token.
     * <p/>
     * A token corresponds to a term, a record change or an end-of-file indicator.
     *
     * @param tkn an existing Token object to reuse. The caller is responsible to initialize the Token.
     * @return the next token found
     * @throws java.io.IOException on stream access error
     */
    @Override
    Token nextToken(Token tkn) throws IOException {

        // get the last read char (required for empty line detection)
        int lastChar = in.readAgain();

        //  read the next char and set eol
        int c = in.read();
        /*
         * Note:
         * The following call will swallow LF if c == CR.
         * But we don't need to know if the last char
         * was CR or LF - they are equivalent here.
         */
        boolean eol = isEndOfLine(c);

        //  empty line detection: eol AND (last char was EOL or beginning)
        if (emptyLinesIgnored) {
            while (eol && isStartOfLine(lastChar)) {
                // go on char ahead ...
                lastChar = c;
                c = in.read();
                eol = isEndOfLine(c);
                // reached end of file without any content (empty line at the end)
                if (isEndOfFile(c)) {
                    tkn.type = EOF;
                    // don't set tkn.isReady here because no content
                    return tkn;
                }
            }
        }

        // did we reach eof during the last iteration already ? EOF
        if (isEndOfFile(lastChar) || (!isDelimiter(lastChar) && isEndOfFile(c))) {
            tkn.type = EOF;
            // don't set tkn.isReady here because no content
            return tkn;
        }

        if (isStartOfLine(lastChar) && isCommentStart(c)) {
            String comment = in.readLine().trim();
            tkn.content.append(comment);
            tkn.type = COMMENT;
            return tkn;
        }

        //  important: make sure a new char gets consumed in each iteration
        while (tkn.type == INVALID) {
            // ignore whitespaces at beginning of a token
            if (surroundingSpacesIgnored) {
                while (isWhitespace(c) && !eol) {
                    c = in.read();
                    eol = isEndOfLine(c);
                }
            }
            
            // ok, start of token reached: encapsulated, or token
            if (isDelimiter(c)) {
                // empty token return TOKEN("")
                tkn.type = TOKEN;
            } else if (eol) {
                // empty token return EORECORD("")
                //noop: tkn.content.append("");
                tkn.type = EORECORD;
            } else if (isEncapsulator(c)) {
                // consume encapsulated token
                encapsulatedTokenLexer(tkn);
            } else if (isEndOfFile(c)) {
                // end of file return EOF()
                //noop: tkn.content.append("");
                tkn.type = EOF;
                tkn.isReady = true; // there is data at EOF
            } else {
                // next token must be a simple token
                // add removed blanks when not ignoring whitespace chars...
                simpleTokenLexer(tkn, c);
            }
        }
        return tkn;
    }

    /**
     * A simple token lexer
     * <p/>
     * Simple token are tokens which are not surrounded by encapsulators.
     * A simple token might contain escaped delimiters (as \, or \;). The
     * token is finished when one of the following conditions become true:
     * <ul>
     *   <li>end of line has been reached (EORECORD)</li>
     *   <li>end of stream has been reached (EOF)</li>
     *   <li>an unescaped delimiter has been reached (TOKEN)</li>
     * </ul>
     *
     * @param tkn the current token
     * @param c   the current character
     * @return the filled token
     * @throws IOException on stream access error
     */
    private Token simpleTokenLexer(Token tkn, int c) throws IOException {
        // Faster to use while(true)+break than while(tkn.type == INVALID)
        while (true) {
            if (isEndOfLine(c)) {
                tkn.type = EORECORD;
                break;
            } else if (isEndOfFile(c)) {
                tkn.type = EOF;
                tkn.isReady = true; // There is data at EOF
                break;
            } else if (isDelimiter(c)) {
                tkn.type = TOKEN;
                break;
            } else if (isEscape(c)) {
                tkn.content.append((char) readEscape());
                c = in.read(); // continue
            } else {
                tkn.content.append((char) c);
                c = in.read(); // continue
            }
        }

        if (surroundingSpacesIgnored) {
            trimTrailingSpaces(tkn.content);
        }

        return tkn;
    }

    /**
     * An encapsulated token lexer
     * <p/>
     * Encapsulated tokens are surrounded by the given encapsulating-string.
     * The encapsulator itself might be included in the token using a
     * doubling syntax (as "", '') or using escaping (as in \", \').
     * Whitespaces before and after an encapsulated token are ignored.
     * The token is finished when one of the following conditions become true:
     * <ul>
     *   <li>an unescaped encapsulator has been reached, and is followed by optional whitespace then:</li>
     *   <ul>
     *       <li>delimiter (TOKEN)</li>
     *       <li>end of line (EORECORD)</li>
     *   </ul>
     *   <li>end of stream has been reached (EOF)</li>
     * </ul>
     *
     * @param tkn the current token
     * @return a valid token object
     * @throws IOException on invalid state: 
     *  EOF before closing encapsulator or invalid character before delimiter or EOL
     */
    private Token encapsulatedTokenLexer(Token tkn) throws IOException {
        // save current line number in case needed for IOE
        int startLineNumber = getLineNumber();
        int c;
        while (true) {
            c = in.read();
            
            if (isEscape(c)) {
                tkn.content.append((char) readEscape());
            } else if (isEncapsulator(c)) {
                if (isEncapsulator(in.lookAhead())) {
                    // double or escaped encapsulator -> add single encapsulator to token
                    c = in.read();
                    tkn.content.append((char) c);
                } else {
                    // token finish mark (encapsulator) reached: ignore whitespace till delimiter
                    while (true) {
                        c = in.read();
                        if (isDelimiter(c)) {
                            tkn.type = TOKEN;
                            return tkn;
                        } else if (isEndOfFile(c)) {
                            tkn.type = EOF;
                            tkn.isReady = true; // There is data at EOF
                            return tkn;
                        } else if (isEndOfLine(c)) {
                            tkn.type = EORECORD;
                            return tkn;
                        } else if (!isWhitespace(c)) {
                            // error invalid char between token and next delimiter
                            throw new IOException("(line " + getLineNumber() + ") invalid char between encapsulated token and delimiter");
                        }
                    }
                }
            } else if (isEndOfFile(c)) {
                // error condition (end of file before end of token)
                throw new IOException("(startline " + startLineNumber + ") EOF reached before encapsulated token finished");
            } else {
                // consume character
                tkn.content.append((char) c);
            }
        }
    }

}