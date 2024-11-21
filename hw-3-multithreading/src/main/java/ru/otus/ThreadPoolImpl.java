package ru.otus;

import java.util.ArrayList;
import java.util.List;


public class ThreadPoolImpl {
    private final List<Runnable> taskQueue;
    private volatile boolean isFinished;
    private final Object lock = new Object();

    protected ThreadPoolImpl(int threadCount) {
        taskQueue = new ArrayList<>(threadCount);
        isFinished = false;
        for (int i = 0; i < threadCount; i++) {
            new Thread(new Executor()).start();
        }
    }

    protected void execute(Runnable r) {
        if (isFinished) {
            throw new IllegalStateException("ThreadPool is shut down " +
                    "and cannot accept new tasks");
        }

        synchronized (lock) {
            taskQueue.add(r);
            lock.notify();
        }
    }

    protected void shutdown() {
        isFinished = true;
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    private class Executor implements Runnable {
        @Override
        public void run() {
            Runnable task = null;
            synchronized (lock) {
                while (!isFinished || !taskQueue.isEmpty()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!taskQueue.isEmpty()) {
                        task = taskQueue.remove(0);
                    }
                }
            }
            try {
                task.run();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}
