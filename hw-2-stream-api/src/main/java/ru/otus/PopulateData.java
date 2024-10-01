package ru.otus;


import java.util.stream.Stream;

public class PopulateData {

    static Stream<TaskDTO> getTasks() {
        return Stream.of(
                TaskDTO.builder()
                        .id(1)
                        .name("PROJECT-1")
                        .status("открыта")
                        .build(),
                TaskDTO.builder()
                        .id(2)
                        .name("PROJECT-2")
                        .status("открыта")
                        .build(),
                TaskDTO.builder()
                        .id(3)
                        .name("PROJECT-3")
                        .status("в работе")
                        .build(),
                TaskDTO.builder()
                        .id(4)
                        .name("PROJECT-4")
                        .status("в работе")
                        .build(),
                TaskDTO.builder()
                        .id(5)
                        .name("PROJECT-5")
                        .status("закрыта")
                        .build(),
                TaskDTO.builder()
                        .id(6)
                        .name("PROJECT-6")
                        .status("закрыта")
                        .build(),
                TaskDTO.builder()
                        .id(7)
                        .name("PROJECT-7")
                        .status("в тестировании")
                        .build(),
                TaskDTO.builder()
                        .id(8)
                        .name("PROJECT-8")
                        .status("анализ")
                        .build(),
                TaskDTO.builder()
                        .id(9)
                        .name("PROJECT-9")
                        .status("анализ")
                        .build(),
                TaskDTO.builder()
                        .id(10)
                        .name("PROJECT-10")
                        .status("анализ")
                        .build()
        );


    }


}
