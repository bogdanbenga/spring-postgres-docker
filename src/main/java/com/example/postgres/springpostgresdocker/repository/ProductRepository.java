package com.example.postgres.springpostgresdocker.repository;

import com.example.postgres.springpostgresdocker.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Bogdan Benga <bogdanbenga@gmail.com></>
 */
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByName(String name);

    long deleteByName(String name);
}
