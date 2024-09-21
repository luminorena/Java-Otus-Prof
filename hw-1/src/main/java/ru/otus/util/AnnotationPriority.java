package ru.otus.util;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import ru.otus.annotations.Test;

import java.lang.reflect.Method;

public class AnnotationPriority {
    protected static ListMultimap<Integer, Method> validatePriority(Class testClass) {
        ListMultimap<Integer, Method> priorityMap = ArrayListMultimap.create();
        int priority = 0;
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                Test testAnnotation = method.getAnnotation(Test.class);
                priority = testAnnotation.priority();
                if (priority < 1 || priority > 10) {
                    throw new IllegalArgumentException("Priority must be between 1 and 10");
                } else priorityMap.put(priority, method);
            }

        }

//        List<Map.Entry<Integer, Method>> sortedList = priorityMap.entries()
//                .stream()
//                .sorted(Map.Entry.<Integer, Method>comparingByKey().reversed())
//                .collect(Collectors.toList());
//
//        ListMultimap<Integer, Method> listMultimap = ArrayListMultimap.create();
//
//        for (Map.Entry<Integer, Method> entry : sortedList) {
//            listMultimap.put(entry.getKey(), entry.getValue());
//        }


        return priorityMap;
    }
}


