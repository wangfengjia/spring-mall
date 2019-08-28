package com.mall.common.specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 简单条件表达式
 *
 * @author wangyongchun
 * @version 1.0
 * @date 2019/03/02 22:37
 * @param <T>
 */
public class SimpleSpecification<T> implements Specification {

    /**
     * 查询的条件列表，是一组列表
     */
    private List<SpecificationOperator> operators;

    public SimpleSpecification(List<SpecificationOperator> operators){
        this.operators = operators;
    }

    /**
     * 构造查询
     * @param root
     * @param criteriaQuery
     * @param criteriaBuilder
     * @return
     */
    @Override
    public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
        int index = 0;
        //通过resultPredicate来组合多个条件
        Predicate resultPredicate = null;
        for (SpecificationOperator operator : operators){
            if (index++ == 0){
                resultPredicate = generatePredicate(root, criteriaBuilder, operator);
                continue;
            }
            Predicate predicate = generatePredicate(root, criteriaBuilder, operator);
            if (predicate == null){
                continue;
            }
            switch (operator.getJoin()){
                case AND:
                    resultPredicate = criteriaBuilder.and(resultPredicate, predicate);
                    break;
                case OR:
                    resultPredicate = criteriaBuilder.or(resultPredicate, predicate);
                    break;
                default:
            }
        }
        return resultPredicate;
    }

    /**
     * 根据不同的操作符返回特定的查询
     * @param root
     * @param cb
     * @param operator
     * @return
     */
    private Predicate generatePredicate(Root<T> root, CriteriaBuilder cb, SpecificationOperator operator){
        Object value = operator.getValue();
        Predicate predicate = null;
        switch (operator.getOperator()){
            case EQ:
                predicate = cb.equal(root.get(operator.getKey()), value);
                break;
            case NE:
                predicate = cb.notEqual(root.get(operator.getKey()), value);
                break;
            case GE:
                predicate = cb.ge(root.get(operator.getKey()).as(Number.class), (Number) value);
                break;
            case LE:
                predicate = cb.le(root.get(operator.getKey()).as(Number.class), (Number) value);
                break;
            case GT:
                predicate = cb.gt(root.get(operator.getKey()).as(Number.class), (Number) value);
                break;
            case LT:
                predicate = cb.lt(root.get(operator.getKey()).as(Number.class), (Number) value);
                break;
            case IN:
                if (value instanceof Collection){
                    predicate = root.get(operator.getKey()).in((Collection) value);
                }
                break;
            case NOTIN:
                if (value instanceof Collection){
                    predicate = cb.not(root.get(operator.getKey()).in((Collection) value));
                }
                break;
            case LIKEALL:
                predicate = cb.like(root.get(operator.getKey()).as(String.class), "%" + value + "%");
                break;
            case LIKELEFT:
                predicate = cb.like(root.get(operator.getKey()).as(String.class), value + "%");
                break;
            case LIKERIGHT:
                predicate = cb.like(root.get(operator.getKey()).as(String.class), "%" + value);
                break;
            case ISNULL:
                predicate = cb.isNull(root.get(operator.getKey()));
                break;
            case ISNOTNULL:
                predicate = cb.isNotNull(root.get(operator.getKey()));
                break;
            case LESSTHAN:
                if (value instanceof Date){
                    predicate = cb.lessThan(root.get(operator.getKey()), (Date) value);
                }
                break;
            case LESSTHANEQUAL:
                if (value instanceof Date){
                    predicate = cb.lessThanOrEqualTo(root.get(operator.getKey()), (Date) value);
                }
                break;
            case GREATERTHAN:
                if (value instanceof Date){
                    predicate = cb.greaterThan(root.get(operator.getKey()), (Date) value);
                }
                break;
            case GREATERTHANEQUAL:
                if (value instanceof Date){
                    predicate = cb.greaterThanOrEqualTo(root.get(operator.getKey()), (Date) value);
                }
                break;
            case BETWEEN:
                if (value instanceof Date[]){
                    Date[] dateArray = (Date[]) value;
                    predicate = cb.between(root.get(operator.getKey()), dateArray[0], dateArray[1]);
                }
                break;
            default:
                return null;
        }

        return predicate;
    }


}
