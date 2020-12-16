package com.example.postgres.springpostgresdocker.repository;

import com.example.postgres.springpostgresdocker.dto.OrderDto;
import com.example.postgres.springpostgresdocker.model.CustomerOrder;
import com.example.postgres.springpostgresdocker.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * @author Bogdan Benga <bogdanbenga@gmail.com></>
 */
@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder,Long> {

    List<CustomerOrder> findByPlacedTimeAfterAndPlacedTimeBefore(OffsetDateTime startTime, OffsetDateTime endTime);

}
