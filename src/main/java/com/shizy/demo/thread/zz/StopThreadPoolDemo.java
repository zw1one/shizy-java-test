package com.shizy.demo.thread.zz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class StopThreadPoolDemo {

    private static final Logger logger = LoggerFactory.getLogger(StopThreadPoolDemo.class);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        new StopThreadPoolDemo().dothis();
    }

    public void dothis() throws InterruptedException, ExecutionException {

        StopedThreadPool executor = new StopedThreadPool(2);

//        ExecutorService executor = Executors.newFixedThreadPool(4);

        Thread thread1 = new TestThread();
        Thread thread2 = new TestThread();
        Thread thread3 = new TestThread();
        Thread thread4 = new TestThread();

        executor.execute(thread1);
        executor.execute(thread2);
        executor.execute(thread3);
        executor.execute(thread4);


        Thread.sleep(3000);
        logger.error("stop1");
//        thread1.stop();//直接stop是stop不了的

        List<Thread> running = executor.getRunning();

        executor.stop(running.get(0));


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

    class TestThread extends Thread {
        @Override
        public void run() {
            logger.error(getName() + " : " + "start");

            for (int i = 0; i < 8; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.error(getName() + " : " + i + "");
            }
            logger.error(getName() + " : " + "end");
        }
    }

}























