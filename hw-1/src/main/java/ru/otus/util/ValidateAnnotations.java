package ru.otus.util;

import ru.otus.ComparatorTests;
import ru.otus.annotations.AfterSuite;
import ru.otus.annotations.BeforeSuite;
import ru.otus.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ValidateAnnotations {
    protected static int countAnnotations(Class<?> testClass, Class<?> annotation) {
        int count = 0;
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent((Class<? extends Annotation>) annotation)) {
                count++;
            }
        }
        return count;
    }


    protected static void showMessage() throws CustomException {
        if (countAnnotations(ComparatorTests.class, AfterSuite.class) > 1) {
            throw new CustomException("AfterSuite annotation should have only one instance in class");
        }
        if (countAnnotations(ComparatorTests.class, BeforeSuite.class) > 1) {
            throw new CustomException("BeforeSuite annotation should have only one instance in class");
        }
    }

    protected static void isMarkedOneMethod(Class<?> testClass) throws CustomException {
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(AfterSuite.class) &&
                    method.isAnnotationPresent(BeforeSuite.class) &&
                    method.isAnnotationPresent(Test.class)) {
                throw new CustomException("Annotations AfterSuite, BeforeSuite, Test must be " +
                        "on separate methods");
            }

        }
    }
}

