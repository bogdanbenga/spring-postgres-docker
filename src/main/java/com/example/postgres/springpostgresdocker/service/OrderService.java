package com.example.postgres.springpostgresdocker.service;


import com.example.postgres.springpostgresdocker.dto.OrderDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Bogdan Benga <bogdanbenga@gmail.com></>
 */
public interface OrderService {

    void placeOrder(OrderDto orderDto);
    List<OrderDto> getOrders(Date startTime, Date endTime);
    BigDecimal getOrderPrice(Long id);

}
