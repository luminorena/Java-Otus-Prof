package ru.otus.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "purchases")
public class Purchases {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;

    @Column(name = "buy_time")
    private String buyTime;

    @Column(name = "buy_price")
    private double buyPrice;

    @Override
    public String toString() {
        return "Purchases{" +
                ", buyTime=" + buyTime +
                ", buyPrice=" + buyPrice +
                '}';
    }
}


