package com.carlos.springboot.error.springboot_error;

import com.carlos.springboot.error.springboot_error.models.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    List<User> listUers() {
        List<User> users = Arrays.asList(new User(1L, "Pepe", "Gonzalez"), new User(2L, "Andres", "Mena"), new User(3L, "Maria", "Perez"), new User(4L, "Josefa", "Ramirez"), new User(5L, "Ale", "Gutierrez"));
        return users;
    }

}
