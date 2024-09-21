package ru.otus.util;

import ru.otus.ComparatorTests;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TestRunner {
    protected ArrayList<Method> beforeSuiteList = new ArrayList<>();
    protected ArrayList<Method> afterSuiteList = new ArrayList<>();
    protected ArrayList<Method> disabledList = new ArrayList<>();
    protected int tests = 0;
    protected int passed = 0;
    protected int disabledMethods = 0;
    protected int disabledClasses = 0;
    public static TestRunner testRunner = new TestRunner();

    public void chooseAnnotation(Class<?> testClass) throws CustomException {
        ValidateAnnotations.showMessage();
        ValidateAnnotations.isMarkedOneMethod(testClass);
        final var declaredMethods = testClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            for (Annotation annotation : method.getDeclaredAnnotations()) {
                switch (annotation.annotationType().getSimpleName()) {
                    case "BeforeSuite" -> beforeSuiteList.add(method);
                    case "AfterSuite" -> afterSuiteList.add(method);
                    case "Disabled" -> disabledList.add(method);
                }
            }
        }

        Class<?> classToFind = ComparatorTests.class;
        final Class<?>[] declaredClasses = classToFind.getDeclaredClasses();
        for (Class<?> declaredClass : declaredClasses) {
            Annotation[] annotations = declaredClass.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().getSimpleName().equals("Disabled")) {
                    disabledClasses++;
                    System.out.println("Class " + declaredClass.getSimpleName() + " is disabled");
                }
            }
        }
    }

    public void run(Class<?> testRunner) throws Exception {
        final var constructor = testRunner.getConstructor();
        List<Map.Entry<Integer, Method>> integerMethodMap = AnnotationPriority.validatePriority(testRunner);
        Iterator<Map.Entry<Integer, Method>> iterator = integerMethodMap.iterator();
        int totalTests = integerMethodMap.size();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Method> entry = iterator.next();
            if (disabledList.contains(entry.getValue())) {
                System.out.println("Test " + entry.getValue().getName() + " is disabled");
                iterator.remove();
                disabledMethods++;
            }
        }

        for (Map.Entry<Integer, Method> entry : integerMethodMap) {
            Method method = entry.getValue();
            if (disabledList.contains(method)) {
                System.out.println("Test " + method.getName() + " is disabled");
                disabledMethods++;
                continue;
            }

            final var newInstance = constructor.newInstance();
            tests++;

            for (Method before : beforeSuiteList) {
                try {
                    before.invoke(newInstance);
                } catch (InvocationTargetException e) {
                    e.getTargetException().printStackTrace();
                }
            }

            System.out.println("Test to invoke now: " + method.getName());
            try {
                method.invoke(newInstance);
                passed++;
            } catch (InvocationTargetException e) {
                e.getTargetException().printStackTrace();
            }

            for (Method after : afterSuiteList) {
                try {
                    after.invoke(newInstance);
                } catch (InvocationTargetException e) {
                    e.getTargetException().printStackTrace();
                }
            }
        }


        System.out.printf("Total classes: %d, Passed: %d, Failed: %d, Disabled methods: %d, Disabled classes: %d%n",
                totalTests, passed, totalTests - passed - disabledMethods, disabledMethods, disabledClasses);
    }

}


