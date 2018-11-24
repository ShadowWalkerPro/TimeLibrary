public class MultiThread implements Runnable {

  boolean DEBUGMODE = true;
  Thread timeThread;
  Thread printThread;
  Time time;

  public MultiThread() {
     timeThread = new Thread(this, "TimeThread");
     printThread = new Thread(this, "PrintThread");
     if(DEBUGMODE) {
       System.out.println("[System/Debug] Time and Print Thread created.");
     }
     timeThread.start();
     printThread.start();
  }

  public void run() {
    if(DEBUGMODE) {
      System.out.println("[System/Debug] Running phase reached!");
    }
    if(Thread.currentThread().getName().equals("TimeThread")) {
      if(DEBUGMODE) {
        System.out.println("[System/Debug] Time Thread Initialized");
      }
      time = new Time(false, 1);
    } else if(Thread.currentThread().getName().equals("PrintThread")) {
      if(DEBUGMODE) {
        System.out.println("[System/Debug] Print Thread Initialized");
      }
      while(true) {
        try {
          Thread.sleep(1000);
        } catch(InterruptedException ex) {
          System.out.println("[System/Error] Delay Malfunction");
        }
        System.out.println("Time in Minutes and Seconds: " + time.getTimeData()[1] + ":" + time.getTimeData()[0]);
      }
    }
  }

}
