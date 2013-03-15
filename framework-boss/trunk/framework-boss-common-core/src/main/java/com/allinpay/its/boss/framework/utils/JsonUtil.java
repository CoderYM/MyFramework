package com.allinpay.its.boss.framework.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.xwork.StringUtils;

import com.google.gson.Gson;

public class JsonUtil {
	
	public static String toJsonByObj(Object obj){
		Gson gson = new Gson();
		return gson.toJson(obj);
	}

	public static  String toJSONStrByObject(Collection<?> obj){
		Gson gson = new Gson();
		return "{\"result\":\"success\",\"length\":\""
				+ ((null == obj) ? 0 : obj.size())
				+ "\",\"lists\":" + gson.toJson(obj)
				+ "}";
	}
	
	@SuppressWarnings("rawtypes")
	public static  String toJSONMapByObject(Map obj){
		Gson gson = new Gson();
		return "{\"result\":\"success\",\"length\":\""
				+ ((null == obj) ? 0 : obj.size())
				+ "\",\"maps\":" + gson.toJson(obj)
				+ "}";
	}
	
	/**
	 * 针对DWZ的下拉框返回的数据格式
	 * 例如:[["a","b"]]
	 * @param map
	 * @param isAdd 判断是否为添加界面
	 * @return
	 */
	public static String toDWZComboxData(Map<String,String> map,String isSelect){
		StringBuffer comBoxStr=new StringBuffer("");
		if(StringUtils.equalsIgnoreCase(isSelect, "true")){
			
			comBoxStr.append("[[\"\",\"全部\"]");
		}else{
			comBoxStr.append("[[\"\",\"请选择\"]");
		}
		if(map != null && map.size() >0){
			@SuppressWarnings("rawtypes")
			Iterator item = map.entrySet().iterator();
			while(item.hasNext()){
				@SuppressWarnings("unchecked")
				Entry<String, String> entry = (Entry<String, String>)item.next(); 
				comBoxStr.append(",");
				comBoxStr.append("[\""+entry.getKey()+"\",\""+entry.getValue()+"\"]");
				
			}
			
		}
		comBoxStr.append("]");
		return comBoxStr.toString();
	}
	
	
}
