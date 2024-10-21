package ru.otus;

public class Main {
    public static void main(String[] args) {
        ThreadPoolImpl threadPool = new ThreadPoolImpl(2);
        threadPool.execute(() -> System.out.println("Task 1 is running"));
        threadPool.execute(() -> System.out.println("Task 2 is running"));
        threadPool.shutdown();
    }
}
