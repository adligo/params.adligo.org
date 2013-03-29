package org.adligo.models.params.client;

import org.adligo.i.util.client.AppenderFactory;
import org.adligo.i.util.client.CollectionFactory;
import org.adligo.i.util.client.I_Appender;
import org.adligo.i.util.client.I_Collection;
import org.adligo.i.util.client.StringUtils;

public class TagFinder {
	private String xml;
	private char [] chars ;
	private int startIndex;
	private int endIndex;
	
	private String tagName = "";
	private boolean lastCharLessThan = false;

	private TagMatcher matcher = new TagMatcher();
	private TagMutant currentTag;
	private boolean headerOrEnder;
	
	private I_Appender sb = AppenderFactory.create();

	public TagFinder(String pXml, int pStartIndex) {
		xml = pXml;
		endIndex = pXml.length();
		chars = pXml.toCharArray();
		startIndex = pStartIndex;
	}
	
	public TagInfo getNextTag()  {

		boolean inQuotes = false;
		
		for (int i = startIndex; i < endIndex; i++) {
			char c = chars[i];
			//removed check for in quotes, I may support CDATA at some point
			// and would want to have a inCdata boolean
			// the text <foo bar=">" />  is actually invalid xml according to the spec
			// and should be replaced/escaped as <foo bar="&gt;" />
			if (c == '"') {
				inQuotes = !inQuotes;
			}
			if (!inQuotes) {
				if (c == '<') {
					lastCharLessThan = true;
					tagEnded();
					currentTag =  new TagMutant();
					currentTag.setStart(i);
				} else {
					if (lastCharLessThan) {
						lastCharLessThan = false;
						headerOrEnder = true;
						if (c == '>') {
							currentTag = null;
							headerOrEnder = false;
						} else if (isWhitespace(c)) {
							sb = AppenderFactory.create();
						} else if (isCarrot(c)) {
							sb = AppenderFactory.create();
						} else if (c == '/') {
							currentTag.setStart(i - 1);
							headerOrEnder = false;
						} else {
							sb.append(c);
						}
					} else if (currentTag != null) {
						if (headerOrEnder) {
							if (processTagHeader(i, c)) {
								tagEnded();
								break;
							}
						} else {
							if (c == '>') {
								tagName = sb.toString();
								currentTag.setName(tagName);
								currentTag.setEnd(i);
								Tag tag = new Tag(currentTag);
								matcher.addEnder(tag);
								sb = AppenderFactory.create();
								tagEnded();
							} else {
								sb.append(c);
							}
						}
					}
				}
			}
		}
		return matcher.getRoot();
	}
	
	

	/**
	 * @param i
	 * @param c
	 * @return true if the tag header ended
	 */
	private boolean processTagHeader(int i, char c) {
		if ("".equals(tagName)) {
			if (checkForEndOfHeader(i, c)) {
				if (headerEnded()) {
					return true;
				}
			} else if (isWhitespace(c)) {
				String nm = sb.toString();
				if (nm.length() == 0) {
					//its a <> which is text, not a tag header at all
					currentTag = null;
					return true;
				} else {
					setTagName();
				}
			} else {
				if ("".equals(tagName)) {
					sb.append(c);
				}
			}
		} else if (checkForEndOfHeader(i, c)) {
			if (headerEnded()) {
				return true;
			}
		} else {
			if ("".equals(tagName)) {
				sb.append(c);
			}
		}
		return false;
	}

	/**
	 * if the header ended process it
	 * @return if this was the root
	 */
	private boolean headerEnded() {
		if ("".equals(tagName)) {
			setTagName();
		}
		Tag tag = new Tag(currentTag);
		if (matcher.addHeader(tag)) {
			tagEnded();
			return true;
		}
		tagEnded();
		return false;
	}

	
	private void tagEnded() {
		if ("".equals(tagName)) {
			headerOrEnder = false;
		}
		currentTag = null;
		sb = AppenderFactory.create();
		tagName = "";
	}


	private void setTagName() {
		tagName = sb.toString();
		sb = AppenderFactory.create();
		currentTag.setName(tagName);
	}

	/**
	 * if ended tag was determined by a / or a >
	 * @param i
	 * @param c
	 * @return
	 */
	private boolean checkForEndOfHeader(int i, char c) {
		if (c == '/') {
			currentTag.setEnd(i + 1);
			currentTag.setSelfEnding(true);
			return true;
		} else if (c == '>') {
			currentTag.setEnd(i);
			currentTag.setSelfEnding(false);
			return true;
		}
		return false;
	}
	
	boolean isWhitespace(char c) {
		String s = new String("" + c);
		if (s.trim().length() == 0) {
			return true;
		}
		return false;
	}
	boolean isCarrot(char c) {
		if (c == '>') {
			return true;
		} 
		if (c == '<') {
			return true;
		}
		return false;
	}
}
