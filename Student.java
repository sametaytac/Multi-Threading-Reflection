
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//***************************************************************************************************************************************************

public class Student extends Entity implements Runnable
{
  //=================================================================================================================================================

  private final int          level      ;
  private final List<Course> courses    ;
  private final Thread       thread     ;
  private       Instructor   advisor    ;
  private       Department   department ;

  //=================================================================================================================================================

  public Student ( String name )
  {
    super( name ) ;

    this.level      = Department.generateRandomLevel() ;
    this.courses    = new ArrayList<>()                ;
    this.thread     = new Thread( this , name )        ;
    this.advisor    = null                             ;
    this.department = null                             ;
  }

  //=================================================================================================================================================

  public void setAdvisor ( Instructor instructor )
  {
    this.advisor = instructor ;
  }

  //=================================================================================================================================================

  private boolean addCourse ( Course course )
  {
    if ( courses.contains( course ) )  { return false ; }

    courses.add( course ) ;

    return true ;
  }

  //=================================================================================================================================================

  private void clearCourses ()
  {
    courses.clear() ;
  }

  //=================================================================================================================================================

  @Override
  public String toString ()
  {
    String advisorString = (advisor == null) ? "-" : advisor.toString() ;

    return ( getName() + " (" + Department.getLevelString( level ) + ", " + advisorString + ", " + courses.size() + ")" ) ;
  }

  //=================================================================================================================================================

  public void start ( Department department )
  {
    this.department = department ;

    thread.start() ;
  }

  //=================================================================================================================================================

  @Override
  public void run ()
  {
    //-----------------------------------------------------------------------------------------------------------------------------------------------

    boolean registrationDone = false ;
    Computer c = null;
    Department.log( getName() , "I am in the laboratory looking for a free computer" ) ;
          
    while ( ! registrationDone )
    {
      boolean isfindcomp=false;
      synchronized(department.computers){
      for (Computer comp :department.computers)
      {
          if(!comp.isInUse())
          {
              comp.setInUse(true);
              isfindcomp=true;
              c=comp;
              Department.log( getName() , "I got computer " + c.getName() ) ;

              break;
          }
      }}
      if(!isfindcomp)
      {
          
          boolean isempty=false;
          
          Department.log( getName() , "No available computers, I'm waiting" ) ;
          while(!isempty){
              synchronized(department.computers){
             try {
            department.computers.wait();
                } catch (InterruptedException e) { }
             for(Computer cc:department.computers)
             {
                 if(!cc.isInUse())
                     isempty=true;
                     break;
             }
          }
      Department.log( getName() , "Someone just left the laboratory, I'll now check again for a free computer" ) ;
      }}
      if(isfindcomp)
      {
      Department.log( getName() , "I am registering for 3 courses" ) ;
      while(true)
      {
      if(this.addCourse(department.getRandomCourse()))
          break;
      }  
      Department.log( getName() , "I took "+courses.get(0).toString()  ) ;
 while(true)
      {
      if(this.addCourse(department.getRandomCourse()))
          break;
      } 
      
                  Department.log( getName() , "I took "+ courses.get(1).toString()   ) ;

     while(true)
      {
      if(this.addCourse(department.getRandomCourse()))
          break;
      } 
                  Department.log( getName() , "I took "+courses.get(2).toString()   ) ;

      
      
       synchronized(department.computers){
      Department.log( getName() ,"I am leaving computer "+c.getName());
      c.setInUse(false);
      department.computers.notify();
      
       }
              Department.log( getName() , "I am about to get my registration checked by "+ advisor.getName()  ) ;
              if(advisor.isRegistrationOK(this))
              {
                  registrationDone=true;
              Department.log( getName() , "My registration is approved"  ) ;

              }
              else{
              Department.log( getName() ,"I need to register again");
              clearCourses();
              Department.log( getName() , "I am in the laboratory looking for a free computer" ) ;
              }
        
      }
  
      
      
      
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------

    Department.log( getName() , "I'm joining the after-registration party" ) ;

    department.barrierParty.await() ;

    Department.log( getName() , Department.getRandomPartyTalk() ) ;

    //-----------------------------------------------------------------------------------------------------------------------------------------------
  }

  //=================================================================================================================================================
}

//***************************************************************************************************************************************************

