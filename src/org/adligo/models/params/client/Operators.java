package org.adligo.models.params.client;

import java.util.Arrays;

public class Operators implements I_Operators {
	private String [] values;
	
	public Operators(String p) {
		values = new String[] {p};
	}
	
	public Operators(String [] p) {
		values = p;
	}
	
	public String [] getValues() {
		return values;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		// this next line is the reason for this
		// whole class why is hashCode not implemented 
		// in the String[] class?
		result = prime * result + Arrays.hashCode(values);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operators other = (Operators) obj;
		if (!Arrays.equals(values, other.values))
			return false;
		return true;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Operators [values=[");
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				if (i != 0) {
					sb.append(",");
				}
				sb.append(values[i]);
			}
		}
		sb.append("]]");
		return sb.toString();
	}
	
}
