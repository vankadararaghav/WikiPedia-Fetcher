package utility;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TaskManager {
      ExecutorService executorService;
      private int threadCount;
      public TaskManager(int threadCount)
      {
           this.threadCount=threadCount;
          executorService = Executors.newFixedThreadPool(1);
      }
      public void waitTillQueueSizeIsFreeAndAddTask(Runnable runnable)
      {
          while(getQueueSize()>=threadCount)
          {
                try {
                      Thread.currentThread().sleep(1000) ;
                } catch (InterruptedException e) {
                      throw new RuntimeException(e);
                }
          }
          addTask(runnable);
      }
      public void addTask(Runnable runnable)
      {
            executorService.submit(runnable);
      }
      public int getQueueSize()
      {
            ThreadPoolExecutor executor =   (ThreadPoolExecutor) executorService;
            return executor.getQueue().size();
      }
}
