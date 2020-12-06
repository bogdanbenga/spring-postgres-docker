package com.example.postgres.springpostgresdocker.repository;

import com.example.postgres.springpostgresdocker.model.CustomerOrder;
import com.example.postgres.springpostgresdocker.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bogdan Benga <bogdanbenga@gmail.com></>
 */
@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder,Integer> {
}
