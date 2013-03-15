package com.allinpay.its.boss.framework.enums;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 操作员状态
 * @author YM
 *
 */
public enum EUserInfState {
	/**
	 * 正常.
	 */
	NORMAL(0),

	/**
	 * 禁用.
	 */
	FORBIDDEN(1);

	int type;

	EUserInfState(int type) {
		this.type = type;
	}

	public int getValue() {
		return type;
	}

	public static int getValue(EUserInfState type) {
		return type.getValue();
	}

	@SuppressWarnings("unchecked")
	public static List<EUserInfState> getAllTypes() {
		EUserInfState[] types = EUserInfState.values();
		List result = new ArrayList();
		for (int i = 0; i < types.length; i++) {
			result.add(types[i]);
		}
		return result;
	}

	private static LinkedHashMap<Integer, String> codes = new LinkedHashMap<Integer, String>();
	static {
		codes.put(NORMAL.getValue(), "正常");
		codes.put(FORBIDDEN.getValue(), "禁用");
	}

	/**
	 * 返回描述.
	 * 
	 * @param orderState
	 * @return
	 */
	public static String getDesc(int state) {
		return codes.get(state);
	}
	
	/**
	 * 为前台界面下拉列表框返回代码与描述对应集合
	 * 
	 * @return Map
	 */
	public static LinkedHashMap<Integer, String> getCodes() {
		return codes;
	}
}
