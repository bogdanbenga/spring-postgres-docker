package com.example.postgres.springpostgresdocker.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date created;

}
