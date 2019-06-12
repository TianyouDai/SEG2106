/**
 * DoWork.java
 *
 * This method is used to distinguish between the notify()
 * and notifyAll() methods. Run the program using notify(). 
 * The program will shortly hang as the incorrect thread
 * (i.e. the thread whose turn is not next!) receives the notification.
 * After that, change the call to notify() to notifyAll(). The
 * program should run correctly using notifyAll().
 *
 * @author Greg Gagne, Peter Galvin, Avi Silberschatz
 * @version 1.0 - July 15, 1999
 * Copyright 2000 by Greg Gagne, Peter Galvin, Avi Silberschatz
 * Applied Operating Systems Concepts - John Wiley and Sons, Inc.
 */


public class DoWork {
	// pnum is the number of the thread that wishes to do some work
	public synchronized void DoWork(int pnum) {
		while (turn != pnum) {
			try {
				wait();
			}
			catch (InterruptedException e) {}
		}

		// do some work for awhile
		System.out.println("Worker " + pnum + " will do some work");
		try {
            		Thread.sleep( (int) (Math.random() * 3000) );
      		}
      		catch (InterruptedException e) { }

		// ok, we're finished. Now indicate to the next waiting
		// thread that it is their turn to do some work.
		System.out.println("Worker " + pnum + " is done working");

		if (turn < 5)
			++turn;
		else
			turn = 1;

		// change this to notifyAll() to see it run correctly!
		notify();
	}

	private int turn = 1;
}
