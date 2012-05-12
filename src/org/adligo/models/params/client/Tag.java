package org.adligo.models.params.client;

public class Tag {
	private TagMutant mutant;
	
	Tag(TagMutant other) {
		mutant = new TagMutant(other);
	}

	public Integer getStart() {
		return mutant.getStart();
	}

	public String getName() {
		return mutant.getName();
	}

	public Integer getEnd() {
		return mutant.getEnd();
	}

	public boolean isSelfEnding() {
		return mutant.isSelfEnding();
	}
	
	public String toString() {
		return mutant.toString(Tag.class);
	}
}
