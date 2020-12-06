package com.example.postgres.springpostgresdocker.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.List;

/**
 * @author Bogdan Benga <bogdanbenga@gmail.com></>
 */
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
