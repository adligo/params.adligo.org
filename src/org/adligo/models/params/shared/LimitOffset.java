package org.adligo.models.params.shared;

public class LimitOffset implements I_LimitOffset {
	private int limit = -1;
	private int offset = -1;
	
	public void setLimit(int p) {
		limit = p;
	}
	
	public void setOffset(int p) {
		offset = p;
	}

	public int getLimit() {
		return limit;
	}

	public int getOffset() {
		return offset;
	}
	
	
}
