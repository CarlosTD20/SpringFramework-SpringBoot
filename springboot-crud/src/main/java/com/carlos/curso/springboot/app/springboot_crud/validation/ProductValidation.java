package com.carlos.curso.springboot.app.springboot_crud.validation;

import com.carlos.curso.springboot.app.springboot_crud.entities.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null, "Es requerido");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotBlank.product.description");

        if (product.getDescription() == null || product.getDescription().isBlank()) {
            errors.rejectValue("description", null, "Es requerido! porfi <3");
        }

        if (product.getPrice() == null) {
            errors.rejectValue("price", null, "No puede ser nulo, ok!");
        } else if (product.getPrice() < 500) {
            errors.rejectValue("price", null, "Debe ser mayor o igual a 500!");
        }
    }
}
