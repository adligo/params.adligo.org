package org.adligo.models.params.shared;

public class Tag {
	private TagMutant mutant;
	
	Tag(TagMutant other) {
		mutant = new TagMutant(other);
	}

	public int getStart() {
		return mutant.getStart();
	}

	public String getName() {
		return mutant.getName();
	}

	public int getEnd() {
		return mutant.getEnd();
	}

	public boolean isSelfEnding() {
		return mutant.isSelfEnding();
	}
	
	public String toString() {
		return mutant.toString(Tag.class);
	}
}
