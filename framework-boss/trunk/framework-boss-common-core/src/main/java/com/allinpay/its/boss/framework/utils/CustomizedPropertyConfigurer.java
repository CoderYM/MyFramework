package com.allinpay.its.boss.framework.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
/**
 * 用户自定义的初始化spring配置文件中定义的变量信息能够在java中方便使用
 * CustomizedPropertyConfigurer.getContextProperty("key的名称")
 * @author YM
 *
 */
public class CustomizedPropertyConfigurer extends PropertyPlaceholderConfigurer {
	private static Map<String, Object> ctxPropertiesMap;  
	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		//load properties to ctxPropertiesMap  
        ctxPropertiesMap = new HashMap<String, Object>();  
        for (Object key : props.keySet()) {  
            String keyStr = key.toString();  
            String value = props.getProperty(keyStr);  
            ctxPropertiesMap.put(keyStr, value);  
        }  
		
	}
	//static method for accessing context properties  
    public static Object getContextProperty(String name) {  
        return ctxPropertiesMap.get(name);  
    }  
}
