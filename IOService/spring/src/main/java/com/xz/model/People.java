package com.xz.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="people")
public class People {
    
    @Id
    @Column(name="ID")
    private int id;
    
    @Column(name="name")
    private String name;
    
    @Column(name="sex")
    private String sex;
    
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
  
    
}