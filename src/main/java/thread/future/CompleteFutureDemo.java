package thread.future;

import io.netty.util.concurrent.CompleteFuture;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public class CompleteFutureDemo {


    @Test
    public void test1() throws ExecutionException, InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello world";
        });

        //阻塞获取结果
//        System.out.println(future.get());
        //立即过去结果，不阻塞，如果没有返回，那么返回传入的参数值
//        System.out.println(future.getNow("world"));
//        System.out.println(future.join());

        //当future 阻塞时候，get返回complete传入的值
//        future.complete("one");
//        System.out.println(future.get());

        CompletableFuture<String> future1 = future.thenApply((e) -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            return e + " add string";
        }).thenApply((e) -> {
            return e + " add two string";
        });

        CompletableFuture future2 = future.thenAccept((e) -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println("then accept " + e);
        });


        future.whenCompleteAsync((v, e) -> callBack(v, e));

        System.out.println(future1.get());

//        System.out.println(future2.get());


        CompletableFuture<String> future3 = future.thenCombineAsync(CompletableFuture.completedFuture("compose"),
                (x, y) -> {
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return x + " " + y;
                });//hello world compose

        future3.whenCompleteAsync((v, e) -> callBack(v, e));

        latch.await();
    }

    public void callBack(String res, Throwable e) {
        System.out.println(res);
    }


    @Test
    public void test2() throws ExecutionException, InterruptedException {

        Random rand = new Random();
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 200;
        });

//        CompletableFuture<String> future = future1.applyToEither(future2, integer -> integer.toString());
//        CompletableFuture future = future1.acceptEither(future2, integer -> {
//            System.out.println(integer);
//        });

        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 300;
        });

        CompletableFuture<Object> futureR = CompletableFuture.anyOf(future1, future2, future3);

//        futureR.whenCompleteAsync((v, e) -> System.out.println(e));
        System.out.println(futureR.get());

//        System.out.println(future.get());
    }


    Integer allCount = 0;

    @Test
    public void test3() {

        Random rand = new Random();
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {

            if (rand.nextInt() % 2 == 0) {
                return 12 / 0;
            }

            callBack2(100, null);
            return allCount;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            callBack2(300, null);
            return allCount;
        });

        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            callBack2(200, null);
            return allCount;
        });

        List<CompletableFuture<Integer>> futures = Arrays.asList(future1, future2, future3);

        Function function = new Function<Throwable, Integer>() {
            @Override
            public Integer apply(Throwable throwable) {
                for (int i = 0; i < futures.size(); i++) {
                    if (!futures.get(i).isCancelled()) {
                        futures.get(i).cancel(true);
                    }
                }
                return -1;
            }
        };


        future1.exceptionally(function);
        future2.exceptionally(function);
        future3.exceptionally(function);

        CompletableFuture<Void> futureR = CompletableFuture.allOf(future1, future2, future3);

        futureR.whenComplete((v, e) -> {
            System.out.println("final result:" + allCount);
        });

        futureR.exceptionally((v) -> {
            rollBack(0);
            return null;
        });

        try {
            futureR.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    public void callBack2(Integer element, Throwable throwable) {
        allCount += element;
    }

    public void rollBack(Integer element) {
        System.out.println("call back !");
        allCount = element;
    }
}
