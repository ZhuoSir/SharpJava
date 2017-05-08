package thread.executor;

import java.util.concurrent.*;

/**
 *
 * Created by sunny on 2017/5/8.
 */
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                4,
                3L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 0; i < 10; i++) {
            String task = "task@" + i;
            System.out.println("创建任务并提交到线程池中：" + task);
            threadPoolExecutor.execute(new task(task));
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        threadPoolExecutor.shutdown();
    }

    static class task implements Runnable {

        private String name;

        public task(String t) {
            this.name = t;
        }

        @Override
        public void run() {
            System.out.println("运行任务：" + name);
        }
    }
}
