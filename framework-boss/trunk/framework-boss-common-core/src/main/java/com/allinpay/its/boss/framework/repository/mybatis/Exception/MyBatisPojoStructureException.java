package com.allinpay.its.boss.framework.repository.mybatis.Exception;

/**
 * 自定义的关于Mybatis基础POJO的异常
 * @author YM
 *
 */
public class MyBatisPojoStructureException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;  
	  
    public MyBatisPojoStructureException(String msg) {  
        super(msg);  
    }  
      
    public MyBatisPojoStructureException(String msg,Throwable e) {  
        super(msg,e);  
    }  

}
