package org.adligo.models.params.client;

public class LimitOffset implements I_LimitOffset {
	private int limit;
	private int offset;
	
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
