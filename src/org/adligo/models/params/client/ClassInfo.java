package org.adligo.models.params.client;

public class ClassInfo {
	private String name;
	private double version;
	
	public ClassInfo(String p_name, double p_version) {
		name = p_name;
		version = p_version;
	}
	
	String getName() {
		return name;
	}
	
	double getVersion() {
		return version;
	}
	
}
