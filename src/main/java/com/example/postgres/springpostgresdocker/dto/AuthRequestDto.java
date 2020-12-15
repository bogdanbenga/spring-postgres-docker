package com.example.postgres.springpostgresdocker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bogdan Benga <bogdanbenga@gmail.com></>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDto {

    private String userName;
    private String password;

}
