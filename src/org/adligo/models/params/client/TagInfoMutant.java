package org.adligo.models.params.client;

import org.adligo.i.util.client.AppenderFactory;
import org.adligo.i.util.client.ArrayCollection;
import org.adligo.i.util.client.CollectionFactory;
import org.adligo.i.util.client.I_Appender;
import org.adligo.i.util.client.I_Iterator;
import org.adligo.i.util.client.IteratorFactory;

public class TagInfoMutant {
	public static final String THIS_TAG_DOES_NOT_HAVE_A_END_TAG_CHECK_HAS_END_TAG = "This tag does NOT have a end tag, check hasEndTag.";
	public static final String THE_END_TAG_START_INDEX_MUST_BE_BEFORE_THE_END_TAG_END_INDEX = "The end tag start index must be before the end tag end index.";
	public static final String THE_START_TAG_END_INDEX_MUST_BE_BEFORE_THE_END_TAT_START_INDEX = "The start tag end index must be before the end tat start index";
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
		tagName = other.tagName;
		headerStart = other.headerStart;
		headerEnd = other.headerEnd;
		enderStart = other.enderStart;
		enderEnd = other.enderEnd;
		hasEnder = other.hasEnder;
	}
	
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public int getHeaderStart() {
		return headerStart;
	}
	public void setHeaderStart(int headerStart) {
		this.headerStart = headerStart;
	}
	public int getHeaderEnd() {
		return headerEnd;
	}
	public void setHeaderEnd(int headerEnd) {
		this.headerEnd = headerEnd;
	}
	
	public int getEnderStart() {
		return enderStart;
	}
	public void setEnderStart(int enderStart) {
		this.enderStart = enderStart;
	}
	public int getEnderEnd() {
		return enderEnd;
	}
	public void setEnderEnd(int enderEnd) {
		this.enderEnd = enderEnd;
	}
	public int getChildrenSize() {
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
		if (headerStart == null || headerEnd == null) {
			throw new IllegalArgumentException(
					TAG_INFO_REQUIRES_2_INDEXES);
		}
		if (headerStart >= headerEnd) {
			throw new IllegalArgumentException(
					THE_START_TAG_START_INDEX_MUST_BE_BEFORE_THE_START_TAG_END_INDEX);
		}
		if (enderStart != null || enderEnd != null) {
			if (enderStart == null || enderEnd == null) {
				throw new IllegalArgumentException(
						TAG_INFO_REQUIRES_2_INDEXES);
			}
			if (headerEnd >= enderStart) {
				throw new IllegalArgumentException(
						THE_START_TAG_END_INDEX_MUST_BE_BEFORE_THE_END_TAT_START_INDEX);
			}
			if (enderStart >= enderEnd) {
				throw new IllegalArgumentException(
						THE_END_TAG_START_INDEX_MUST_BE_BEFORE_THE_END_TAG_END_INDEX);
			}
		}
	}

	public boolean hasEnder() {
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
	
	public String toString() {
		I_Appender out = AppenderFactory.create();
		out.append("<");
		out.append(tagName);
		if (!hasEnder) {
			out.append("/>");
		} else {
			out.append(">");
			if (children != null) {
				I_Iterator it = children.getIterator();
				while (it.hasNext()) {
					out.append(it.next());
				}
			}
			out.append("</");
			out.append(tagName);
			out.append(">");
		}
		return out.toString();
	}
}
