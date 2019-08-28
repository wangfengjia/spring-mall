package com.mall.common.annotation;


import com.mall.common.validator.MobileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MobileValidator.class)
public @interface Mobile {

    boolean required() default true;

    String message() default "手机号格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
