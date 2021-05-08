package com.shizy.feature.thread.zz;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 自定义线程池（伪）
 * 思路：顺序执行传入的线程，设置一个线程上限，超过该上限的线程则等待。
 * 目的：动态stop线程池中的线程
 * <p>
 * ps：随便写的 设计很随意 且只通过了简单测试
 */
public class StopedThreadPool {

    private boolean isTerminated;//是否干完活了

    private int poolSize;

    private List<Thread> runThreads;

    private List<Thread> waitThreads;

    private Map<Thread, AfterThread> realRunThreads;

    public StopedThreadPool(int poolSize) {
        this.poolSize = poolSize;
        runThreads = Collections.synchronizedList(new LinkedList<>());
        waitThreads = Collections.synchronizedList(new LinkedList());
        realRunThreads = new ConcurrentHashMap();
        isTerminated = true;
    }

    public void execute(Runnable runnable) {
        execute(new Thread(runnable));
    }

    public synchronized void execute(Thread thread) {
        if (runThreads.size() < poolSize) {
            runThread(thread);
        } else {
            waitThreads.add(thread);
        }
    }

    private synchronized void runThread(Thread thread) {
        runThreads.add(thread);
        AfterThread afterThread = new AfterThread(thread);
        realRunThreads.put(thread, afterThread);
        afterThread.start();
    }

    public synchronized void shutdown() {
        //不接收新任务 懒得写了
    }

    public boolean isTerminated() {
        return isTerminated;
    }

    public List<Thread> getRunning() {
        return runThreads;
    }

    public synchronized void stop(Thread thread) {
        realRunThreads.get(thread).stop();
//        realRunThreads.get(thread).interrupt();

        runThreads.remove(thread);
        realRunThreads.remove(thread);
    }

    class AfterThread extends Thread {
        Thread runThread;

        public AfterThread(Thread thread) {
            super(thread);
            runThread = thread;
        }

        @Override
        public void run() {
            try {
                setTerminated(false);
                super.run();
            } finally {
                popAndRunWaitThread(runThread);
            }
        }
    }

    private synchronized void setTerminated(boolean b) {
        isTerminated = b;
    }

    private synchronized boolean getTerminated() {
        return isTerminated;
    }

    private synchronized void popAndRunWaitThread(Thread runThread) {
        runThreads.remove(runThread);
        realRunThreads.remove(runThread);
        Thread thread = popWaitThread();
        if (thread != null) {
            runThread(thread);
        } else {
            setTerminated(true);
        }
    }

    private Thread popWaitThread() {
        if (waitThreads.size() <= 0) {
            return null;
        }
        Thread thread = waitThreads.get(0);
        waitThreads.remove(0);
        return thread;
    }
}

























