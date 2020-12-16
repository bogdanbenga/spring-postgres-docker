package com.example.postgres.springpostgresdocker.service;

import com.example.postgres.springpostgresdocker.dto.OrderDto;
import com.example.postgres.springpostgresdocker.model.CustomerOrder;
import com.example.postgres.springpostgresdocker.model.Product;
import com.example.postgres.springpostgresdocker.repository.CustomerOrderRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Bogdan Benga <bogdanbenga@gmail.com></>
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    CustomerOrderRepository orderRepository;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public void placeOrder(OrderDto orderDto) {
        CustomerOrder order = modelMapper.map(orderDto, CustomerOrder.class);
        orderRepository.save(order);
    }

    @Override
    public List<OrderDto> getOrders(OffsetDateTime startTime, OffsetDateTime endTime) {

        List<CustomerOrder> orderList = orderRepository
                .findByPlacedTimeAfterAndPlacedTimeBefore(startTime, endTime);

        Type listType = new TypeToken<List<OrderDto>>() {
        }.getType();

        return modelMapper.map(orderList, listType);
    }

    @Override
    public BigDecimal getOrderPrice(Long id) {
        CustomerOrder order = orderRepository.getOne(id);
        Set<Product> products = order
                .getProducts();
        BigDecimal totalPrice = calculateOrderPrice(products);
        return totalPrice;
    }

    private BigDecimal calculateOrderPrice(Set<Product> products) {
        BigDecimal totalPrice = new BigDecimal("0");
        for (Product product : products) {
            totalPrice = totalPrice.add(product.getPrice());
        }
        return totalPrice;
    }
}
