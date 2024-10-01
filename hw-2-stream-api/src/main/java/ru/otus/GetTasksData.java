package ru.otus;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GetTasksData extends PopulateData {


    public static void main(String[] args) {
        System.out.println(getTasksByStatus());
        System.out.println(isTaskWithIdPresent());
        System.out.println(getSortedByStatusTasks());
        System.out.println(countTasks());
    }

    protected static List<TaskDTO> getTasksByStatus() {
        Stream<TaskDTO> tasks = getTasks();
        return tasks.filter(task -> task.getStatus()
                .equals("открыта"))
                .toList();
    }

    protected static boolean isTaskWithIdPresent() {

        Stream<TaskDTO> tasks = getTasks();
        return tasks.anyMatch(t -> t.getId() == 100);
    }

    protected static List<TaskDTO> getSortedByStatusTasks() {
        Stream<TaskDTO> tasks = getTasks();
        return tasks
                .sorted(Comparator.comparing(TaskDTO::getStatus))
                .distinct()
                .limit(3)
                .collect(Collectors.toList());
    }

    protected static Long countTasks() {
        Stream<TaskDTO> tasks = getTasks();
        return tasks.filter(t -> t.getStatus()
                .equals("анализ"))
                .count();
    }


}
