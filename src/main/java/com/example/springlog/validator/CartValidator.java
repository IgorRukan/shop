package com.example.springlog.validator;

import com.example.springlog.model.Cart;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class CartValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Cart.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Cart cart = (Cart) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount", "Cart.amount.empty");
        if(cart.getAmount().length()<1 || cart.getAmount().length()>10){
            errors.rejectValue("amount", "Cart.amount.length");
        }
    }
}
