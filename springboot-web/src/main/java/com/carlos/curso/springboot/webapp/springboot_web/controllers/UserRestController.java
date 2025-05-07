package com.carlos.curso.springboot.webapp.springboot_web.controllers;

import com.carlos.curso.springboot.webapp.springboot_web.models.User;
import com.carlos.curso.springboot.webapp.springboot_web.models.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @GetMapping("/details-map")
    public Map<String, Object> detailsMap() {
        User user = new User("Carlos", "Tornero");

        Map<String, Object> response = new HashMap<>();
        response.put("title", "Hola mundo Spring Boot");
        response.put("user", user);

        return response;
    }

    @GetMapping("/details")
    public UserDto details() {
        User user = new User("Carlos", "Tornero");
        UserDto userDto = new UserDto();
        userDto.setTitle("Hola mundo Spring Boot");
        userDto.setUser(user);

        return userDto;
    }

    @GetMapping("/list")
    public List<User> list() {
        User user1 = new User("User1", "Apellido1");
        User user2 = new User("User2", "Apellido2");
        User user3 = new User("User3", "Apellido3");

        List<User> users = Arrays.asList(user1, user2, user3);
        //        List<User> users = new ArrayList<>();
        //        users.add(user1);
        //        users.add(user2);
        //        users.add(user3);

        return users;
    }
}
