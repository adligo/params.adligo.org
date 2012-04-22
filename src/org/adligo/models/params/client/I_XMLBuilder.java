package org.adligo.models.params.client;

public interface I_XMLBuilder {

	public static final String UNIX_LINE_FEED = "\n";
	public static final String MAC_LINE_FEED = "\r";
	public static final String DOS_LINE_FEED = "\r\n";
	public static final String NO_LINE_FEED = "";
	public static final String TAB_INDENT = "\t";
	public static final String NO_INDENT = "";
	public static final String SPACE_INDENT = "   ";

	public abstract String getLineFeed();

	public abstract void setLineFeed(String lineFeed);

	public abstract String getIndent();

	public abstract void setIndent(String indent);

	public abstract void indent();

	public abstract void addIndentLevel();

	public abstract void removeIndentLevel();

	public abstract void lineFeed();
	
	/**
	 * this should append <tagName
	 * after indenting with no space after the tag name!
	 * 
	 * @param tagName
	 */
	public void appendStartTag(String tagName);

	/**
	 * this should append >
	 * as well 
	 * 
	 * @param tagName
	 */
	public void appendTagHeaderEnd(boolean lineFeed);
	
	/**
	 * appends /> if this tag only had properties.
	 * as well as a line feed
	 * @param tagName
	 */
	public void appendTagHeaderEndNoChildren();
	
	/**
	 * this should append <tagName
	 * after indenting!
	 * 
	 * @param tagName
	 */
	public void appendEndTag(String tagName);

	/**
	 * append a space then the propertyName="value"
	 * property are assumed to be well formed xml
	 * 
	 * @param propName
	 * @param value
	 */
	public void appendProperty(String propName, String value);
	/**
	 * append a space then the propertyName="value"
	 * property are assumed to be well formed xml
	 * 
	 * @param propName
	 * @param value
	 */
	public void appendProperty(String tagName, long value);
	/**
	 * append a space then the propertyName="value"
	 * property are assumed to be well formed xml
	 * 
	 * @param propName
	 * @param value
	 */
	public void appendProperty(String tagName, float value);
	/**
	 * append a space then the propertyName="value"
	 * property are assumed to be well formed xml
	 * 
	 * @param propName
	 * @param value
	 */
	public void appendProperty(String tagName, short value);
	/**
	 * append a space then the propertyName="value"
	 * property are assumed to be well formed xml
	 * 
	 * @param propName
	 * @param value
	 */
	public void appendProperty(String tagName, int value);
	/**
	 * append a space then the propertyName="value"
	 * property are assumed to be well formed xml
	 * 
	 * @param propName
	 * @param value
	 */
	public void appendProperty(String tagName, double value);
	/**
	 * append a space then the propertyName="value"
	 * property are assumed to be well formed xml
	 * 
	 * @param propName
	 * @param value
	 */
	public void appendProperty(String tagName, boolean value);
	
	public abstract void append(String p);

	public abstract void append(boolean p);

	public abstract void append(int p);

	public abstract void append(float p);

	public abstract void append(double p);

	public abstract void append(short p);

	public abstract void append(long p);

	/**
	 * this will append a base64 encoding 
	 * @param bytes
	 */
	public abstract void appendBase64(byte [] bytes);
	/**
	 * flush the current buffer to xml
	 */
	public String toXmlString();
}