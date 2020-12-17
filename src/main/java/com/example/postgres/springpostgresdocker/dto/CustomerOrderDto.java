package com.example.postgres.springpostgresdocker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Bogdan Benga <bogdanbenga@gmail.com></>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrderDto {

    private long id;

    @NotNull
    private Map<ProductDto, Long> products;

    @NotNull
    @Email
    private String customerEmail;

    @NotNull
    private Date placedTime;

}
