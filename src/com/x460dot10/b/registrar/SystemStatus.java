package com.x460dot10.b.registrar;

/**
 * indicates the current state of the program
 * 
 * @author Alexandros Bantis
 * @param  STARTING_UP       indicates that <code>StartupManager</code
 *                           is starting up the system
 * @param  RUNNING           indicates system is currently running
 * @param  SHUTTING_DOWN     indicates system is shutting down
 * @param  TESTING           indicates system is in testing mode
 */
public enum SystemStatus
{
     TESTING,
     SLEEPING,
     STARTING_UP,
     READY_FOR_LOGIN,
     NEW_ACCOUNT_REQUEST,
     LOGIN_REQUEST,
     RUNNING, 
     SHUTTING_DOWN,
     ERROR,
     QUIT;
}
