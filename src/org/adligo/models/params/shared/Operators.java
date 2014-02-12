package org.adligo.models.params.shared;


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

	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		// this next line is the reason for this
		// whole class why is hashCode not implemented 
		// in the String[] class?
		result = prime * result + values.hashCode();
		for (int i =0; i < values.length; i++) {
			if (values[i] != null) {
				result = result + values[i].hashCode();
			}
		}
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operators other = (Operators) obj;
		if (values.length != other.values.length) {
			return false;
		}
		for (int i =0; i < values.length; i++) {
			if (values[i] != null) {
				if (other.values[i] == null) {
					return false;
				} else if ( !values[i].equals(other.values[i])) {
					return false;
				}
			} else if (other.values[i] != null) {
				return false;
			} 
		}
		return true;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("operators=\"");
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				if (i != 0) {
					sb.append(",");
				}
				sb.append(values[i]);
			}
		}
		sb.append("\"");
		return sb.toString();
	}
	
}
