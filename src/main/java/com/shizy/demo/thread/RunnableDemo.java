package com.shizy.demo.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * 使用Runnable同时处理多个线程，主线程不会等待
 * <p>
 * 主线程不等待子线程的执行结果(返回值)
 */
public class RunnableDemo {

    /**
     * 线程不会返回执行结果，可以用一个synchronizedList来保存执行信息
     */
    List resultList = Collections.synchronizedList(new ArrayList());

    private ExecutorService executor = Executors.newCachedThreadPool();

    /**
     * Executors.newCachedThreadPool() 和 Executors.newFixedThreadPool()的区别：
     *   https://stackoverflow.com/questions/949355/executors-newcachedthreadpool-versus-executors-newfixedthreadpool
     */
//    private ExecutorService executor = Executors.newFixedThreadPool(4);

    private void runDemo() {

        //--

        List callList = getRunableDemo();

        long a = System.currentTimeMillis();

        //启动线程
        invokeRunnableThreads(callList);

        long b = System.currentTimeMillis();
        System.out.println("all Threads run out. time:" + (b - a) + "ms");

        resultList = (List) resultList.stream().sorted().collect(Collectors.toList());

        System.out.println("==========");
        for (Object result : resultList) {
            System.out.println("result : " + result);
        }
    }

    private void invokeRunnableThreads(List callList){
        for (Object o : callList) {
            executor.execute((Runnable) o);
        }

        executor.shutdown();

        //手动等待，不需要结果则可以不等待
        while (!executor.isTerminated()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private List getRunableDemo(){

        Runnable callable1 = () -> {
            System.out.println("Thread running : 1");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resultList.add("111");
            System.out.println("Thread run end : 1");
        };
        Runnable callable2 = () -> {
            System.out.println("Thread running : 2");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resultList.add("222");
            System.out.println("Thread run end : 2");
        };
        Runnable callable3 = () -> {
            System.out.println("Thread running : 3");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resultList.add("333");
            System.out.println("Thread run end : 3");
        };

        List callList = new ArrayList<>();
        callList.add(callable1);
        callList.add(callable2);
        callList.add(callable3);

        return callList;
    }

    public static void main(String[] args) {
        new RunnableDemo().runDemo();
    }


}








