package org.adligo.models.params.client;

public interface I_LimitOffset {
	/**
	 * the starting row
	 * @param limit
	 */
	public void setLimit(int limit);
	/**
	 * the number of rows
	 * @param offset
	 */
	public void setOffset(int offset);
}
