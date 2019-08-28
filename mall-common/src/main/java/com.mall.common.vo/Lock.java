package com.mall.common.vo;

import lombok.Data;

/*
* @Author:wangyongchun
* @Date:2019-01-20 15:34
*@Description redis锁对象
*/
@Data
public class Lock {

    private String name;
    private String value;

    public Lock(){}

    public Lock(String name){
        this.name = name;
    }

    public Lock(String name, String value){
        this.name = name;
        this.value = value;
    }
}
