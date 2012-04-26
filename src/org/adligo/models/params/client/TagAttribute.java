package org.adligo.models.params.client;

public class TagAttribute {
	private String name;
	/**
	 * note this value has NOT been unescaped from xml
	 */
	private String value;
	
	public TagAttribute(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
}
