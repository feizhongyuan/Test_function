package com.example.threadpool_executor;

import android.util.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by fei .
 * Created by Date 2019/8/8 16:21
 */

public class MyThreadPoolExecutor extends ThreadPoolExecutor {
    private boolean isPaused;
    private ReentrantLock pauseLock = new ReentrantLock();//重入锁定
    private Condition unpaused = pauseLock.newCondition();//条件

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public void pause() {
        pauseLock.lock();
        try {
            isPaused = true;
        } finally {
            pauseLock.unlock();
        }
    }

    public void resume() {
        pauseLock.lock();
        try {
            isPaused = false;
            unpaused.signalAll();//唤醒所有线程
        } finally {
            pauseLock.unlock();
        }
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        String threadName = t.getName();
        Log.v("zxy", "线程：" + threadName + "准备执行任务！");

        pauseLock.lock();//加锁
        try {
            while (isPaused) {
                unpaused.await();//等待
            }
        } catch (InterruptedException ie) {
            t.interrupt();
        } finally {
            pauseLock.unlock();//解锁
        }

    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        String threadName = Thread.currentThread().getName();
        Log.v("zxy", "线程：" + threadName + "任务执行结束！");
    }

    @Override
    protected void terminated() {
        super.terminated();
//        线程池结束

    }


}
