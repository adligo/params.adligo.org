package org.adligo.models.params.client;

import org.adligo.i.util.client.StringUtils;

public class TagMutant {
	private Integer start;
	private String name;
	private Integer end;
	private boolean selfEnding = false;
	
	public TagMutant() {}
	
	public TagMutant(TagMutant other) {
		setStart(other.start);
		setName(other.name);
		setEnd(other.end);
		selfEnding = other.selfEnding;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if (StringUtils.isEmpty(name)) {
			throw new NullPointerException("Name may not be null or empty");
		}
		this.name = name;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public boolean isSelfEnding() {
		return selfEnding;
	}
	public void setSelfEnding(boolean selfEnding) {
		this.selfEnding = selfEnding;
	}

	public String toString() {
		return toString(TagMutant.class);
	}
	
	public String toString(Class<?> clazz) {
		return "" + clazz.getSimpleName() + " [start=" + start + ", name=" + name + ", end=" + end
				+ ", selfEnding=" + selfEnding + "]";
	}
	
}
