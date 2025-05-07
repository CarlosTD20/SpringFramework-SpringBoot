package com.carlos.curso.springboot.webapp.springboot_web.controllers;

import com.carlos.curso.springboot.webapp.springboot_web.models.User;
import com.carlos.curso.springboot.webapp.springboot_web.models.dto.ParamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/var")
public class PathVariableController {

    @Value("${config.code}")
    private Integer code;

    @Value("${config.username}")
    private String message;

    @Value("${config.message}")
    private String username;

    @Value("${config.ListOfValues}")
    private List<String> listOfValues;

    @Value("#{ '${config.ListOfValues}'.split(',')}")
    private List<String> valueList;

    @Value("#{ '${config.ListOfValues}'.toUpperCase()}")
    private String valueString;

    @Value("#{${config.valuesMap}}")
    private Map<String, Object> valueJson;

    @Value("#{${config.valuesMap}.product}")
    private String product;

    @Value("#{${config.valuesMap}.description}")
    private String description;

    @Autowired
    private Environment environment;

    @GetMapping("/baz/{message}")
    public ParamDto baz(@PathVariable() String message) {
        ParamDto param = new ParamDto();
        param.setMessage(message);
        return param;
    }

    @GetMapping("/mix/{product}/{id}")
    public Map<String, Object> mixPath(@PathVariable String product, @PathVariable Long id) {
        Map<String, Object> json = new HashMap<>();
        json.put("product", product);
        json.put("id", id);
        return json;
    }

    @PostMapping("/create")
    public User create(@RequestBody User user) {

        return user;
    }

    @GetMapping("/values")
    public Map<String, Object> getValues() {
        Map<String, Object> json = new HashMap<>();
        json.put("code", code);
        json.put("code2", Integer.parseInt(environment.getProperty("config.code")));
        json.put("code3", environment.getProperty("config.code", Long.class));
        json.put("username", username);
        json.put("message", message);
        json.put("message2", environment.getProperty("config.message"));
        json.put("listOfValues", listOfValues);
        json.put("listValues", valueList);
        json.put("valueString", valueString);
        json.put("JSON", valueJson);
        json.put("product", product);
        json.put("description", description);

        return json;
    }
}
