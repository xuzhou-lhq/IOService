package com.xz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	    
	    @Id
	    @Column(name="ID")
	    private int id;
	    
	    @Column(name="name")
	    private String name;
	    
	    @Column(name="pwd")
	    private String pwd;
	    
	    public void getName(String name) {
	        this.name = name;
	    }
	    public String getpwd() {
	        return pwd;
	    }
}