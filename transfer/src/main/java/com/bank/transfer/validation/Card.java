package com.bank.transfer.validation;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CardConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Card {

    String message() default "{Card}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
