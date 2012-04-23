package org.adligo.models.params.client;

public class TagInfo {
	public static final String THIS_TAG_DOES_NOT_HAVE_A_END_TAG_CHECK_HAS_END_TAG = "This tag does NOT have a end tag, check hasEndTag.";
	public static final String THE_END_TAG_START_INDEX_MUST_BE_BEFORE_THE_END_TAG_END_INDEX = "The end tag start index must be before the end tag end index.";
	public static final String THE_START_TAG_END_INDEX_MUST_BE_BEFORE_THE_END_TAT_START_INDEX = "The start tag end index must be before the end tat start index";
	public static final String THE_START_TAG_START_INDEX_MUST_BE_BEFORE_THE_START_TAG_END_INDEX = "The start tag start index must be before the start tag end index.";
	public static final String TAG_INFO_REQUIRES_2_INDEXES = "Tag Info requires 2 indexes";
	/**
	 * the tag name
	 * <foo/></foo>
	 * would be foo
	 */
	private String tagName;
	/**
	 * 0 is the starting head tag index, or 0 in the example
	 * 1 is the ending head tag, or 5 in the example
	 * 2 is the starting end tag start index, or 6 in the example
	 * 3 is the ending end tag index or 11 in the example
	 * example "<foo/></foo>"
	 */
	private int[] indexes;
	private int[] endTagIndexes;
	
	public TagInfo(String name, int [] pIndexes, int [] pEndTagIndexes) {
		tagName = name;
		indexes = pIndexes;
		endTagIndexes = pEndTagIndexes;
		if (indexes.length != 2) {
			throw new IllegalArgumentException(
					TAG_INFO_REQUIRES_2_INDEXES);
		}
		if (indexes[0] >= indexes[1]) {
			throw new IllegalArgumentException(
					THE_START_TAG_START_INDEX_MUST_BE_BEFORE_THE_START_TAG_END_INDEX);
		}
		if (endTagIndexes != null) {
			if (endTagIndexes.length != 2) {
				throw new IllegalArgumentException(
						TAG_INFO_REQUIRES_2_INDEXES);
			}
			if (indexes[1] >= endTagIndexes[0]) {
				throw new IllegalArgumentException(
						THE_START_TAG_END_INDEX_MUST_BE_BEFORE_THE_END_TAT_START_INDEX);
			}
			if (endTagIndexes[0] >= endTagIndexes[1]) {
				throw new IllegalArgumentException(
						THE_END_TAG_START_INDEX_MUST_BE_BEFORE_THE_END_TAG_END_INDEX);
			}
		}
	}
	
	public String getTagName(){
		return tagName;
	}
	
	public int getStartTagStartIndex() {
		return indexes[0];
	}
	
	public int getStartTagEndIndex() {
		return indexes[1];
	}
	public int getEndTagStartIndex() {
		if (endTagIndexes == null) {
			throw new IllegalStateException(
					THIS_TAG_DOES_NOT_HAVE_A_END_TAG_CHECK_HAS_END_TAG);
		}
		return endTagIndexes[0];
	}
	public int getEndTagEndIndex() {
		if (endTagIndexes == null) {
			throw new IllegalStateException(
					THIS_TAG_DOES_NOT_HAVE_A_END_TAG_CHECK_HAS_END_TAG);
		}
		return endTagIndexes[1];
	}
	
	public boolean hasEndTag() {
		if (endTagIndexes == null) {
			return false;
		}
		return true;
	}
}
