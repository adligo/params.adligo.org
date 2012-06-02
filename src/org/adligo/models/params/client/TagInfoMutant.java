package org.adligo.models.params.client;

import org.adligo.i.util.client.AppenderFactory;
import org.adligo.i.util.client.ArrayCollection;
import org.adligo.i.util.client.I_Appender;
import org.adligo.i.util.client.I_Iterator;
import org.adligo.i.util.client.StringUtils;

public class TagInfoMutant {
	public static final String TAG_INFO_REQUIRES_A_NON_EMPTY_NAME = "Tag Info requires a non empty name";
	public static final String THE_ENDER_FLAG_SHOULD_MATCH_THE_FACT_THAT_THERE_ARE_ENDER_INTEGERS = "The ender flag should match the fact that there are ender integers";
	public static final String TAG_INFO_INDEXES_MUST_BE_GREATER_THAN_OR_EQUAL_TO_ZERO = "TagInfo indexes must be greater than or equal to zero.";
	public static final String THIS_TAG_DOES_NOT_HAVE_A_END_TAG_CHECK_HAS_END_TAG = "This tag does NOT have a end tag, check hasEndTag.";
	public static final String THE_END_TAG_START_INDEX_MUST_BE_BEFORE_THE_END_TAG_END_INDEX = "The end tag start index must be before the end tag end index.";
	public static final String THE_START_TAG_END_INDEX_MUST_BE_BEFORE_THE_END_TAG_START_INDEX = "The start tag end index must be before the end tag start index";
	public static final String THE_START_TAG_START_INDEX_MUST_BE_BEFORE_THE_START_TAG_END_INDEX = "The start tag start index must be before the start tag end index.";
	public static final String TAG_INFO_REQUIRES_2_INDEXES = "Tag Info requires 2 indexes";
	
	
	private String tagName;
	private Integer headerStart;
	private Integer headerEnd;
	private Integer enderStart;
	private Integer enderEnd;
	private boolean hasEnder = true;
	/**
	 * a collection of TagInfoMutants
	 */
	private ArrayCollection children;
	private TagInfoMutant parent;
	
	public TagInfoMutant() {}
	
	public TagInfoMutant(TagInfoMutant other) {
		other.validate();
		setTagName(other.tagName);
		headerStart = other.headerStart;
		headerEnd = other.headerEnd;
		enderStart = other.enderStart;
		enderEnd = other.enderEnd;
		hasEnder = other.hasEnder;
		if (hasEnder) {
			if (enderEnd == null) {
				throw new IllegalArgumentException("This tag has a ender but the enderEnd is null " +
						toString());
			}
			if (enderStart == null) {
				throw new IllegalArgumentException("This tag has a ender but the enderStart is null" + 
						toString());
			}
		}
	}
	
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		char [] chars = tagName.toCharArray();
		I_Appender appender = AppenderFactory.create();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (Character.isWhitespace(c)) {
				//do nothing
			} else {
				appender.append(c);
			}
		}
		this.tagName = appender.toString();
	}
	public Integer getHeaderStart() {
		return headerStart;
	}
	public void setHeaderStart(int headerStart) {
		this.headerStart = headerStart;
	}
	public Integer getHeaderEnd() {
		return headerEnd;
	}
	public void setHeaderEnd(int headerEnd) {
		this.headerEnd = headerEnd;
	}
	
	public Integer getEnderStart() {
		return enderStart;
	}
	public void setEnderStart(int enderStart) {
		this.enderStart = enderStart;
	}
	public Integer getEnderEnd() {
		return enderEnd;
	}
	public void setEnderEnd(int enderEnd) {
		this.enderEnd = enderEnd;
	}
	public Integer getChildrenSize() {
		if (children == null) {
			return 0;
		}
		return children.size();
	}
	public TagInfoMutant getChild(int whichOne) {
		if (children == null) {
			return null;
		}
		return (TagInfoMutant) children.get(whichOne);
	}
	
	public I_Iterator getChildren() {
		if (children == null) {
			return new ArrayCollection().getIterator();
		}
		return children.getIterator();
	}
	public void addChild(TagInfoMutant child) {
		if (children == null) {
			children = new ArrayCollection();
		}
		children.add(child);
		child.setParent(this);
	}
	
	public void validate() {
		if (StringUtils.isEmpty(tagName)) {
			throw new IllegalArgumentException(
					TAG_INFO_REQUIRES_A_NON_EMPTY_NAME);
		}
		if (headerStart == null || headerEnd == null) {
			throw new IllegalArgumentException(
					TAG_INFO_REQUIRES_2_INDEXES);
		}
		if (headerStart < 0) {
			throw new IllegalArgumentException(TAG_INFO_INDEXES_MUST_BE_GREATER_THAN_OR_EQUAL_TO_ZERO);
		}
		if (headerEnd < 0) {
			throw new IllegalArgumentException(TAG_INFO_INDEXES_MUST_BE_GREATER_THAN_OR_EQUAL_TO_ZERO);
		}
		if (headerEnd <= headerStart ) {
			throw new IllegalArgumentException(
					THE_START_TAG_START_INDEX_MUST_BE_BEFORE_THE_START_TAG_END_INDEX);
		}
		if (enderStart != null || enderEnd != null) {
			if (enderStart == null || enderEnd == null) {
				throw new IllegalArgumentException(
						TAG_INFO_REQUIRES_2_INDEXES);
			}
			if (enderStart < 0 ) {
				throw new IllegalArgumentException(
						TAG_INFO_INDEXES_MUST_BE_GREATER_THAN_OR_EQUAL_TO_ZERO);
			}
			if (enderEnd < 0 ) {
				throw new IllegalArgumentException(
						TAG_INFO_INDEXES_MUST_BE_GREATER_THAN_OR_EQUAL_TO_ZERO);
			}
			if (headerEnd >= enderStart) {
				throw new IllegalArgumentException(
						THE_START_TAG_END_INDEX_MUST_BE_BEFORE_THE_END_TAG_START_INDEX);
			}
			if (enderStart >= enderEnd) {
				throw new IllegalArgumentException(
						THE_END_TAG_START_INDEX_MUST_BE_BEFORE_THE_END_TAG_END_INDEX);
			}
			if (!hasEnder) {
				throw new IllegalArgumentException(THE_ENDER_FLAG_SHOULD_MATCH_THE_FACT_THAT_THERE_ARE_ENDER_INTEGERS);
			}
		}
	}

	public boolean hasEnder() {
		if (enderEnd == null) {
			return false;
		}
		if (enderStart == null) {
			return false;
		}
		return hasEnder;
	}

	public void setHasEnder(boolean p) {
		this.hasEnder = p;
	}

	public TagInfoMutant getParent() {
		return parent;
	}

	public void setParent(TagInfoMutant parent) {
		this.parent = parent;
	}
	@Override
	public String toString() {
		return toString(TagInfoMutant.class);
	}
	
	String toString(Class<?> clazz) {
		return "" + clazz.getSimpleName() + " [tagName=" + tagName + ", headerStart="
				+ headerStart + ", headerEnd=" + headerEnd + ", enderStart="
				+ enderStart + ", enderEnd=" + enderEnd + " hasEnder=" + hasEnder + "]";
	}
}
