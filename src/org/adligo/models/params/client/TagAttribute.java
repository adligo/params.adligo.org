package org.adligo.models.params.client;

import org.adligo.i.util.client.AppenderFactory;
import org.adligo.i.util.client.I_Appender;

public class TagAttribute {
	private String name;
	/**
	 * note this value has NOT been unescaped from xml
	 */
	private String value;
	
	public TagAttribute(String name, String value) {
		char [] chars = name.toCharArray();
		I_Appender appender = AppenderFactory.create();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (Character.isWhitespace(c)) {
				//do nothing
			} else {
				appender.append(c);
			}
		}
		this.name = appender.toString();
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
}
