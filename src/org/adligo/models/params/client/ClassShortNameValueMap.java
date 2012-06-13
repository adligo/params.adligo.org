package org.adligo.models.params.client;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.adligo.i.util.client.ClassUtils;
import org.adligo.i.util.client.I_Factory;

public class ClassShortNameValueMap implements I_Factory {

	@Override
	public Object createNew(Object o) {
			if (o == null) {
				return XMLObject.NULL;
			}
			Class<?> cls = o.getClass();
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
			} else if (ClassUtils.getClassName(String.class).equals(clazz)) {
				return ClassForNameValueMap.STRING_SHORT_NAME;
			} else if (ClassUtils.getClassName(Boolean.class).equals(clazz)) {
				return ClassForNameValueMap.BOOLEAN_SHORT_NAME;
			} else if (ClassUtils.getClassName(BigDecimal.class).equals(clazz)) {
				return ClassForNameValueMap.BIG_DECIMAL_SHORT_NAME;
			} else if (ClassUtils.getClassName(BigInteger.class).equals(clazz)) {
				return ClassForNameValueMap.BIG_INTEGER_SHORT_NAME;
			}  
			
			// allow for sub classing to create new types
			return null;
			
		}
}
