package com.kingnod.service.impl;     
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.kingnod.service.AccountService;
    
/**    
 * AccountService的实现类    
 * zhenghongwei  
 */ 
@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Override
	public void save(String userName, String password) {
		System.out.println("开始保存数据");
		
	}     
         
 
}  