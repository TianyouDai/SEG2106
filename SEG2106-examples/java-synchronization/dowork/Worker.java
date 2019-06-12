/**
 * Worker.java
 *
 * This thread is used to demonstrate the difference between 
 * notify() and notifyAll().
 *
 * @author Greg Gagne, Peter Galvin, Avi Silberschatz
 * @version 1.0 - July 15, 1999
 * Copyright 2000 by Greg Gagne, Peter Galvin, Avi Silberschatz
 * Applied Operating Systems Concepts - John Wiley and Sons, Inc.
 */

public class Worker extends Thread
{
   public Worker(DoWork p, String n, int i) {
	pile = p;
	name = n;
	num = i;
   }

   public void run() {
      while (true) {
         Runner.slacking();

         pile.DoWork(num);
      }
   }

  private DoWork pile;
  private String name;
  private int num;
}

