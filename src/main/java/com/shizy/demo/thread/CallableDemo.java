package com.shizy.demo.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 使用Callable同时处理多个线程，主线程会等待
 * <p>
 * 主线程等待所有子线程执行完毕时，获得子线程的执行结果(返回值)。
 */
public class CallableDemo {

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
             * 使用executor.execute()和executor.submit()时，主线程不等待。
             *
             * 区别：
             *  execute()会在主线程抛出子线程的异常，submit()不会抛出线程的运行时异常，会放在线程返回值中，在调用get()时，才会将异常抛出到console。
             *
             *  submit().get()获取线程返回值时(需将Runnable改成Callable)，主线程会等待。
             *  submit()的返回值是线程执行信息，该方法不等待。submit().get()的返回值是线程执行结果，该方法会使主线程等待。
             *
             *  使用executor.invokeAll()执行所有线程并获取执行结果时，主线程会等待。之后再使用get()则不会等待了。
             */
            List<Future<T>> list = executor.invokeAll(callList);

            for (Future<T> objectFuture : list) {
                T result = objectFuture.get();
                resultList.add(result);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        //shutdown线程池，不shutdown的话主线程不会关闭。
        executor.shutdown();

        return resultList;
    }

    public static void main(String[] args) {
        new CallableDemo().runDemo();
    }

}








