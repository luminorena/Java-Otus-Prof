package ru.otus;


import lombok.*;

@Builder
@ToString
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TaskDTO {
    @Getter
    private final int id;

    @Getter
    private final String name;

    @Getter
    @EqualsAndHashCode.Include
    private final String status;
}

