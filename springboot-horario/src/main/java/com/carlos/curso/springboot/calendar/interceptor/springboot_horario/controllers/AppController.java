package com.carlos.curso.springboot.calendar.interceptor.springboot_horario.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AppController {

    @GetMapping("/foo")
    public ResponseEntity<?> foo(HttpServletRequest request) {
        Map<String, Object> data = new HashMap<>();
        data.put("title", "Bienvenidos al sistem de atención!");
        data.put("tiem", new Date());
        data.put("message", request.getAttribute("message"));
        return ResponseEntity.ok(data);
    }
}
