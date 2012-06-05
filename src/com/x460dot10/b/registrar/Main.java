package com.x460dot10.b.registrar;

public class Main implements Observer
{
     University uni;
     private SystemStatus systemStatus;
//     StartupManager startupMngr;
//     LoginManager loginMngr;
//     PasswordManager pwdMngr;
//     SessionManager sessionMngr;
//     RegistrationManager regMngr;
     
     public Main()
     {
          uni = University.getInstance();

          uni.initStartup();
          while (systemStatus != SystemStatus.QUIT)
          {
               switch (systemStatus)
               {
               case READY_FOR_LOGIN:
                    uni.initLoginScreen();
                    break;
               case LOGIN_REQUEST:
                    break;
               case NEW_ACCOUNT_REQUEST:
                    try
                    {
                         uni.initNewStudent("");
                    }
                    catch (IllegalArgumentException ex)
                    {
                         uni.initNewStudent(ex.getMessage());
                    }
                    break;
                    
                    
                    
                    
               default:
                    break;
               }
          }
          
     }
     
     /**
      * Implements Observer pattern to stay synchronized with 
      * <code>SystemStatus</code> of the <code>University</code>.
      * 
      * @author Alexandros Bantis
      * @param status             possible values are:
      *                           STARTING_UP = can import records from
      *                                         <code>StartupManager</code>
      *                           RUNNING = program available for users.
      *                                     to log in.
      *                           SHUTTING_DOWN = system preparing to
      *                                           shut down by saving data.
      */
     @Override
     public void update(SystemStatus status)
     {
          systemStatus = status;
     }
}
