package org.adligo.models.params.client;

public class LimitOffset implements I_LimitOffset {
	private Integer limit;
	private Integer offset;
	
	public void setLimit(int p) {
		limit = p;
	}
	
	public void setOffset(int p) {
		offset = p;
	}

	public Integer getLimit() {
		return limit;
	}

	public Integer getOffset() {
		return offset;
	}
	
	
}
