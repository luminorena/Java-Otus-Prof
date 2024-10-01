package ru.otus;

import ru.otus.util.TestRunner;

public class Main {
    public static void main(String[] args) throws Exception{
        Class<?> testClass = ComparatorTests.class;
        TestRunner.testRunner.chooseAnnotation(testClass);
        TestRunner.testRunner.run(testClass);
    }
}
