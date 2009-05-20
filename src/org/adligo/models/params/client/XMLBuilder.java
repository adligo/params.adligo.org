package org.adligo.models.params.client;

public class XMLBuilder {
	public static final String UNIX_LINE_FEED = "\n";
	public static final String MAC_LINE_FEED = "\r";
	public static final String DOS_LINE_FEED = "\r\n";
	public static final String NO_LINE_FEED = "";

	public static final String TAB_INDENT = "\t";
	public static final String NO_INDENT = "";
	public static final String SPACE_INDENT = "   ";
	/**
	 * defaults to dos line feed looks nice in eclipse console, and on dos, and
	 * unix file systems
	 */
	private String lineFeed = DOS_LINE_FEED;
	private String indent = SPACE_INDENT;
	private StringBuffer buffer = new StringBuffer();
	private int indentLevel = 0;

	public String getLineFeed() {
		// throw npe if null
		return lineFeed;
	}

	public void setLineFeed(String lineFeed) {
		lineFeed.hashCode();
		this.lineFeed = lineFeed;
	}

	public String getIndent() {
		return indent;
	}

	public void setIndent(String indent) {
		// throw npe if null
		indent.hashCode();
		this.indent = indent;
	}

	public StringBuffer getBuffer() {
		return buffer;
	}

	public void indent() {
		for (int i = 0; i < indentLevel; i++) {
			buffer.append(indent);
		}
	}

	public void addIndentLevel() {
		indentLevel++;
	}

	public void removeIndentLevel() {
		indentLevel--;
	}

	public void lineFeed() {
		buffer.append(lineFeed);
	}

	public void append(String p) {
		buffer.append(p);
	}

}
