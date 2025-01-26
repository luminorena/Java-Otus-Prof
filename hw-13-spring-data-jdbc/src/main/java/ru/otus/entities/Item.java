package ru.otus.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("ITEMS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    private Long id;
    private double price;
    private double discount;
    private String shortName;
    private String description;
}
