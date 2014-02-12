package org.adligo.models.params.client;

import org.adligo.i.util.shared.ArrayCollection;
import org.adligo.i.util.shared.CollectionFactory;
import org.adligo.i.util.shared.I_Iterator;

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
	private TagInfoMutant mutant;
	private ArrayCollection children;
	
	public TagInfo(TagInfoMutant other) {
		mutant = new TagInfoMutant(other);
		I_Iterator it = other.getChildren();
		if (it.hasNext()) {
			children = new ArrayCollection();
		}
		TagInfoMutant child = null;
		try {
			while (it.hasNext()) {
				child = (TagInfoMutant) it.next();
				children.add(new TagInfo(child));
			}
		} catch (IllegalArgumentException x) {
			/**
			 * note older version of IllegalArgumentException
			 * doesn't allow exception chaning
			 */
			throw new IllegalArgumentException(x.getMessage() + other + "/" + child);
		}
	}

	public String getTagName() {
		return mutant.getTagName();
	}

	/**
	 * should never return null
	 * the index of the header start or 0 in"<a></a>" or "<a/>"
	 * 
	 * @return
	 */
	public int getHeaderStart() {
		return mutant.getHeaderStart();
	}
	/**
	 * should never return null
	 * the index of the header end tag or 2 in"<a></a>" or 3 in "<a/>"
	 * 
	 * @return
	 */
	public int getHeaderEnd() {
		return mutant.getHeaderEnd();
	}
	/**
	 * null if no ender tag ie (<a/>)
	 * the index of the header start or 3 in"<a></a>" 
	 * 
	 * @return
	 */
	public int getEnderStart() {
		return mutant.getEnderStart();
	}
	/**
	 * null if no ender tag ie (<a/>)
	 * the index of the header start or 6 in"<a></a>" 
	 * 
	 * @return
	 */
	public int getEnderEnd() {
		return mutant.getEnderEnd();
	}

	public I_Iterator getChildren() {
		if (children == null) {
			return CollectionFactory.create().getIterator();
		}
		return children.getIterator();
	}

	/**
	 * if a ender tag exists (<a></a> = true, <b/> = false, exc)
	 * @return
	 */
	public boolean hasEnder() {
		return mutant.hasEnder();
	}
	
	public String toString() {
		return mutant.toString(TagInfo.class);
	}
	
}
