package org.adligo.models.params.client;

import org.adligo.i.util.client.AppenderFactory;
import org.adligo.i.util.client.CollectionFactory;
import org.adligo.i.util.client.I_Appender;
import org.adligo.i.util.client.I_Collection;

public class TagFinder {
	private String xml;
	private char [] chars ;
	private int startIndex;
	private int endIndex;
	private TagInfo firstTag;
	
	private String tagName = "";
	private boolean inTag = false;
	private boolean inTagHeader = false;
	private boolean inTagHeaderText = false;
	private boolean inTagEnder = false;
	private TagInfoMutant root = null;
	private TagInfoMutant currentParent = null;
	private TagInfoMutant currentTag = null;
	
	private I_Appender sb = AppenderFactory.create();

	public TagFinder(String pXml, int pStartIndex) {
		xml = pXml;
		endIndex = pXml.length() -1;
		chars = pXml.toCharArray();
		startIndex = pStartIndex;
	}
	
	public TagInfo getNextTag()  {

		
		
		for (int i = startIndex; i < xml.length(); i++) {
			char c = chars[i];
			//removed check for in quotes, I may support CDATA at some point
			// and would want to have a inCdata boolean
			// the text <foo bar=">" />  is actually invalid xml according to the spec
			// and should be replaced/escaped as <foo bar="&gt;" />
			if (inTag) {
				if (inTagHeader) {
					if (processTagHeader(i, c)) {
						break;
					}
				} else if (inTagEnder) {
					if (c == '>') {
						if (processFinishedEndTag(i)) {
							break;
						}
					} else {
						sb.append(c);
					}
				} else if (c == '/') {
					inTagEnder = true;
				} else {
					sb.append(c);
					inTagHeader = true;
				}
			} else if (c == '<') {
				inTag = true;
				if (root == null) {
					root = new TagInfoMutant();
					root.setHeaderStart(i);
					currentTag = root;
					inTagHeader = true;
				} else {
					currentTag = null;
				}
			} 
		}
		if (root == null) {
			return null;
		}
		return new TagInfo(root);
	}

	private boolean processFinishedEndTag(int i) {
		tagName = sb.toString();
		String parentTagName = currentParent.getTagName();
		if (parentTagName.equals(tagName)) {
			currentParent.setEnderStart(i - tagName.length()  - 2);
			currentParent.setEnderEnd(i);
			if (root == currentParent) {
				return true;
			}
			currentParent = currentParent.getParent();
		} else {
			recurseUpToTagMatch(i, currentParent);
		}
		currentTag = null;
		inTag = false;
		inTagHeader = false;
		inTagEnder = false;
		inTagHeaderText = false;
		sb = AppenderFactory.create();
		return false;
	}

	private void recurseUpToTagMatch(int i, TagInfoMutant parent) {
		TagInfoMutant info = parent.getParent();
		if (info != null) {
			String infoTagName = info.getTagName();
			if (infoTagName.equals(tagName)) {
				info.setEnderStart(i - tagName.length()  - 2);
				info.setEnderEnd(i);
				currentParent = info.getParent();
				return;
			}
		}
		TagInfoMutant grandParent = parent.getParent();
		if (grandParent != null) {
			recurseUpToTagMatch(i, grandParent);
		}
	}

	/**
	 * 
	 * @param i
	 * @param c
	 * @return true if the loop should break
	 */
	private boolean processTagHeader(int i, char c) {
		if (inTagHeaderText) {
			if (c == '/') {
				inTagHeaderText = false;
			} else if (c == ' ') {
				inTagHeaderText = false;
			} else if (c == '>') {
				if (finishHeader(i)) {
					return true;
				}
			} else {
				sb.append(c);
			}
		} else {
			if (c == '>') {
				if (finishHeader(i)) {
					return true;
				}
			} else {
				if (currentTag == null) {
					currentTag = new TagInfoMutant();
					currentTag.setHeaderStart(i -2);
				}
				if (currentTag.getTagName() == null) {
					sb.append(c);
					inTagHeaderText = true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param i
	 * @return if it should break
	 */
	public boolean finishHeader(int i) {
		char beforeC = chars[i -1];
		if (beforeC == '/') {
			currentTag.setHasEnder(false);
		}
		if (currentTag == null) {
			//this is a one character tag header like <s/>
			currentTag = new TagInfoMutant();
			currentTag.setHeaderStart(i -2);
		}
		tagName = sb.toString();
		currentTag.setTagName(tagName);
		currentTag.setHeaderEnd(i);
		if (currentTag == root) {
			currentParent = root;
			if (!currentParent.hasEnder()) {
				return true;
			}
		} else {
			currentParent.addChild(currentTag);
			if (currentTag.hasEnder()) {
				currentParent = currentTag;
			}
		}
		
		currentTag = null;
		inTag = false;
		inTagHeader = false;
		inTagHeaderText = false;
		sb = AppenderFactory.create();
		return false;
	}

}
