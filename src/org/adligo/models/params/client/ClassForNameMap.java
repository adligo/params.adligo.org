package org.adligo.models.params.client;

import org.adligo.i.util.shared.I_Factory;

public class ClassForNameMap implements I_Factory {
	/**
	 * short names for smaller xml files
	 */
	public static final String PARAMS_SHORT_NAME = "Params";
	public static final String PARAM_SHORT_NAME = "Param";
	public static final String XML_OBJECT_SHORT_NAME = "XMLObject";
	
	public Object createNew(Object p) {
		String clazz = (String) p;
		/*
		 * could also use something like this
		 * ClassUtils.getClassName(XMLObject.class).equals(clazz)
		 * but I wanted to shorten some of my xml
		 */
		if (PARAMS_SHORT_NAME.equals(clazz)) {
			return new Params();
		}
		if (PARAM_SHORT_NAME.equals(clazz)) {
			return new Param();
		}
		if (XML_OBJECT_SHORT_NAME.equals(clazz)) {
			return new XMLObject();
		}
		throw new RuntimeException("Couldn't create class " + clazz);
	}

}
