package com.bank.transfer.validation;


import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AccountConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Account {

    String message() default "{Account}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}