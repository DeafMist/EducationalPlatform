package com.github.deafmist.educationalplatform.annotation.validator;

import com.github.deafmist.educationalplatform.annotation.TitleCase;
import com.github.deafmist.educationalplatform.annotation.enums.TitleCaseType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static com.github.deafmist.educationalplatform.annotation.enums.TitleCaseType.ANY;

public class TitleCaseConstraintValidator implements ConstraintValidator<TitleCase, String> {
    private TitleCaseType type;

    private final String ruRegex = "[а-яёА-ЯЁ]+";
    private final String enRegex = "[a-zA-Z]+";
    private final String digitsRegex = "[0-9]+";
    //TODO: Не реализована проверка недопустимых символов

    @Override
    public void initialize(TitleCase constraintAnnotation) {
        this.type = constraintAnnotation.type() != null ? constraintAnnotation.type() : ANY;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        boolean result = isGeneralValid(s);
        switch (type) {
            case EN -> result &= isEnValid(s);
            case RU -> result &= isRuValid(s);
            case ANY -> result &= (s.matches(enRegex) ? isEnValid(s) : isRuValid(s));
        }
        return result;
    }

    private boolean isRuValid(String s) {
        String[] words = s.split(" ");
        boolean result = Character.isUpperCase(words[0].charAt(0));
        int n = words.length;
        if (n > 1) {
            for (int i = 1; i < n; i++) {
                result &= Character.isLowerCase(words[i].charAt(0));
            }
        }
        return result;
    }

    private boolean isEnValid(String s) {
        String[] words = s.split(" ");
        boolean result = Character.isUpperCase(words[0].charAt(0));
        int n = words.length;
        if (n > 1) {
            result &= Character.isUpperCase(words[words.length - 1].charAt(0));
            for (int i = 1; i < n - 1; i++) {
                if (
                        !words[i].equals("a") && !words[i].equals("but") && !words[i].equals("for") &&
                        !words[i].equals("or") && !words[i].equals("not") && !words[i].equals("the") &&
                        !words[i].equals("an")
                ) {
                    result &= Character.isUpperCase(words[i].charAt(0));
                }
            }
        }
        return result;
    }

    private boolean isGeneralValid(String s) {
        return s.length() > 5 && !s.contains("\\t") && !s.contains("\\r") && !s.contains("\\n") && !s.contains("  ") &&
                s.charAt(0) != ' ' && s.charAt(s.length() - 1) != ' ' &&
                (
                        (s.matches(ruRegex) && !s.matches(enRegex)) ||
                                (s.matches(enRegex) && !s.matches(ruRegex))
                ) && !s.matches(digitsRegex);
    }
}
