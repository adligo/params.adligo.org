package org.adligo.models.params.shared;

public class ClassInfo {
	private String name;
	private double version;
	
	public ClassInfo(String p_name, double p_version) {
		name = p_name;
		version = p_version;
	}
	
	public String getName() {
		return name;
	}
	
	public double getVersion() {
		return version;
	}
	
}
