package com.kingnod.service;

import java.io.IOError;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingnod.dao.ClassesDao;
import com.kingnod.dao.UserDao;
import com.kingnod.entity.Classes;
import com.kingnod.entity.User;

@Service("userService")
@Transactional(readOnly=true)
public class UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private ClassesDao classesDao;
	public List<User> findAllUser(Long id){
		System.out.println("*****service层**"+userDao);
		return userDao.findAllUser(id);
	}
	@Transactional(readOnly=false)
	public User save(User user){
	 return	userDao.save(user);
	}
	/**
	 * 分页查询
	 * @param sp
	 * @param page
	 * @return
	 */
	public Page<User> pageUser(Specification<User> sp,PageRequest page){
		return userDao.findAll(sp,page);
	}
	@Cacheable(value = "entity:user", key="'cpy:user:' + #id+''")
	public User findOne(Long id){
		System.out.println("*****service层**"+userDao);
		return userDao.findOne(id);
	}
	
	@CachePut(value = "entity:user", key="'cpy:user:' + #id+''")
	//@CacheEvict(value = "entity:user", key="'cpy:user:' + #id+''")
	@Transactional(readOnly=false)
	public User updateOne(Long id){
		User user = userDao.findOne(id);
		user.setName(user.getName()+"_S");
	    user = userDao.save(user);
		return user;
	}
	@Transactional(readOnly=false)
	public Classes saveClasses(){
		Classes c = new Classes();
		List<User> list = (List<User>) userDao.findAll();
		c.setCode("c_1002");
		c.setName("网络102");
		c.setCreateDate(new Date());
		c.setLastUpdateDate(new Date());
		c.setList(list);
	    c=classesDao.save(c);
	    return c;
	}
	//@Transactional(readOnly=false)
	public Classes findOneClasses(Long id){
		Classes c= classesDao.findOne(id);
		//List<User> list = c.getList();
		//System.out.println("*********"+list.size());
		return c;
	}
	@Transactional(readOnly=false)
	public void de(Long id){
		classesDao.delete(id);
	}
}
