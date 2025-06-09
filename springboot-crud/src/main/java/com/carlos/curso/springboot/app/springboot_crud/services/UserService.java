package com.carlos.curso.springboot.app.springboot_crud.services;

import com.carlos.curso.springboot.app.springboot_crud.entities.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User save(User user);

    boolean existsByUsername(String username);
}
