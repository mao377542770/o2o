package com.ugfind.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 异常日志捕获aop   
 * @author Administrator
 *
 */
public class ThrowLogAop {
		private static Logger logger = Logger.getLogger(ThrowLogAop.class);
		
		//主要用到 切入的参数
		 public void invoke(ProceedingJoinPoint joinPoint) throws Throwable {     
	         StringBuffer sb = new StringBuffer();   
	        try{
	            joinPoint.proceed();      //执行
	        }catch(Exception e){     
	            sb.append("开始方法："+joinPoint.getTarget().getClass() + "." + joinPoint.getSignature().getName()+ "()  ");   
	            sb.append("错误信息如下："+e);   
	            logger.error(sb.toString());
	            throw e;
	        }   
	    }     
}
