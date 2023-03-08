package com.bank.transfer.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CardConstraintValidator implements ConstraintValidator<Card, Long> {

    @Override
    public void initialize(Card card) {}

    @Override
    public boolean isValid(Long cardField, ConstraintValidatorContext cxt) {
        if(cardField == null) {
            return false;
        }
        return cardField.toString().length() == 16;
    }
}
