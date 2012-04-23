package org.adligo.models.params.client;

/**
 * Description:  This is a utility class that holds static methods used for
 *                parseing xml.
 * Company:      Adligo
 * @author       scott@adligo.com
 * @version 1.3
 */
import org.adligo.i.log.client.Log;
import org.adligo.i.log.client.LogFactory;
import org.adligo.i.util.client.AppenderFactory;
import org.adligo.i.util.client.I_Appender;

public class Parser {
	private Parser() {
	}

	static final Log log = LogFactory.getLog(Parser.class);

	/**
	 * This method returns the attribute value for the give attribute from
	 * inside a xml tag header. For instance if the parameters passed in were
	 * "<foo id=\"123\" >","id" respectively this method would return "123".
	 */
	static public String getAttributeValue(String tagHeader, String attrName) {
		if (attrName.length() != 0 && tagHeader.length() != 0
				&& tagHeader.indexOf(attrName) != -1) {
			int iAttrStart, iAttrEnd, iQuoteStart, iQuoteEnd, iEqualsSign;

			iAttrStart = tagHeader.indexOf(attrName);
			iAttrEnd = iAttrStart + attrName.length() - 1;

			// check for a equals sign
			iEqualsSign = tagHeader.indexOf("=", iAttrEnd);
			iQuoteStart = tagHeader.indexOf("\"", iAttrEnd);
			if (iEqualsSign > iAttrEnd && iEqualsSign < iQuoteStart) {
				iQuoteEnd = tagHeader.indexOf("\"", iQuoteStart + 1);
				return new String(tagHeader.substring(iQuoteStart + 1,
						iQuoteEnd));
			}
		}
		return null;
	}

	/**
	 * This returns a int array with the indexes of the header and ender Strings
	 * respectively. [0] = the starting index of the first instance of the
	 * header tag [1] = the starting index of the respective ending tag For
	 * instance passing in "<a> <a></a> <b></b> </a> <c></c> <a></a> <b></b>"
	 * ,"<a","</a>" would return a int array x with x[0] = 0, x[1] = 19 note the
	 * second parameter should not pass any header information after the tag
	 * name!
	 * 
	 * Note that the header tags should have a space for the last character to
	 * prevent confulsion between "<s" and "<sa"
	 */
	static public int[] getTagIndexs(String stuff, String header, String ender) {
		// say("Stuff = " + stuff + "\n" + header + "\n" + ender);
		int iStart, iEnd;

		int [] toRet = new int[] {-1,-1};
		iStart = stuff.indexOf(header);
		iEnd = stuff.indexOf(ender, iStart);
		
		try {
			if (iStart != -1 && iEnd != -1) {
				if ("/>".equals(ender)) {
					// there is NOT another tag to couple with
				} else {
					// there is another tag need to find it
					while (!couple(iStart, iEnd, stuff, header, ender)) {
						iEnd = stuff.indexOf(ender, iEnd + 1);
					}
				}
			} else if (iStart == -1 && iEnd == -1) {
				return new int[] { -1, -1 };
			}
			if (iEnd < 0) {
				// the ender tag wans't found
				iEnd = stuff.indexOf("/>", iStart);
				toRet = new int[] {iStart, iEnd + 2};
			} else {
				toRet = new int[] { iStart, iEnd + ender.length() };
			}
			//prevent out of bounds exceptions
			if (toRet[1] > stuff.length()) {
				toRet[1] = stuff.length();
			}
			if (log.isDebugEnabled()) {
				log.debug("param returned = " + toRet[0] + "," + toRet[1]);
			}
		} catch (StringIndexOutOfBoundsException g) {
			log.error("problem in getTagIndexes stuff = " + stuff + " \n\n header =" +
					header + "\n\nender=" + ender + "  \n\n original=" + g.getMessage()
					+ "   ");
			throw g;
		}
		return toRet;
	}

