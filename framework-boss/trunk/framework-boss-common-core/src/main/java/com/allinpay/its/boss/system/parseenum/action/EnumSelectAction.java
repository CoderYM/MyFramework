package com.allinpay.its.boss.system.parseenum.action;


import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allinpay.its.boss.framework.utils.JsonUtil;
import com.allinpay.its.boss.system.BaseAction;
import com.allinpay.its.boss.system.parseenum.model.EnumModel;



/**
 * @date 2013-02-22
 * @author zhujf1
 *
 */
@Controller
@RequestMapping(value="/enumJson")
public class EnumSelectAction extends BaseAction{
	
	
	private Map<String,String> codes;
	
	@RequestMapping("")
	@ResponseBody
	public String getJsonData(EnumModel enumModel) throws Exception {
		
		Map<String,String> rstMap;
		
		rstMap = getEnumCodes(enumModel.getCvalue(),enumModel);
		//filter select data according include and exclude
		codes = new LinkedHashMap<String,String>();
		if(enumModel.getInclude() != null) {
			for(String key:enumModel.getInclude().split(",")) {
				key = key.trim();
				codes.put(key, (String)rstMap.get(key));
			}
		} else {
			Iterator<String> i = rstMap.keySet().iterator();
			List<String> exList = null;
			if(enumModel.getExclude()!=null) {
				exList = Arrays.asList(enumModel.getExclude().split(","));
			}
			while(i.hasNext()) {
				String key = i.next();
				if(exList == null || !exList.contains(key)) {
					codes.put(key, (String)rstMap.get(key));
				}
			}
		}
		return JsonUtil.toDWZComboxData(codes,enumModel.getIsSelect());
	}
	
	
	@RequestMapping("/getEnumMapJson")
	@ResponseBody
	public String getMapEnumJsonStyle(EnumModel enumModel){
		
		
		return JsonUtil.toJSONMapByObject(getEnumCodes(enumModel.getCvalue(),enumModel));
	}
	
	
	/**
	 * @return Map
	 */
	private Map<String, String> getEnumCodes(String cvalue, EnumModel enumModel) {
		Map<String, String> codes = new LinkedHashMap<String, String>();
		if (cvalue == null || cvalue.trim().length() == 0) {
			return codes;
		}
		// 动态调用指定Java枚举类的固定方法：getDesc() 以获取代码与中文描述对应集合 
		try {
			Class[] parameterTypes = null;
			// 指定的Java枚举类必须在指定目录下，如果有新的路径可将此处代码稍做改动。
			Class enumClass = Class.forName("com.allinpay.its.util.metadata." + cvalue);
			Method getDesc = null;
			// 如果前台传回指定的方法名则不取默认方法：getCodes()
			if (enumModel.getMethod() != null && enumModel.getMethod().trim().length() > 0) {
				getDesc = enumClass.getMethod(enumModel.getMethod(), parameterTypes);
			} else {
				getDesc = enumClass.getMethod("getCodes", parameterTypes);
			}
			Map<String,String> result=new LinkedHashMap<String,String>();
            Map tempMap=(Map)getDesc.invoke(null,new Object[]{});
            Iterator it = tempMap.keySet().iterator();
            	while(it.hasNext()){
            		Object key = it.next();
            		Object value = tempMap.get(key);
            		result.put(String.valueOf(key), String.valueOf(value));
            }
			return result;
		} catch (ClassNotFoundException e) {
			return codes;
		} catch (NoSuchMethodException e) {
			return codes;
		} catch (Exception e) {
			return codes;
		}
	}


}
