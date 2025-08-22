package ru.otus;

public class Main {
    public static void main(String[] args) {
        ThreadPoolImpl threadPool = new ThreadPoolImpl(10);
        threadPool.execute(() -> System.out.println("Task 1 is running"));
        threadPool.execute(() -> System.out.println("Task 2 is running"));
        threadPool.execute(() -> System.out.println("Task 3 is running"));
        threadPool.execute(() -> System.out.println("Task 4 is running"));
        threadPool.execute(() -> System.out.println("Task 5 is running"));
        threadPool.execute(() -> System.out.println("Task 6 is running"));
        threadPool.execute(() -> System.out.println("Task 7 is running"));
        threadPool.execute(() -> System.out.println("Task 8 is running"));
        threadPool.execute(() -> System.out.println("Task 9 is running"));
        threadPool.execute(() -> System.out.println("Task 10 is running"));
        threadPool.shutdown();
    }
}
