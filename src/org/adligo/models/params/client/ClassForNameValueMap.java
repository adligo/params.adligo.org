package org.adligo.models.params.client;

import java.util.Date;

import org.adligo.i.log.client.Log;
import org.adligo.i.log.client.LogFactory;
import org.adligo.i.util.client.ClassUtils;
import org.adligo.i.util.client.I_Factory;

public class ClassForNameValueMap implements I_Factory {
	private static final Log log = LogFactory
			.getLog(ClassForNameValueMap.class);

	@SuppressWarnings("deprecation")
	@Override
	public Object createNew(Object p) {
		ValueConstructionParams in = (ValueConstructionParams) p;
		String clazz = in.getClazz();
		String content = in.getContent();

		Object toRet = null;
		if (XMLObject.NULL.equals(content)) {
			return null;
		} else if (ClassUtils.getClassName(Integer.class).equals(clazz)) {
			toRet = new Integer(content);
		} else if (ClassUtils.getClassName(Double.class).equals(clazz)) {
			toRet = new Double(content);
		} else if (ClassUtils.getClassName(Float.class).equals(clazz)) {
			toRet = new Float(content);
		} else if (ClassUtils.getClassName(Short.class).equals(clazz)) {
			toRet = new Short(content);
		} else if (ClassUtils.getClassName(Long.class).equals(clazz)) {
			toRet = new Long(content);
		} else if (ClassUtils.getClassName(Date.class).equals(clazz)) {
			// yes use the depricated method, since
			// GWT (most of the reason for this class anyway
			// has different date parsers this is still the best way!
			toRet = new Date(content);
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
