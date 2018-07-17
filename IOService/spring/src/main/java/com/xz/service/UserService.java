package com.xz.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("UserService")
public class UserService {
	
	@Resource(name="UserDao")
    private com.xz.dao.UserDao UserDao;
	
	@Transactional
	public boolean longin(String name,String pwd) {
		boolean result=UserDao.longin(name, pwd);
		return result;
	}

}
