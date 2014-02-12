package org.adligo.models.params.client;

import java.util.Date;

import org.adligo.i.log.shared.Log;
import org.adligo.i.log.shared.LogFactory;
import org.adligo.i.util.shared.I_Factory;

public class ClassForNameValueMap implements I_Factory {
	private static final Log log = LogFactory
			.getLog(ClassForNameValueMap.class);

	public static final String INTEGER_SHORT_NAME = "Integer";
	public static final String DOUBLE_SHORT_NAME = "Double";
	public static final String FLOAT_SHORT_NAME = "Float";
	public static final String SHORT_SHORT_NAME = "Short";
	public static final String LONG_SHORT_NAME = "Long";
	public static final String DATE_SHORT_NAME = "Date";
	public static final String STRING_SHORT_NAME = "String";
	public static final String BOOLEAN_SHORT_NAME = "Boolean";
	public static final String BIG_DECIMAL_SHORT_NAME = "BigDecimal";
	public static final String BIG_INTEGER_SHORT_NAME = "BigInteger";
	
	public Object createNew(Object p) {
		ValueConstructionParams in = (ValueConstructionParams) p;
		String clazz = in.getClazz();
		String content = in.getContent();

		Object toRet = null;
		if (XMLObject.NULL.equals(content)) {
			return null;
		} else if (INTEGER_SHORT_NAME.equals(clazz)) {
			toRet = new Integer(Integer.parseInt(content));
		} else if (DOUBLE_SHORT_NAME.equals(clazz)) {
			toRet = new Double(Double.parseDouble(content));
		} else if (FLOAT_SHORT_NAME.equals(clazz)) {
			toRet = new Float(Float.parseFloat(content));
		} else if (SHORT_SHORT_NAME.equals(clazz)) {
			toRet = new Short(Short.parseShort(content));
		} else if (LONG_SHORT_NAME.equals(clazz)) {
			toRet = new Long(Long.parseLong(content));
		} else if (DATE_SHORT_NAME.equals(clazz)) {
			//write date and read dates as longs,
			// for better (easier interop with other languages)
			// which is the whole reason for xml serlizaion 
			// in the first place
			// there are (2^63) milliseconds in a long
			// or 292 277 266 years 
		    // So we can represent
			// the years  January 1, 1970
			// 292,275,296 BC (negative numbers) - 
			// 292,279,236 AD 
			// The Triassic period (about 230 million years ago)
			// is about as far back as things go, unless your
			// a geologist
			long timestamp = Long.parseLong(content);
			toRet = new Date(timestamp);
		} else if (BOOLEAN_SHORT_NAME.equals(clazz)){
			if ("true".equals(content)) {
				return Boolean.TRUE;
			} else {
				return Boolean.FALSE;
			}
		} else if (BIG_DECIMAL_SHORT_NAME.equals(clazz)){
			toRet = content;
		} else if (BIG_INTEGER_SHORT_NAME.equals(clazz)){
			toRet = content;
		} else {
			// defaults to String
			toRet = content;
		}
		if (log.isDebugEnabled()) {
			log.debug("returning '" + toRet + "'");
			if (toRet != null) {
				log.debug(" a " + toRet.getClass());
			}
		}
		return toRet;

	}
	
	

}
