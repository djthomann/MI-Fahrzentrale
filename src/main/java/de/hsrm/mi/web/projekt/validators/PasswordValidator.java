package de.hsrm.mi.web.projekt.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<GutesPasswort, String> {

    private String wortSiebzehn = "siebzehn";
    private String zahlSiebzehn = "17";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        
        if(value == null || value.equals("")) {
            return true;
        }

        value = value.toLowerCase();

        if(value.contains(wortSiebzehn) || value.contains(zahlSiebzehn)) {
            return true;
        } else {
            return false;
        }
    }

}
