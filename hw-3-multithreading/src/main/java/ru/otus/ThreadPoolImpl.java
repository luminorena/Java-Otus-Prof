package ru.otus;

import java.util.concurrent.ArrayBlockingQueue;

public class ThreadPoolImpl {
    private final ArrayBlockingQueue<Runnable> taskQueue;
    private boolean isFinished;

    protected ThreadPoolImpl(int threadCount) {
        taskQueue = new ArrayBlockingQueue<>(threadCount);
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

        if (!taskQueue.offer(r)) {
            throw new IllegalStateException("Task queue is full " +
                    "and cannot accept new tasks");
        }
    }

    protected void shutdown() {
        isFinished = true;
    }

    private class Executor implements Runnable {
        @Override
        public void run() {
            while (!isFinished || !taskQueue.isEmpty()) {
                try {
                    Runnable task = taskQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
