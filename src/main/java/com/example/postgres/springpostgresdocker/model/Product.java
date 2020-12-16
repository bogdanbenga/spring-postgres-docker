package com.example.postgres.springpostgresdocker.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

/**
 * @author Bogdan Benga <bogdanbenga@gmail.com></>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private OffsetDateTime created;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_order",
            joinColumns = { @JoinColumn(name = "product_id",nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "order_id", nullable = false) })
    private Set<CustomerOrder> orders;

}
