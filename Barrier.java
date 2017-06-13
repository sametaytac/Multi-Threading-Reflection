
import java.util.logging.Level;
import java.util.logging.Logger;

//***************************************************************************************************************************************************

public class Barrier
{
  //=================================================================================================================================================

  private int numberOfThreadsCurrentlyWaiting    ;
  private int numberOfThreadsToReachBarrierPoint ;

  //=================================================================================================================================================

  public Barrier ( int numberOfThreadsToReachBarrierPoint )
  {
    this.numberOfThreadsToReachBarrierPoint=numberOfThreadsToReachBarrierPoint;
    numberOfThreadsCurrentlyWaiting=0;
  }

  //=================================================================================================================================================

  public void await ()
  {
    numberOfThreadsCurrentlyWaiting++;
    if(numberOfThreadsCurrentlyWaiting==numberOfThreadsToReachBarrierPoint)
    {
    synchronized(this){
    this.notifyAll();}
    
    }
    while(numberOfThreadsCurrentlyWaiting<numberOfThreadsToReachBarrierPoint)
    {
        try {
               synchronized(this){
    this.wait();}
        } catch (InterruptedException ex) {
            Logger.getLogger(Barrier.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    
    
  }

  //=================================================================================================================================================
}

//***************************************************************************************************************************************************