	/**
	 * this method checks to see if the substring of the string parameter
	 * defined by s.substring(p,p1) determines a xml tag
	 * 
	 * @param p
	 *            the possible start of the xml tag
	 * @param p2
	 *            the possible end of the xml tag
	 * @param s
	 *            the string with the data in question
	 */
	static public boolean couple(int p, int p2, String s, String header,
			String ender) {
		
		int iNestedHeaderTags = 0;
		int iNestedEnderTags = 0;
		int iLastHeader = 0;
		int iLastEnder = 0;

		int endTag = p2 + ender.length();
		boolean b = false;
		try {
			String xmlTag = s.substring(p, endTag);
		
			if (log.isDebugEnabled()) {
				log.debug("trying = " + xmlTag);
			}
	
			while (xmlTag.indexOf(header, iLastHeader) != -1) {
				int nextEnderInHeader = xmlTag.indexOf("/>", iLastHeader);
				int headerEnd = xmlTag.indexOf(">", iLastHeader);
				if (headerEnd < nextEnderInHeader || nextEnderInHeader == -1) {
					iNestedHeaderTags++;
				}
				if (log.isDebugEnabled()) {
					log.debug("iNestedHeaderTags = " + iNestedHeaderTags);
				}
				iLastHeader++;
				iLastHeader = xmlTag.indexOf(header, iLastHeader);
				if (iLastHeader == -1) {
					break;
				}
			}
			while (xmlTag.indexOf(ender, iLastEnder) != -1) {
				iNestedEnderTags++;
				if (log.isDebugEnabled()) {
					log.debug("iNestedEnderTags = " + iNestedEnderTags);
				}
				iLastEnder = xmlTag.indexOf(ender, iLastEnder);
				iLastEnder++;
			}
			
			if (iNestedEnderTags == iNestedHeaderTags) {
				b = true;
			}
		} catch (StringIndexOutOfBoundsException z) {
			log.error("Trying to parse " + p + "," + endTag + " " + s);
			throw z;
		}
		return b;
	}

	/**
	 * @param p
	 *            the stuff with phrases that need to be replaced
	 * @param p2
	 *            the replacement stuff
	 * @param p3
	 *            the key used for finding where to insert the replacement stuff
	 * @return the replaced string
	 */
	static public String replace(String p, String p2, String p3) {
		if (log.isDebugEnabled()) {
			log.debug("replace input \n" + p + " \n " + p2 + " \n " + p3);
		}
		String r = "";
		int i = r.indexOf(p3);
		while (i != -1) {
			r = r.substring(0, i) + p2
					+ r.substring(i + p3.length(), r.length());
			i = r.indexOf(p3);
		}
		if (log.isDebugEnabled()) {
			log.debug("replace returns \n" + r);
		}
		return r;
	}

	/**
	 * @param s
	 *            the string to apply line insertions to
	 * @param space
	 *            the string to apply to each line
	 */
	public static String tab(String s, String space) {
		if (log.isDebugEnabled()) {
			log.debug("tabing\n" + s);
		}
		s = space + s;
		int i = s.indexOf("\n");
		while (i != -1 && i <= s.length() - 2) {
			s = s.substring(0, i + 1) + space + s.substring(i + 1, s.length());
			if (log.isDebugEnabled()) {
				log.debug(" i ='" + i + "' s='" + s + "'");
			}
			i = s.indexOf("\n", i + 1 + space.length());
		}
		if (log.isDebugEnabled()) {
			log.debug("end tabing\n");
		}
		return s;
	}

	/**
	 * this counts the number of tags in the string the tag should just have its
	 * header ie;
	 * 
	 * <object or <template
	 * 
	 * @param sText
	 *            to count tags from
	 * @param sTagHeader
	 *            the tag to count
	 */
	public static int countTags(String sText, String sTagHeader) {
		if (log.isDebugEnabled()) {
			log.debug("countTags text\n" + sText + "\n tag\n" + sTagHeader);
		}
		int i = 0;
		int[] iIndex = getTagIndexs(sText, sTagHeader, ">");
		String sTemp = new String(sText);

		while (iIndex[1] > 1 && (!(iIndex[0] < 0) && !(iIndex[1] < 0))) {
			if (log.isDebugEnabled()) {
				log.debug("in Loop" + iIndex[0] + "," + iIndex[1]);
			}
			sTemp = sTemp.substring(iIndex[1], sTemp.length());
			iIndex = getTagIndexs(sTemp, sTagHeader, ">");
			i++;
		}
		return i;
	}

	public static String removeAllTags(String s, String sHeader, String sEnder) {
		int[] iIndex = getTagIndexs(s, sHeader, sEnder);

		while (iIndex[1] > 1 && (!(iIndex[0] < 0) && !(iIndex[1] < 0))) {
			s = s.substring(0, iIndex[0]) + s.substring(iIndex[1], s.length());
			iIndex = getTagIndexs(s, sHeader, sEnder);
		}
		return s;
	}

	public static String getContent(String s, String sHeader, String sEnder) {
		int[] iIndex = getTagIndexs(s, sHeader, sEnder);
		int[] iHeaderIndex = getTagIndexs(s, sHeader, ">");
		return s.substring(iHeaderIndex[1], iIndex[1] - sEnder.length());
	}
	
