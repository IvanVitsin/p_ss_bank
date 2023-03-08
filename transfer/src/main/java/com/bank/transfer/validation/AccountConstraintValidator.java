package com.bank.transfer.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AccountConstraintValidator implements ConstraintValidator<Account, Long> {

    @Override
    public void initialize(Account account) {}

    @Override
    public boolean isValid(Long accountField, ConstraintValidatorContext cxt) {
        if(accountField == null) {
            return false;
        }
        return accountField.toString().length() == 12;
    }
}