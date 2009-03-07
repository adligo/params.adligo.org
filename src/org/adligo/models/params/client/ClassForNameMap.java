package org.adligo.models.params.client;

import org.adligo.i.util.client.ClassUtils;
import org.adligo.i.util.client.I_Factory;

public class ClassForNameMap implements I_Factory {

	public Object createNew(Object p) {
		String clazz = (String) p;
		if (ClassUtils.getClassName(Params.class).equals(clazz)) {
			return new Params();
		}
		if (ClassUtils.getClassName(Param.class).equals(clazz)) {
			return new Param();
		}
		if (ClassUtils.getClassName(XMLObject.class).equals(clazz)) {
			return new XMLObject();
		}
		throw new RuntimeException("Couldn't create class " + clazz);
	}

}
