package com.mall.common.validator;

import com.mall.common.annotation.Mobile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class MobileValidator implements ConstraintValidator<Mobile, String> {

    private Mobile mobile;

    private static final Pattern mobilePattern = Pattern.compile("1\\d{10}");

    @Override
    public void initialize(Mobile constraintAnnotation) {
        this.mobile = constraintAnnotation;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null){
            return mobile.required() ? false : true;
        }

        return isMobile(s);
    }

    public static boolean isMobile(String mobile){
        return mobilePattern.matcher(mobile).matches();
    }
}
