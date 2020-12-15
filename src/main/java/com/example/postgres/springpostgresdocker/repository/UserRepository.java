package com.example.postgres.springpostgresdocker.repository;

import com.example.postgres.springpostgresdocker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Bogdan Benga <bogdanbenga@gmail.com></>
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String userName);
}
