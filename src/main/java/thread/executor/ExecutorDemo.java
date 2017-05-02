package thread.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * Created by sunny-chen on 2017/5/3.
 */
public class ExecutorDemo {

    public static void main(String[] args) {
        Executor executor = Executors.newCachedThreadPool();
        executor.execute(new Task());
        ThreadPoolExecutor pool = (ThreadPoolExecutor) executor;
        pool.shutdown();
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            Long duration = (long) (Math.random() * 5);
            System.out.println("Running task !");
            try {
                TimeUnit.SECONDS.sleep(duration);
                System.out.println("Task Completed !");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
