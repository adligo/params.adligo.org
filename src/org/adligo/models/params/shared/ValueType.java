package org.adligo.models.params.shared;

public class ValueType {
	private short id;
	private String name;
	
	protected ValueType(short pid, String pname) {
		id = pid;
		name = pname;
	}
	
	public short getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean equals(Object other) {
		if (other.getClass() != ValueType.class) {
			return false;
		}
		ValueType vtOther = (ValueType) other;
		if (id != vtOther.getId()) {
			return false;
		}
		return true;
	}
	
	public String toString() {
		return "ValueType [" + name + "/" + id + "]";
	}
}
