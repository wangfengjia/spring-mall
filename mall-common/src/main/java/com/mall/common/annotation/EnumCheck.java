package com.mall.common.annotation;


import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumCheck {

    /*
    * 是否必填,默认是非必填
    */
    boolean required() default false;

    /*
    * 验证失败的返回信息
    */
    String message() default "枚举类验证失败";

    /*
    * 分组的内容
    * @return
    */
    Class<?>[] groups() default {};

    /*
    * 错误验证的级别
    * @return
    */
    Class<? extends Payload>[] payload() default {};

    /*
    * 枚举的class
    * @return
    */
    Class<? extends Enum<?>> enumClass();

    /*
    * 枚举中的验证方法
    */
    String enumMethod() default "validation";
}
