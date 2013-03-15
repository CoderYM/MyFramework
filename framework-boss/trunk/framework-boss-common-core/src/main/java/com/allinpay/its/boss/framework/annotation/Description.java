package com.allinpay.its.boss.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来写描述信息的自定义注解
 * @author YM
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE,ElementType.FIELD})
public @interface Description {
	
	public String value();

}
