package com.mall.common.validator;

import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 用于验证状态是否在指定范围内的注解
 * @author wangyongchun
 * @version 1.0
 * @date 2019/03/09 20:44
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})

public @interface FlagValidator {

    /**
     * 是否必填,默认是非必填
     * @return
     */
    boolean required() default false;

    String[] value() default {};

    /**
     * 验证失败的返回信息
     * @return
     */
    String message() default "flag is not found";

    /**
     * 分组的内容
     * @return
     */
    Class<?>[] group() default {};

    /**
     * 错误验证的级别
     * @return
     */
    Class<? extends Payload>[] payload() default {};

}
