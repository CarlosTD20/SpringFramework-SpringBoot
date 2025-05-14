package com.carlos.springboot.error.springboot_error.services;

import com.carlos.springboot.error.springboot_error.models.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private List<User> users;

    @Override
    public List<User> findAll() {
        return this.users;
    }

    //    @Override
    //    public Optional<User> findById(Long id) {
    //        User user = null;
    //        for (User u : this.users) {
    //            if (u.getId().equals(id)) {
    //                user = u;
    //                break;
    //            }
    //        }
    //        return Optional.ofNullable(user);
    //    }

    @Override
    public Optional<User> findById(Long id) {
        return users.stream().filter(u -> u.getId().equals(id)).findFirst();
    }
}
