/**
 * DeadlockExample.java
 *
 * This program gives an example of how deadlock can occur between threads.
 * This program differs from the book as each thread will sleep a random
 * amount of time between calls to the synchronized statement. It may require
 * several runs of the program to deadlock the threads.
 *
 * @author Greg Gagne, Peter Galvin, Avi Silberschatz
 * @version 1.0 - July 15,  1999.
 * Copyright 2000 by Greg Gagne, Peter Galvin, Avi Silberschatz
 * Applied Operating Systems Concepts - John Wiley and Sons, Inc.
 */
 
class Mutex
{

}

class A extends Thread
{
   private Mutex first, second;
   
   public A(Mutex f, Mutex s) {
      first = f;
      second = s;
   }
   
   public void run() {
      synchronized (first) {
         // do something

	try { 
		Thread.sleep( ((int)(3*Math.random()))*1000);
	}
	catch (InterruptedException e) {}

         System.out.println("threadA got first mutex");
         synchronized (second) {
            // do something
            System.out.println("threadA got second mutex");
            
         }
      }
   }
}

class B extends Thread
{
   private Mutex first, second;
   
   public B(Mutex f, Mutex s) {
      first = f;
      second = s;
   }
   
   public void run() {
      synchronized (second) {
         // do something

	try { 
                Thread.sleep( ((int)(3*Math.random()))*1000);
        }
        catch (InterruptedException e) {}

         System.out.println("threadB got second mutex");
         synchronized (first) {
            // do something
            System.out.println("threadB got first mutex");
         }
      }
   }
}


public class DeadlockExample 
{  
   public static void main(String arg[]) {         
      Mutex mutexX = new Mutex();
      Mutex mutexY = new Mutex();
      
      A threadA = new A(mutexX,mutexY);
      B threadB = new B(mutexX,mutexY);
      
      threadA.start();
      threadB.start();
   }
}

