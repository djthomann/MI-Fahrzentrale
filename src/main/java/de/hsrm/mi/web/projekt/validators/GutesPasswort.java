package de.hsrm.mi.web.projekt.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Documented;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface GutesPasswort {
    String message() default "{gutespasswort.fehler}";
    // optionale Zusatzinfos
    Class<? extends Payload>[] payload() default { };
    // Zuordnung zu Validierungsgruppen?
    Class<?>[] groups() default { };
}
