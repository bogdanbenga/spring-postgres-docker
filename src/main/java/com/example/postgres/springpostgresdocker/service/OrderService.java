package com.example.postgres.springpostgresdocker.service;


import com.example.postgres.springpostgresdocker.dto.CustomerOrderDto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * @author Bogdan Benga <bogdanbenga@gmail.com></>
 */
public interface OrderService {

    void placeOrder(CustomerOrderDto customerOrderDto);

    List<CustomerOrderDto> getOrders(OffsetDateTime startTime, OffsetDateTime endTime);

    BigDecimal getOrderPrice(Long id);

}
