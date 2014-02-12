package org.adligo.models.params.client;

import java.util.Date;

import org.adligo.i.util.shared.ClassUtils;

public class ClassShortNameValueMap {

	/**
	 * Note no generics or annotations for jme compatibility
	 */
	public String getClassFor(Object o, ValueType vt) {
			if (o == null) {
				return XMLObject.NULL;
			}
			Class cls = o.getClass();
			String clazz = ClassUtils.getClassName(cls);
			if (ClassUtils.getClassName(Integer.class).equals(clazz)) {
				return ClassForNameValueMap.INTEGER_SHORT_NAME;
			} else if (ClassUtils.getClassName(Short.class).equals(clazz)) {
				return ClassForNameValueMap.SHORT_SHORT_NAME;
			} else if (ClassUtils.getClassName(Float.class).equals(clazz)) {
				return ClassForNameValueMap.FLOAT_SHORT_NAME;
			} else if (ClassUtils.getClassName(Double.class).equals(clazz)) {
				return ClassForNameValueMap.DOUBLE_SHORT_NAME;
			} else if (ClassUtils.getClassName(Long.class).equals(clazz)) {
				return ClassForNameValueMap.LONG_SHORT_NAME;
			} else if (ClassUtils.getClassName(Date.class).equals(clazz)) {
				return ClassForNameValueMap.DATE_SHORT_NAME;
			} else if (ClassUtils.getClassName(Boolean.class).equals(clazz)) {
				return ClassForNameValueMap.BOOLEAN_SHORT_NAME;
			} else {
				if (vt == ValueTypes.BIG_DECIMAL) {
					return ClassForNameValueMap.BIG_DECIMAL_SHORT_NAME;
				} else if (vt == ValueTypes.BIG_INTEGER) {
					return ClassForNameValueMap.BIG_INTEGER_SHORT_NAME;
				} else {
					return ClassForNameValueMap.STRING_SHORT_NAME;
				} 
			} 
		}
}
