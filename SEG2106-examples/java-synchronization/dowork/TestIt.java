/**
 * TestIt.java
 *
 * This program creates the threads that are used to demonstrate
 * the difference between notify() and notifyAll().
 *
 * @author Greg Gagne, Peter Galvin, Avi Silberschatz
 * @version 1.0 - July 15, 1999
 * Copyright 2000 by Greg Gagne, Peter Galvin, Avi Silberschatz
 * Applied Operating Systems Concepts - John Wiley and Sons, Inc.
 */

public class TestIt 
{
   public static void main(String args[]) {
      DoWork pile = new DoWork();
      
      Worker[] bees = new Worker[5];
      
      for (int i = 1; i <= 5; i++)
         bees[i-1] = new Worker(pile, "Worker " + (new Integer(i)).toString(), i);
         
      for (int i = 1; i <= 5; i++)
         bees[i-1].start();
   }
}
   