	public static String escapeForXml(String in) {
		StringBuffer sb = new StringBuffer();

		char[] chars = in.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (c == '&') {
				sb.append("&amp;");
			} else if (c == '<') {
				sb.append("&lt;");
			} else if (c == '>') {
				sb.append("&gt;");
			} else if (c == '"') {
				sb.append("&quot;");
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String unescapeFromXml(String in) {
		in = in.replaceAll("&amp;", "&");
		in = in.replaceAll("&lt;", "<");
		in = in.replaceAll("&gt;", ">");
		in = in.replaceAll("&quot;", new String(new char[] { '"' }));
		return in;
	}

	/**
	 * 
	 * @param headerStartEnd
	 *            a int [] 0 is the start 1 is the end of the header
	 * @param content
	 * @return
	 */
	public static String getAttribute(int[] headerStartEnd, String total,
			String content) {
		return getAttributeValue(total.substring(headerStartEnd[0],
				headerStartEnd[1]), content);
	}
	
	/**
	 * returns TagInfo, currently is not meant to be compatible with 
	 * cdata (use utf-8), binary may be Base64 encoded
	 * assumes stripComments has been called (so there arn't any comments 
	 * in the xml)
	 * 
	 * @param the xml to query
	 * @param startIndex the first index to start from, 
	 * 				to look for the next tag.
	 * @return
	 */
	public static TagInfo getNextTagInfo(String xml, int startIndex) {
		return getNextTagInfo(xml, startIndex, xml.length() -1 );
	}
	
	public static TagInfo getNextTagInfo(String xml, int startIndex, int endIndex) {
		char [] chars = xml.toCharArray();
		
		I_Appender sb = AppenderFactory.create();
		String tagName = "";
		boolean inTag = false;
		int startHeaderIndex = -1;
		int endHeaderIndex = -1;
		for (int i = startIndex; i <= endIndex; i++) {
			char c = chars[i];
			if (inTag) {
				if (c== '>') {
					endHeaderIndex = i;
					if (tagName.length() == 0) {
						tagName = sb.toString();
					}
					break;
				} else if (c == ' ') {
					tagName = sb.toString();
				} else if (c == '/') {
					endHeaderIndex = i + 1;
					int [] indexes = new int [] {
							startHeaderIndex, endHeaderIndex};
					tagName = sb.toString();
					return new TagInfo(tagName, indexes, null);
				} else {
					sb.append(c);
				}
			} else if (c == '<') {
				inTag = true;
				startHeaderIndex = i;
			}  
		}
		
		sb = AppenderFactory.create();
		inTag = false;
		boolean inEndTag = false;
		int startCloseTagIndex = -1;
		int endCloseTagIndex = -1;
		for (int i = endHeaderIndex + 1; i <= endIndex; i++) {
			char c = chars[i];
			if (inEndTag) {
				if (c =='>') {
					String thisTagsName = sb.toString();
					if (tagName.equals(thisTagsName)) {
						startCloseTagIndex = i - tagName.length() - 2;
						endCloseTagIndex  = i;
						break;
					} else {
						inEndTag = false;
						inTag = false;
						sb = AppenderFactory.create();
					}
				} else {
					sb.append(c);
				}
			} else if (inTag) {
				if (c == '/') {
					inEndTag = true;
				} 
			} else if (c == '<') {
				inTag = true;
				startCloseTagIndex = i;
			}  
		}
		int [] indexes = new int [] {
				startHeaderIndex, endHeaderIndex};
		if (endCloseTagIndex == -1) {
			return new TagInfo(tagName, indexes, null);
		}
		int [] endIndexes = new int [] {
				startCloseTagIndex, endCloseTagIndex};
		return new TagInfo(tagName, indexes, endIndexes);
	}
	
	/**
	 * pull out the comments <!-- to -->
	 * @return
	 */
	public static String stripComments(String xml) {
		int start = xml.indexOf("<!--");
		while (start != -1) {
			int end = xml.indexOf("-->", start);
			if (end == -1) {
				end = xml.length() - 1;
				xml = xml.substring(0, start);
			} else {
				xml = xml.substring(0, start) + 
					xml.substring(end + 3, xml.length());
			}
			start = xml.indexOf("<!--");
		}
		return xml;
	}
	
	public static byte[] parseBytes(String base64) {
		return Base64.decode(base64);
	}
	
	public static String getTextContent(String xml, TagInfo info) {
		int startText = info.getStartTagEndIndex() + 1;
		int endText = info.getEndTagStartIndex();
		String text = xml.substring(startText, endText);
		return text;
	}

}