package com.carlos.curso.springboot.app.aop.springboot_aop.controllers;

import com.carlos.curso.springboot.app.aop.springboot_aop.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    @GetMapping("/greeting")
    public ResponseEntity<?> greeting() {
        return ResponseEntity.ok(Collections.singletonMap("greeting", this.greetingService.sayHello("Pepe", "Hola que tal!")));
    }

    @GetMapping("/greetingError")
    public ResponseEntity<?> greetingError() {
        return ResponseEntity.ok(Collections.singletonMap("greeting", this.greetingService.sayHelloError("Pepe", "Hola que tal!")));
    }
}
