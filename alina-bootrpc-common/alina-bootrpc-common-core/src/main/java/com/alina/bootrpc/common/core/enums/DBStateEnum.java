package com.alina.bootrpc.common.core.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName:  DBStateEnum   
 * @Description: 数据库数据状态枚举   
 * @author: 湖南无为网电子商务有限公司  (Dieudonne)
 * @date:   2018年4月24日 上午9:30:16   
 *     
 * @Copyright: 2018 http://www.0731333.com Inc. All rights reserved. 
 * 注意：本内容仅限于湖南无为网电子商务有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public enum DBStateEnum {

	
	
	/**
	 * 1:启用 
	 */
	ENABLED(1, "启用"),
	/**
	 *  0:停用
	 */
	DISABLED(0, "停用");
	
	
	/** 枚举值 */
	private Integer state;
	/** 描述 */
	private String message;


	private DBStateEnum(Integer state,String message) {
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
	
	public static DBStateEnum getEnum(Integer state){
		DBStateEnum resultEnum = null;
		DBStateEnum[] enumAry = DBStateEnum.values();
		for(int i = 0;i<enumAry.length;i++){
			if(enumAry[i].getState() == state){
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}
	
	public static Map<String, Map<String, Object>> toMap() {
		DBStateEnum[] ary = DBStateEnum.values();
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
		DBStateEnum[] ary = DBStateEnum.values();
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
	public static DBStateEnum valueOf(Integer code) {
		for (DBStateEnum status : values()) {
			if (status.state == code) {
				return status;
			}
		}
		throw new IllegalArgumentException("No matching constant for [" + code + "]");
	}

}