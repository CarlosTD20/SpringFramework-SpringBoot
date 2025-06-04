package com.carlos.curso.springboot.app.springboot_crud.validation;

import com.carlos.curso.springboot.app.springboot_crud.services.ProductService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class IsExistsDbValidation implements ConstraintValidator<IsExistsDb, String > {

    @Autowired
    private ProductService service;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !this.service.existsBySku(s);
    }
}
