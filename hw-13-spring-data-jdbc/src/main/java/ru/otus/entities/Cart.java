package ru.otus.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Table("CART")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart  {
    @Id
    private Long id;
    private double price;
    private double discount;
    private String shortName;
    private String description;
//    @Transient
//    private boolean isNew;

//    @PersistenceCreator
//    public Cart(double price, double discount, String shortName, String description) {
//        this.price = price;
//        this.discount = discount;
//        this.shortName = shortName;
//        this.description = description;
//    }

//    @Override
//    public boolean isNew() {
//        return false;
//    }


//    public Cart(double price, double discount, String shortName, String description) {
//        this.price = price;
//        this.discount = discount;
//        this.shortName = shortName;
//        this.description = description;
//    }
}
