package com.carlos.curso.springboot.webapp.springboot_web.controllers;

import com.carlos.curso.springboot.webapp.springboot_web.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

    @GetMapping("/details")
    public String details(Model model) {
        User user = new User("Carlos", "Tornero");
        user.setEmail("blablabla@mail.bla");
        model.addAttribute("title", "Hola mundo Spring Boot");
        model.addAttribute("user", user);

        return "details";
    }

    @GetMapping("/list")
    public String list(ModelMap modelmap) {
        User user1 = new User("User1", "Apellido1");
        User user2 = new User("User2", "Apellido2");
        User user3 = new User("User3", "Apellido3");

        modelmap.addAttribute("title", "Listado de Usuarios");
        return "list";
    }

    @ModelAttribute("users")
    public List<User> usersModel() {
        List<User> users = Arrays.asList(new User("User1", "Apellido1", "blblblblblb"), new User("User2", "Apellido2"), new User("User3", "Apellido3", "julai@mail.julai"));
        return users;
    }
}
