package com.mall.common.specification;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * 条件构造器
 * @author wangyongchun
 * @version 1.0
 * @date 2019/03/09 14:15
 * @param <T>
 */
public class SimpleSpecificationBuilder<T> {

    /**
     * 条件列表
     */
    private List<SpecificationOperator> operators;

    public SimpleSpecificationBuilder(){
        operators = new ArrayList<>();
    }

    /**
     * 构造函数，初始化的条件为and
     * @param key 条件的key
     * @param operator key的操作符
     * @param value 条件的值
     */
    public SimpleSpecificationBuilder(String key, SpecificationOperator.Operator operator, Object value){
        List<SpecificationOperator> operators = new ArrayList<>();
        SpecificationOperator op = new SpecificationOperator();
        op.setKey(key);
        op.setOperator(operator);
        op.setValue(value);
        op.setJoin(SpecificationOperator.Join.AND);
        operators.add(op);
    }

    /**
     * 完成条件的添加
     * @param join 条件连接符
     * @param key 条件的key
     * @param operator 条件的操作符
     * @param value 条件的值
     * @return
     */
    public SimpleSpecificationBuilder<T> add(
            SpecificationOperator.Join join,
            String key,
            SpecificationOperator.Operator operator,
            Object value
    ){
        SpecificationOperator op = new SpecificationOperator();
        op.setJoin(join);
        op.setKey(key);
        op.setOperator(operator);
        op.setValue(value);
        operators.add(op);
        return this;
    }

    /**
     * 添加and条件
     * @param key 条件的key
     * @param operator 条件
     * @param value 条件的值
     * @return
     */
    public SimpleSpecificationBuilder<T> addAnd(String key, SpecificationOperator.Operator operator, Object value){
        return this.add(SpecificationOperator.Join.AND, key, operator, value);
    }

    /**
     * 添加or条件
     * @param key 条件的key
     * @param operator 条件
     * @param value 条件的值
     * @return
     */
    public SimpleSpecificationBuilder<T> addOr(String key, SpecificationOperator.Operator operator, Object value){
        return this.add(SpecificationOperator.Join.OR, key, operator, value);
    }


    /**
     * 构建条件表达式
     * @return
     */
    public Specification<T> generateSpecification(){
        return new SimpleSpecification<>(operators);
    }
}
