package com.carlos.curso.springboot.app.springboot_crud.validation;

import com.carlos.curso.springboot.app.springboot_crud.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExistsByUsernameValidation implements ConstraintValidator<ExistsByUsername, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (this.userService == null) {
            return true;
        }

        return !this.userService.existsByUsername(s);
    }
}
