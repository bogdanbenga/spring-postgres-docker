package com.example.postgres.springpostgresdocker.controller;

import com.example.postgres.springpostgresdocker.dto.OrderDto;
import com.example.postgres.springpostgresdocker.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * @author Bogdan Benga <bogdanbenga@gmail.com></>
 */
@RestController
@RequestMapping("customer-orders")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    @GetMapping
    public List<OrderDto> getOrders(@RequestParam OffsetDateTime startTime, @RequestParam OffsetDateTime endTime) {
        LOGGER.info("Get Orders from: " + startTime + ", to: " + endTime);
        return orderService.getOrders(startTime, endTime);
    }

    @PostMapping
    public void placeOrder(@Valid @RequestBody OrderDto orderDto) {
        LOGGER.info("Place Order: " + orderDto);
        orderService.placeOrder(orderDto);
    }

    @GetMapping("/price")
    public void getOrderPrice(@RequestParam Long id) {
        LOGGER.info("Get order price for order with id: " + id);
        orderService.getOrderPrice(id);
    }
}
