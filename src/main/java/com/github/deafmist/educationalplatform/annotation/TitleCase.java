package com.github.deafmist.educationalplatform.annotation;

import com.github.deafmist.educationalplatform.annotation.enums.TitleCaseType;
import com.github.deafmist.educationalplatform.annotation.validator.TitleCaseConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TitleCaseConstraintValidator.class)
@Documented
public @interface TitleCase {
    TitleCaseType type();

    String message() default "Title is incorrect";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
