package com.example.postgres.springpostgresdocker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Bogdan Benga <bogdanbenga@gmail.com></>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date placedTime;

}
