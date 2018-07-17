package com.xz.dao;


import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.xz.model.User;

@Repository("UserDao")
public class UserDao {   
	
	    @Resource(name="sessionFactory")
	    private SessionFactory sessionFactory;
	    
	    /**
	     * 保存对象
	     * @param p
	     * @return
	     */
	    public void save(User user){
	        sessionFactory.getCurrentSession().save(user);
	    }
	    
	    public boolean longin(String name,String pwd){
	    	Query query =sessionFactory.getCurrentSession().createQuery("from User where name= ? and pwd= ? ");
	    	query.setParameter(0, name);
            query.setParameter(1, pwd);
            boolean result=false;
            if(query.list().size()>0)
            {
            	result=true;
            }else {
            	result=false;
            }
            return result;
	    }
}
