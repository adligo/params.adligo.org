package org.adligo.models.params.shared;

import java.io.UnsupportedEncodingException;

import org.adligo.i.util.shared.AppenderFactory;
import org.adligo.i.util.shared.I_Appender;

public class XMLBuilder implements I_XMLBuilder {
	/**
	 * defaults to dos line feed looks nice in eclipse console, and on dos, and
	 * unix file systems
	 * 
	 * NOTE you can set the line feed to a empty string to remove it 
	 * from the xml (page weight)
	 * see clearLineFeedAndIndent
	 */
	private String lineFeed = DOS_LINE_FEED;
	/**
	 * note you can set the indent to empty string 
	 * to remove it from the xml (page weight)
	 * see clearLineFeedAndIndent
	 */
	private String indent = SPACE_INDENT;
	private I_Appender buffer = AppenderFactory.create();
	private int indentLevel = 0;
	private String charSet = "UTF-8";

	/* (non-Javadoc)
	 * @see org.adligo.models.params.client.I_XMLBuilder#getLineFeed()
	 */
	public String getLineFeed() {
		// throw npe if null
		return lineFeed;
	}

	/* (non-Javadoc)
	 * @see org.adligo.models.params.client.I_XMLBuilder#setLineFeed(java.lang.String)
	 */
	public void setLineFeed(String lineFeed) {
		lineFeed.hashCode();
		this.lineFeed = lineFeed;
	}

	/* (non-Javadoc)
	 * @see org.adligo.models.params.client.I_XMLBuilder#getIndent()
	 */
	public String getIndent() {
		return indent;
	}

	/* (non-Javadoc)
	 * @see org.adligo.models.params.client.I_XMLBuilder#setIndent(java.lang.String)
	 */
	public void setIndent(String indent) {
		// throw npe if null
		indent.hashCode();
		this.indent = indent;
	}

	/* (non-Javadoc)
	 * @see org.adligo.models.params.client.I_XMLBuilder#indent()
	 */
	public void indent() {
		for (int i = 0; i < indentLevel; i++) {
			buffer.append(toUtf8(indent));
		}
	}

	/* (non-Javadoc)
	 * @see org.adligo.models.params.client.I_XMLBuilder#addIndentLevel()
	 */
	public void addIndentLevel() {
		indentLevel++;
	}

	/* (non-Javadoc)
	 * @see org.adligo.models.params.client.I_XMLBuilder#removeIndentLevel()
	 */
	public void removeIndentLevel() {
		indentLevel--;
		if (indentLevel < 0) {
			indentLevel = 0;
		}
	}

	/* (non-Javadoc)
	 * @see org.adligo.models.params.client.I_XMLBuilder#lineFeed()
	 */
	public void lineFeed() {
		buffer.append(toUtf8(lineFeed));
	}

	/* (non-Javadoc)
	 * @see org.adligo.models.params.client.I_XMLBuilder#append(java.lang.String)
	 */
	public void append(String p) {
		buffer.append(toUtf8(p));
	}
	/* (non-Javadoc)
	 * @see org.adligo.models.params.client.I_XMLBuilder#append(boolean)
	 */
	public void append(boolean p) {
		buffer.append(p);
	}
	/* (non-Javadoc)
	 * @see org.adligo.models.params.client.I_XMLBuilder#append(int)
	 */
	public void append(int p) {
		buffer.append(p);
	}
	/* (non-Javadoc)
	 * @see org.adligo.models.params.client.I_XMLBuilder#append(float)
	 */
	public void append(float p) {
		buffer.append(p);
	}
	/* (non-Javadoc)
	 * @see org.adligo.models.params.client.I_XMLBuilder#append(double)
	 */
	public void append(double p) {
		buffer.append(p);
	}
	/* (non-Javadoc)
	 * @see org.adligo.models.params.client.I_XMLBuilder#append(short)
	 */
	public void append(short p) {
		buffer.append(p);
	}
	/* (non-Javadoc)
	 * @see org.adligo.models.params.client.I_XMLBuilder#append(long)
	 */
	public void append(long p) {
		buffer.append(p);
	}
	public void clearLineFeedAndIndent() {
		setLineFeed(NO_LINE_FEED);
		setIndent(NO_INDENT);
	}

	public void appendTagHeaderStart(String tagName) {
		append("<");
		append(tagName);
	}

	public void appendTagHeaderEnd(boolean addFeed) {
		append(">");
		if (addFeed) {
			append(lineFeed);
		}
	}

	public void appendEndTag(String tagName) {
		append("</");
		append(tagName);
		append(">");
		append(lineFeed);
	}

	public void appendAttribute(String propName, String value) {
		append(" ");
		append(propName);
		append("=\"");
		append(value);
		append("\"");
	}

	public void appendAttribute(String tagName, boolean value) {
		appendAttribute(tagName, "" + value);
	}
	
	public void appendAttribute(String tagName, long value) {
		appendAttribute(tagName, "" + value);
	}

	public void appendAttribute(String tagName, float value) {
		appendAttribute(tagName, "" + value);
	}

	public void appendAttribute(String tagName, short value) {
		appendAttribute(tagName, "" + value);
	}

	public void appendAttribute(String tagName, int value) {
		appendAttribute(tagName, "" + value);
	}

	public void appendAttribute(String tagName, double value) {
		appendAttribute(tagName, "" + value);
	}

	public void appendTagHeaderEndNoChildren() {
		append("/>");
		append(lineFeed);
	}

	public String toXmlString() {
		return buffer.toString();
	}

	public void appendBase64(byte[] bytes) {
		String data = Base64.encode(bytes);
		buffer.append(toUtf8(data));
	}

	public void appendTagWithTextContent(String tagName, String textContent) {
		appendTagHeaderStart(tagName);
		buffer.append(">");
		buffer.append(toUtf8(textContent));
		appendEndTag(tagName);
	}

	public void appendAttribute(String tagName, byte[] bytes) {
		String data = Base64.encode(bytes);
		appendAttribute(tagName, data);
	}
	
	private String toUtf8(String p) {
		try {
			return new String(p
				.getBytes(charSet));
		} catch (UnsupportedEncodingException x) {
			throw new IllegalArgumentException(x.getMessage());
		}
	}

	public String getCharSet() {
		return charSet;
	}

	public void setCharSet(String charSet) {
		if (buffer.toString().length() > 0) {
			throw new IllegalStateException(
					"The Charset may only be changed before building the xml document");
		}
		this.charSet = charSet;
	}
}
