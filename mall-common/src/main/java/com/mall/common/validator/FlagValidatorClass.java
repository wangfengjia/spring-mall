package com.mall.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 状态标记验证器
 * @author wangyongchun
 * @version 1.0
 * @date 2019/03/09 23:17
 */
public class FlagValidatorClass implements ConstraintValidator<FlagValidator, Integer> {

    private String[] values;
    private boolean required;

    @Override
    public void initialize(FlagValidator constraintAnnotation) {
        this.values = constraintAnnotation.value();
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if (integer == null){
            return required ? false : true;
        }
        boolean isValid = false;
        for (int i = 0; i < values.length; i++){
            if (values[i].equals(String.valueOf(integer))){
                isValid = true;
                break;
            }
        }

        return isValid;
    }
}
