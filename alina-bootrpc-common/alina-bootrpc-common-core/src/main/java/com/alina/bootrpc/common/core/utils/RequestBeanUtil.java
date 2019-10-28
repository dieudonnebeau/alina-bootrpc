package com.alina.bootrpc.common.core.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/10/28 9:58
 * @description：接口或页面参数对象
 * @modified By：
 * @version:     1.0
 */
public class RequestBeanUtil extends HashMap implements Map {

	private static final long serialVersionUID = 1L;

	Map map = null;
	HttpServletRequest request;

	public RequestBeanUtil(HttpServletRequest request) {
		this.request = request;
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap();
		Iterator entries = properties.entrySet().iterator();
		Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		map = returnMap;
	}

	public RequestBeanUtil() {
		map = new HashMap();
	}

	@Override
	public Object get(Object key) {
		Object obj = null;
		if (map.get(key) instanceof Object[]) {
			Object[] arr = (Object[]) map.get(key);
			obj = request == null ? arr : (request.getParameter((String) key) == null ? arr : arr[0]);
		} else {
			obj = map.get(key);
		}
		return obj;
	}

	public String getString(Object key) {
		return String.valueOf(get(key));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key, Object value) {
		return map.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return map.containsValue(value);
	}

	@Override
	public Set entrySet() {
		// TODO Auto-generated method stub
		return map.entrySet();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return map.isEmpty();
	}

	@Override
	public Set keySet() {
		// TODO Auto-generated method stub
		return map.keySet();
	}

	@Override
	public void putAll(Map t) {
		// TODO Auto-generated method stub
		map.putAll(t);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return map.size();
	}

	@Override
	public Collection values() {
		// TODO Auto-generated method stub
		return map.values();
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Integer getParamToInt(String field, int defaultValue) {
		if (BlankUtil.isBlank(getString(field))) {
			return defaultValue;
		} else {
			try {

				return Integer.valueOf(getString(field));

			} catch (Exception e) {
				return defaultValue;
			}

		}

	}

	public Long getParamToLong(String field, long defaultValue) {
		if (BlankUtil.isBlank(getString(field))) {
			return defaultValue;
		} else {
			try {
				return Long.valueOf(getString(field));
			} catch (Exception e) {
				return defaultValue;
			}

		}
	}

	public Integer getParamToInt(String field) {
		if (BlankUtil.isBlank(getString(field))) {
			return null;
		} else {
			try {
				return Integer.valueOf(getString(field));
			} catch (Exception e) {
				return null;
			}

		}

	}

	public Long getParamToLong(String field) {
		if (BlankUtil.isBlank(getString(field))) {
			return null;
		} else {
			try {
				return Long.valueOf(getString(field));
			} catch (Exception e) {
				return null;
			}
		}
	}

	public Double getParamToDouble(String field) {
		if (BlankUtil.isBlank(getString(field))) {
			return null;
		} else {
			try {
				return Double.valueOf(getString(field));
			} catch (Exception e) {
				return null;
			}

		}
	}

	public static void main(String[] args) {
		RequestBeanUtil data = new RequestBeanUtil();
		data.put("state", "121.12121");
		System.out.println(data.getParamToDouble("state"));
	}

}
