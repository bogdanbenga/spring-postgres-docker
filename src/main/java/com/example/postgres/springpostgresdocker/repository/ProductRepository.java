package com.example.postgres.springpostgresdocker.repository;

import com.example.postgres.springpostgresdocker.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
