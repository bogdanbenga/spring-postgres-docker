package com.example.postgres.springpostgresdocker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author Bogdan Benga <bogdanbenga@gmail.com></>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private long id;

    @NotNull
    private List<ProductDto> products;

    @NotNull
    @Email
    private String customerEmail;

    @NotNull
    private Date placedTime;

}
