package com.shizy.feature.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 使用Callable"分步骤"处理多个线程
 * <p>
 * 使用Future.get()，手动使主线程等待所有子线程执行完毕，然后获得子线程的执行结果(返回值)。
 */
public class CallableDemo2 {

    public void runDemo() {

        Callable callable1 = () -> {
            System.out.println("Thread running : 1");
            Thread.sleep(2000);
            System.out.println("Thread run end : 1");
            return 1;
        };
        Callable callable2 = () -> {
            System.out.println("Thread running : 2");
            Thread.sleep(1000);
            System.out.println("Thread run end : 2");
            return 2;
        };
        Callable callable3 = () -> {
            System.out.println("Thread running : 3");
            Thread.sleep(3000);
            System.out.println("Thread run end : 3");
            return 3;
        };

        List callList = new ArrayList<>();
        callList.add(callable1);
        callList.add(callable2);
        callList.add(callable3);

        //-------------

        long a = System.currentTimeMillis();

        List resultList = invokeCallableThreads(callList);

        long b = System.currentTimeMillis();
        System.out.println("all Threads run out. time:" + (b - a) + "ms");

        System.out.println("==========");
        for (Object result : resultList) {
            System.out.println("result : " + result);
        }

    }

    public <T> List<T> invokeCallableThreads(List<Callable<T>> callList) {

        ExecutorService executor = Executors.newFixedThreadPool(callList.size());
        List<T> resultList = new ArrayList();

        try {
            /**
             * 使用executor.submit()时，主线程不等待，子线程不执行，等调用get()时再执行。不会抛出线程的运行时异常，会放在线程返回值中。
             * submit().get()获取线程返回值时(需将Runnable改成Callable)，主线程会等待。
             * submit()的返回值是线程执行信息，该方法不等待。submit().get()的返回值是线程执行结果，该方法会使主线程等待。
             */
            List<Future> futures = new ArrayList<>();
            for (Callable callable : callList) {
                Future future = executor.submit(callable);
                futures.add(future);
            }

            for (Future<T> objectFuture : futures) {
                T result = objectFuture.get();
                resultList.add(result);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();

        return resultList;
    }

    public static void main(String[] args) {
        new CallableDemo2().runDemo();
    }

}








