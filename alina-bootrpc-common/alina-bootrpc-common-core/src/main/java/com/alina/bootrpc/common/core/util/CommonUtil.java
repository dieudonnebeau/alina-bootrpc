package com.alina.bootrpc.common.core.util;

import com.alina.bootrpc.common.core.utils.BlankUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * 
 * @ClassName: AppUtil
 * @Description: 参数工具
 * @author: 湖南无为网电子商务有限公司 (Dieudonne)
 * @date: 2018年4月20日 下午4:52:38
 * 
 * @Copyright: 2018 http://www.0731333.com Inc. All rights reserved.
 *             注意：本内容仅限于湖南无为网电子商务有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public final class CommonUtil {
	/**
	 * 比较两个实体类属性值是否相等
	 * 
	 * @param source
	 * @param target
	 * @return
	 * @throws Exception
	 */
	public static final boolean entityIsEquals(Object source, Object target) throws Exception {
		if (source == null || target == null) {
			return false;
		}
		boolean ret = true;
		Class<?> srcClass = source.getClass();
		Field[] fields = srcClass.getDeclaredFields();
		String nameKey = null;
		String srcValue = null;
		String tarValue = null;
		for (Field field : fields) {
			nameKey = field.getName();
			srcValue = getClassValue(source, nameKey) == null ? "" : getClassValue(source, nameKey).toString();
			tarValue = getClassValue(target, nameKey) == null ? "" : getClassValue(target, nameKey).toString();
			if (!srcValue.equals(tarValue)) {
				ret = false;
				break;
			}
		}
		return ret;
	}

	/**
	 * 根据字段名称取值
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws Exception
	 */
	public static final Object getClassValue(Object obj, String fieldName) throws Exception {
		@SuppressWarnings("rawtypes")
		Class beanClass = obj.getClass();
		Method[] ms = beanClass.getMethods();
		for (Method method : ms) {
			// 非get方法不取
			if (method.getName().startsWith("get")) {
				Object objValue = method.invoke(obj, new Object[] {});
				if (null != objValue) {
					if (method.getName().toUpperCase().equals(fieldName.toUpperCase())
							|| method.getName().substring(3).toUpperCase().equals(fieldName.toUpperCase())) {
						return objValue;
					}
				}
			}
		}
		return null;
	}

	/**
	 * 生成N位随机数
	 * 
	 * @param length
	 * @return
	 */
	public static final String getRandomString(int length) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; ++i) {
			int number = random.nextInt(3);
			long result = 0;

			switch (number) {
			case 0:
				result = Math.round(Math.random() * 25 + 65);
				sb.append(String.valueOf((char) result));
				break;
			case 1:
				result = Math.round(Math.random() * 25 + 97);
				sb.append(String.valueOf((char) result));
				break;
			case 2:
				sb.append(String.valueOf(new Random().nextInt(10)));
				break;
			}
		}
		return sb.toString();
	}

	/**
	 * 生成N位纯数字验证码
	 * 
	 * @return
	 */
	public static final String getVerificationCode(int n) {
		final Random random = new Random();
		String verificationCode = "";
		for (int i = 0; i < n; i++) {
			verificationCode += random.nextInt(10);
		}
		return verificationCode;
	}

	/**
	 * 生产注单号
	 * 
	 * @return
	 */
	public static final String getOrdercode() {
		String time = String.valueOf(System.currentTimeMillis());
		return time + time.subSequence(2, 6) + getVerificationCode(1);
	}

	/**
	 * 生成32位UUID
	 * 
	 * @return
	 */
	public static final String getUuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 相除
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static final String divide(Object num1, Object num2) {
		Float num = Float.parseFloat(num1.toString());
		BigDecimal b1 = new BigDecimal(num);
		BigDecimal b2 = new BigDecimal(num2.toString());
		Double result = b1.divide(b2, 2, RoundingMode.HALF_UP).doubleValue();
		return new DecimalFormat("#,##0.00").format(result);
	}

	public static final String formatValue(double source) {
		return new DecimalFormat("###0.00").format(source);
	}

	public static final Double formatDouble(double source) {
		return Double.parseDouble(formatValue(source));
	}

	/**
	 * 
	 * @Title: toParamsMap @Description: 两个数组对象转Map @param: @param
	 * names @param: @param values @param: @return @return:
	 * Map<String,Object> @throws
	 */
	public static  final Map<String, Object> toParamsMap(String[] names, Object[] values) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (!BlankUtil.isBlank(names) && !BlankUtil.isBlank(values)) {
			if (names.length == values.length) {
				int length = names.length;
				for (int i = 0; i < length; i++) {
					params.put(names[i], values[i]);
				}
			}
		}
		return params;
	}

	// public static void main(String[] args) {
	// Set<String> sets = new HashSet<String>();
	// for(int i = 0; i < 1000000; i ++){
	// sets.add(getRandomString(20));
	// }
	// System.out.println(sets.size());
	// System.out.println(getRandomString(20));
	// AuthOperation op1 = new AuthOperation();
	// op1.setOpCode("002");
	// op1.setOpid(1);
	//
	// AuthOperation op2 = new AuthOperation();
	// op2.setOpCode("002");
	// op2.setOpid(1);
	// try {
	// System.out.println(entityIsEquals(op1, op2));
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// System.out.println(divide(10, "0.451215"));
	// }

}
