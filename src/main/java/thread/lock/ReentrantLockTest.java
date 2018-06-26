package thread.lock;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    @Test
    public void tryLockTest() {

        //场景1：如果发现该操作已经在执行中则不再执行（有状态执行）
        //a、用在定时任务时，如果任务执行时间可能超过下次计划执行时间，确保该有状态任务只有一个正在执行，忽略重复触发。
        //b、用在界面交互时点击执行较长时间请求操作时，防止多次点击导致后台重复执行（忽略重复触发）。
        //
        //以上两种情况多用于进行非重要任务防止重复执行，（如：清除无用临时文件，检查某些资源的可用性，数据备份操作等）

        final ReentrantLock lock = new ReentrantLock();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                trylockMethod(lock);
            }
        };

        new Thread(runnable).start();
        new Thread(runnable).start();
    }

    @Test
    public void testLockFair() {

        //场景2：如果发现该操作已经在执行，等待一个一个执行（同步执行，类似synchronized）
        //这种比较常见大家也都在用，主要是防止资源使用冲突，保证同一时间内只有一个操作可以使用该资源。
        //但与synchronized的明显区别是性能优势（伴随jvm的优化这个差距在减小）。同时Lock有更灵活的锁定方式，公平锁与不公平锁，而synchronized永远是公平的。

        //这种情况主要用于对资源的争抢（如：文件操作，同步消息发送，有状态的操作等）

        //ReentrantLock默认情况下为不公平锁

        //不公平锁与公平锁的区别：
        //公平情况下，操作会排一个队按顺序执行，来保证执行顺序。（会消耗更多的时间来排队）
        //不公平情况下，是无序状态允许插队，jvm会自动计算如何处理更快速来调度插队。（如果不关心顺序，这个速度会更快）

        CountDownLatch latch = new CountDownLatch(4);

        boolean isFair = false; // true 公平锁，false 不公平锁
        final ReentrantLock lock = new ReentrantLock(isFair);

        new Thread(new LockFairRunnable(1, lock, latch)).start();
        new Thread(new LockFairRunnable(2, lock, latch)).start();
        new Thread(new LockFairRunnable(3, lock, latch)).start();
        new Thread(new LockFairRunnable(4, lock, latch)).start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class LockFairRunnable implements Runnable {

        private CountDownLatch latch;

        private ReentrantLock lock;

        private int num;

        public LockFairRunnable(int num, ReentrantLock lock, CountDownLatch latch) {
            this.num = num;
            this.lock = lock;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                lock.lock();
//                lock.lockInterruptibly();
                System.out.println(Thread.currentThread().getName() + " show num :" + num);
                Thread.sleep((long) (Math.random() * 100000));
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
//
//    private void lockFair(ReentrantLock lock) {
//        try {
//            lock.lock();
//        } finally {
//            lock.unlock();
//        }
//    }

    private void trylockMethod(ReentrantLock lock) {

        try {
            //        if (lock.tryLock()) {
            if (lock.tryLock(2, TimeUnit.SECONDS)) {
                try {
                    System.out.println("lock locked succeed ....");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println("lock unlocked succeed ....");
                }
            } else {
                System.out.println("lock has locked....");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
