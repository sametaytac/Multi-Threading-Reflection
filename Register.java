//***************************************************************************************************************************************************

public class Register
{
  //=================================================================================================================================================

  public static void main ( String[] args )
  {
    //-----------------------------------------------------------------------------------------------------------------------------------------------

    // Usage: Register [NumberOfInstructors] [NumberOfStudents] [NumberOfCourses] [NumberOfComputers]

    //-----------------------------------------------------------------------------------------------------------------------------------------------

    int numberOfInstructors =  2 ;
    int numberOfStudents    =  3 ;
    int numberOfCourses     = 10 ;
    int numberOfComputers   =  2 ;

    if ( args.length >= 1 )  { numberOfInstructors = Integer.parseInt( args[0].trim() ) ; }
    if ( args.length >= 2 )  { numberOfStudents    = Integer.parseInt( args[1].trim() ) ; }
    if ( args.length >= 3 )  { numberOfCourses     = Integer.parseInt( args[2].trim() ) ; }
    if ( args.length >= 4 )  { numberOfComputers   = Integer.parseInt( args[3].trim() ) ; }

    Department ceng = new Department( numberOfInstructors , numberOfStudents , numberOfCourses , numberOfComputers ) ;

    //-----------------------------------------------------------------------------------------------------------------------------------------------

    System.out.println( "Number of Instructors = " + ceng.instructors.size() ) ;
    System.out.println( "Number of Students    = " + ceng.students   .size() ) ;
    System.out.println( "Number of Courses     = " + ceng.courses    .size() ) ;
    System.out.println( "Number of Computers   = " + ceng.computers  .size() ) ;

    System.out.println( "\n" + "Instructors" + " :" + "\n" ) ;  for ( Instructor i : ceng.instructors )  { System.out.println( "  " + i ) ; }
    System.out.println( "\n" + "Students"    + " :" + "\n" ) ;  for ( Student    i : ceng.students    )  { System.out.println( "  " + i ) ; }
    System.out.println( "\n" + "Courses"     + " :" + "\n" ) ;  for ( Course     i : ceng.courses     )  { System.out.println( "  " + i ) ; }
    System.out.println( "\n" + "Computers"   + " :" + "\n" ) ;  for ( Computer   i : ceng.computers   )  { System.out.println( "  " + i ) ; }

    //-----------------------------------------------------------------------------------------------------------------------------------------------

    System.out.println() ;

    for ( Student s : ceng.students )
    {
      s.start( ceng ) ;
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------
  }

  //=================================================================================================================================================
}

//***************************************************************************************************************************************************

