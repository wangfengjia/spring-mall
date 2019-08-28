package com.mall.common.specification;

import lombok.Data;

/**
 * 拼接sql的操作符类
 * created by wangyongchun 2019/02/24 22:07
 */
@Data
public class SpecificationOperator {

    /**
     * 操作符的key，例如查询时的id
     */
    private String key;

    /**
     * 操作符对应的值, 具体要查询的值
     */
    private Object value;

    /**
     * 自己定义的一组操作符，用来方便查询
     */
    private Operator operator;

    /**
     * 连接的方式 AND或者OR
     */
    private Join join;


    public enum Operator{
        EQ, NE, GE, LE, GT, LT, LIKELEFT, LIKERIGHT, LIKEALL, ISNULL, ISNOTNULL,
        IN, NOTIN, LESSTHAN, LESSTHANEQUAL, GREATERTHAN, GREATERTHANEQUAL, BETWEEN
    }

    public enum Join{
        AND, OR
    }
}
