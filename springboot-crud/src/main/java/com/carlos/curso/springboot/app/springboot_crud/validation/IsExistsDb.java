package com.carlos.curso.springboot.app.springboot_crud.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IsExistsDbValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsExistsDb {

    String message() default "Ya existe en base de datos";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
