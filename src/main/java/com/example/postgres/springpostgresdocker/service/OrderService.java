package com.example.postgres.springpostgresdocker.service;


import com.example.postgres.springpostgresdocker.dto.OrderDto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * @author Bogdan Benga <bogdanbenga@gmail.com></>
 */
public interface OrderService {

    void placeOrder(OrderDto orderDto);

    List<OrderDto> getOrders(OffsetDateTime startTime, OffsetDateTime endTime);

    BigDecimal getOrderPrice(Long id);

}
