package com.ugfind.aop;

import org.apache.log4j.Logger;
import org.springframework.aop.ThrowsAdvice;

/*
 * 这是一个异常处理记录日志的aop  实现接口来实现
 */
public class ThrowsLogAop implements ThrowsAdvice{
    /** 
     * Owner  
     * 参数解释 Method method 执行的方法  
     * Object[] args 方法参数 
     *  Object target 代理的目标对象 
     * Throwable throwable 产生的异常  
     * Jan 18, 2010 3:21:46 PM 
     */  
	private  Logger logger = Logger.getLogger(ThrowsLogAop.class);

     public void afterThrowing(Exception e) throws Throwable {
    	 	//记录日志
    	 	logger.error("错误日志:"+e);
     }

}
