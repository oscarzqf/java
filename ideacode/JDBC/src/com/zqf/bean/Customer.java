package com.zqf.bean;

import java.sql.Date;

/**
 * @author oscarzqf
 * @description
 *          ORM编程思想(object relational mapping):
 *          一个数据表对应一个java类
 *          表中的一条记录对应java类的一个对象
 *          表中一个字段对应java类中的一个属性
 * @create 2021-09-19-19:59
 */
public class Customer{
    private int id;
    private String name;
    private String email;
    private Date birth;

    public Customer(){
    }

    public Customer(int id, String name, String email, Date birth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birth = birth;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                '}';
    }
}
