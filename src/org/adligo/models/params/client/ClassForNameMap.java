package org.adligo.models.params.client;

import org.adligo.i.util.client.ClassUtils;
import org.adligo.i.util.client.I_InstanceFactory;

public class ClassForNameMap implements I_InstanceFactory {

	public Object createInstance(String clazz) {
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
