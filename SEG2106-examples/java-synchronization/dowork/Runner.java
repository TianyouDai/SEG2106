/**
 * Runner.java
 *
 * This class contains a static method that simulates a thread sleeping.
 *
 * @author Greg Gagne, Peter Galvin, Avi Silberschatz
 * @version 1.0 - July 15, 1999
 * Copyright 2000 by Greg Gagne, Peter Galvin, Avi Silberschatz
 * Applied Operating Systems Concepts - John Wiley and Sons, Inc.
 */

public class Runner
{
   public static void slacking() {
      try {
            Thread.sleep( (int) (Math.random() * 3000) );
      }  
      catch (InterruptedException e) { }
   }
}
