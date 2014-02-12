package org.adligo.models.params.shared;

import org.adligo.i.util.shared.AppenderFactory;
import org.adligo.i.util.shared.I_Appender;

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
			if (isWhitespace(c)) {
				//do nothing
			} else {
				appender.append(c);
			}
		}
		this.name = appender.toString();
		this.value = value;
	}
	/**
	 * Character.isWhitespace(char c) 
	 * is not available in GWT
	 * @param c
	 * @return
	 */
	public static boolean isWhitespace(char c) {
		if (c == ' ') {
			return true;
		}
		//tab
		if (c == '\t') {
			return true;
		}
		//new lines
		if (c == '\n') {
			return true;
		}
		if (c == '\r') {
			return true;
		}
		return false;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
}
