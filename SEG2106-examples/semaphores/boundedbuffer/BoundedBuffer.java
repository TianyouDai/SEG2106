/**
 * BoundedBuffer.java
 *
 * This program implements the bounded buffer with semaphores.
 * Note that the use of count only serves to output whether 
 * the buffer is empty of full.
 *
 * @author Greg Gagne, Peter Galvin, Avi Silberschatz
 * @version 1.0 - July 15, 1999
 * Copyright 2000 by Greg Gagne, Peter Galvin, Avi Silberschatz
 * Applied Operating Systems Concepts - John Wiley and Sons, Inc.
 */

 
import java.util.*;

public class BoundedBuffer  
{     
   public BoundedBuffer()
   {
      // buffer is initially empty
      count = 0;
      in = 0;
      out = 0;
      
      buffer = new Object[BUFFER_SIZE];
      
      mutex = new Semaphore(1);
      empty = new Semaphore(BUFFER_SIZE);
      full = new Semaphore(0);
   }

  // producer and consumer will call this to nap
   public static void napping() {
     int sleepTime = (int) (NAP_TIME * Math.random() );
     try { Thread.sleep(sleepTime*1000); }
     catch(InterruptedException e) { }
   }


   // producer calls this method
   public void enter(Object item) {
      empty.P();
      mutex.P();
      
      // add an item to the buffer
      ++count;
      buffer[in] = item;
      in = (in + 1) % BUFFER_SIZE;

      if (count == BUFFER_SIZE)
                System.out.println("Producer Entered " + item + " Buffer FULL");
        else
                System.out.println("Producer Entered " + item + " Buffer Size = " +  count);
 
      mutex.V();
      full.V();
   }
   
   // consumer calls this method
   public Object remove() {
      Object item;
      
      full.P();
      mutex.P();
     
      // remove an item from the buffer
      --count;
      item = buffer[out];
      out = (out + 1) % BUFFER_SIZE;

      if (count == 0)
                System.out.println("Consumer Consumed " + item + " Buffer EMPTY");
        else
                System.out.println("Consumer Consumed " + item + " Buffer Size = " + count);

      mutex.V();
      empty.V();
      
      return item;
   }
   
   public static final int    NAP_TIME = 5;
//   private static final int   BUFFER_SIZE = 2;
   private static final int   BUFFER_SIZE = 5;
   
   private Semaphore mutex;
   private Semaphore empty;
   private Semaphore full;
   
   private int count;
   private int in, out;
   private Object[] buffer;
}
