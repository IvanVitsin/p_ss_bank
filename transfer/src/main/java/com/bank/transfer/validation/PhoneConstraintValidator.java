package com.bank.transfer.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneConstraintValidator implements ConstraintValidator<Phone, Long> {

    @Override
    public void initialize(Phone phone) { }

    @Override
    public boolean isValid(Long phoneField, ConstraintValidatorContext cxt) {
        if(phoneField == null) {
            return false;
        }
        return phoneField.toString().startsWith("8") && phoneField.toString().length() == 11;
    }
}
