package com.alina.bootrpc.common.core.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum CommonEnum {


	/**
	 * 添加成功!
	 */
	SAVE_SUCCESS(100, "添加成功!"),
	/**
	 * 添加失败 !
	 */
	SAVE_FAIL(-101, "添加失败 !"),
	
	/**
	 * 更新成功!
	 */
	UPDATE_SUCCESS(102, "更新成功!"),
	/**
	 * 更新失败 !
	 */
	UPDATE_FAIL(-102, "更新失败 !"),
	
	/**
	 * 删除成功!
	 */
	DELETE_SUCCESS(103, "删除成功!"),
	/**
	 * 删除失败! 
	 */
	DELETE_FAIL(-103, "删除失败!");
	
	
	/** 枚举值 */
	private Integer state;
	/** 描述 */
	private String message;


	private CommonEnum(Integer state,String message) {
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
	
	public static CommonEnum getEnum(Integer state){
		CommonEnum resultEnum = null;
		CommonEnum[] enumAry = CommonEnum.values();
		for(int i = 0;i<enumAry.length;i++){
			if(enumAry[i].getState() == state){
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}
	
	public static Map<String, Map<String, Object>> toMap() {
		CommonEnum[] ary = CommonEnum.values();
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
		CommonEnum[] ary = CommonEnum.values();
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
	public static CommonEnum valueOf(Integer code) {
		for (CommonEnum status : values()) {
			if (status.state == code) {
				return status;
			}
		}
		throw new IllegalArgumentException("No matching constant for [" + code + "]");
	}

}