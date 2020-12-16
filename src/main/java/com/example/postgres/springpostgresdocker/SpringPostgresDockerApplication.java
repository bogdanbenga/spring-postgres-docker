package com.example.postgres.springpostgresdocker;

import com.example.postgres.springpostgresdocker.model.User;
import com.example.postgres.springpostgresdocker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * @author Bogdan Benga <bogdanbenga@gmail.com></>
 */
@SpringBootApplication
public class SpringPostgresDockerApplication {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void initUsers() {
        User admin = new User();
        admin.setId(101);
        admin.setUserName("spring_admin");
        admin.setPassword("pass");
        admin.setEmail("admin@spuser.com");
        userRepository.save(admin);

        User testUser = new User();
        testUser.setId(102);
        testUser.setUserName("spring_test");
        testUser.setPassword("pass");
        testUser.setEmail("test@spuser.com");
        userRepository.save(testUser);
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringPostgresDockerApplication.class, args);
    }

}
