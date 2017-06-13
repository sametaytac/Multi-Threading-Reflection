
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//***************************************************************************************************************************************************

public class Instructor extends Entity
{
  //=================================================================================================================================================

  public Instructor ( String name )
  {
    super( name ) ;
  }

  //=================================================================================================================================================

  boolean isRegistrationOK ( Student student )
  {
                     synchronized(this){

      try {
          Department.log( getName() , "I am checking registration of " + student.getName() ) ;
          
          Field field = Student.class.getDeclaredField("level");
          field.setAccessible(true);
          Field field2 = Course.class.getDeclaredField("level");
          field2.setAccessible(true);
          Field field3 = Student.class.getDeclaredField("courses");
          field3.setAccessible(true);
          Field field4 = Student.class.getDeclaredField("department");
          field4.setAccessible(true);
          Method method = Department.class.getDeclaredMethod("canTakeCourse",int.class,int.class);
          method.setAccessible(true);
          try {
              int slevel =(int) field.get(student);
              ArrayList<Course> courses;
              courses =  (ArrayList<Course>) field3.get(student);
              int course1=(int) field2.get(courses.get(0));
              int course2=(int) field2.get(courses.get(1));
              int course3=(int) field2.get(courses.get(2));
              Department d= (Department) field4.get(student);
              if(! (boolean)method.invoke(d,slevel,course1))
              {   Department.log( getName() ,courses.get(0).toString() + " -> NO" ) ;

                  return false;
              }
              else
                  Department.log( getName() ,courses.get(0).toString() + " -> YES" ) ;
              if(! (boolean)method.invoke(d,slevel,course2))
              {   Department.log( getName() ,courses.get(1).toString() + " -> NO" ) ;

                  return false;}
               else
                  Department.log( getName() ,courses.get(1).toString() + " -> YES" ) ;
              if(! (boolean)method.invoke(d,slevel,course3))
                  {   Department.log( getName() ,courses.get(2).toString() + " -> NO" ) ;

                  return false;
              }
              else
                  Department.log( getName() ,courses.get(2).toString() + " -> YES" ) ;
              
              return true;
     
              
       
              
          } catch (IllegalArgumentException ex) {
              Logger.getLogger(Instructor.class.getName()).log(Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
              Logger.getLogger(Instructor.class.getName()).log(Level.SEVERE, null, ex);
          } catch (InvocationTargetException ex) {
              Logger.getLogger(Instructor.class.getName()).log(Level.SEVERE, null, ex);
          }
      } catch (NoSuchFieldException ex) {
          Logger.getLogger(Instructor.class.getName()).log(Level.SEVERE, null, ex);
      } catch (SecurityException ex) {
          Logger.getLogger(Instructor.class.getName()).log(Level.SEVERE, null, ex);
      } catch (NoSuchMethodException ex) {
          Logger.getLogger(Instructor.class.getName()).log(Level.SEVERE, null, ex);
      }
      
                     }
      
            
      
      
      
      return false;
  }

  //=================================================================================================================================================
}

//***************************************************************************************************************************************************

