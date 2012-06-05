package com.x460dot10.b.registrar;

/**
 * indicates the current state of the program
 * 
 * @author Alexandros Bantis
 * @param  STARTING_UP       indicates that <code>Initializer</code
 *                           is starting up the system
 * @param  RUNNING           indicates system is currently running
 * @param  SHUTTING_DOWN     indicates system is shutting down
 * @param  TESTING           indicates system is in testing mode
 */
public enum SystemStatus
{
     STARTING_UP, RUNNING, SHUTTING_DOWN, TESTING;
}
