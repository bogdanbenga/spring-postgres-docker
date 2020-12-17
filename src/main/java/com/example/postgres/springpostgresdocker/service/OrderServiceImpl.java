package com.example.postgres.springpostgresdocker.service;

import com.example.postgres.springpostgresdocker.dto.CustomerOrderDto;
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
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public void placeOrder(CustomerOrderDto customerOrderDto) {
        CustomerOrder order = modelMapper.map(customerOrderDto, CustomerOrder.class);
        orderRepository.save(order);
    }

    @Override
    public List<CustomerOrderDto> getOrders(OffsetDateTime startTime, OffsetDateTime endTime) {

        List<CustomerOrder> orderList = orderRepository
                .findByPlacedTimeAfterAndPlacedTimeBefore(startTime, endTime);

        Type listType = new TypeToken<List<CustomerOrderDto>>() {
        }.getType();

        return modelMapper.map(orderList, listType);
    }

    @Override
    public BigDecimal getOrderPrice(Long id) {
        CustomerOrder order = orderRepository.getOne(id);
        BigDecimal totalPrice = calculateOrderPrice(order.getProducts());
        return totalPrice;
    }

    private BigDecimal calculateOrderPrice(Map<Product, Long> products) {
        BigDecimal totalPrice = new BigDecimal("0");
        for (Product product : products.keySet()) {
            totalPrice = totalPrice.add(product.getPrice().multiply(new BigDecimal(products.get(product))));
        }
        return totalPrice;
    }
}
