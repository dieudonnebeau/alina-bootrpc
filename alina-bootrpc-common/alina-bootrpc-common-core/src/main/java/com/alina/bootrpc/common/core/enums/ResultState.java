package com.alina.bootrpc.common.core.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ResultState {


	/**
	 * 成功
	 */
	SUCCESS(1, "SUCCESS"),
	/**
	 * 失败  
	 */
	FAIL(0, "FAIL"),
	
	/**
	 * 系统内部异常或维护中
	 */
	SYSTEM_EXCEPTION(500,"系统内部异常或维护中");
	
	/** 枚举值 */
	private Integer state;
	/** 描述 */
	private String message;


	private ResultState(Integer state,String message) {
		this.state = state;
		this.message = message;
	}

	public int getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setDesc(String message) {
		this.message = message;
	}
	
	public static ResultState getEnum(Integer state){
		ResultState resultEnum = null;
		ResultState[] enumAry = ResultState.values();
		for(int i = 0;i<enumAry.length;i++){
			if(enumAry[i].getState() == state){
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}
	
	public static Map<String, Map<String, Object>> toMap() {
		ResultState[] ary = ResultState.values();
		Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
		for (int num = 0; num < ary.length; num++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String key = String.valueOf(getEnum(ary[num].getState()));
			map.put("state", String.valueOf(ary[num].getState()));
			map.put("message", ary[num].getMessage());
			enumMap.put(key, map);
		}
		return enumMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toList(){
		ResultState[] ary = ResultState.values();
		List list = new ArrayList();
		for(int i=0;i<ary.length;i++){
			Map<String,String> map = new HashMap<String,String>();
			map.put("state",String.valueOf(ary[i].getState()));
			map.put("message", ary[i].getMessage());
			list.add(map);
		}
		return list;
	}
	
	/**
	 * Return the enum constant of this type with the specified numeric value.
	 * @param statusCode the numeric value of the enum to be returned
	 * @return the enum constant with the specified numeric value
	 * @throws IllegalArgumentException if this enum has no constant for the specified numeric value
	 */
	public static ResultState valueOf(Integer code) {
		for (ResultState status : values()) {
			if (status.state == code) {
				return status;
			}
		}
		throw new IllegalArgumentException("No matching constant for [" + code + "]");
	}

}