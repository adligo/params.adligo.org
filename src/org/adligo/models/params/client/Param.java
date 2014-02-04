package org.adligo.models.params.client;

import java.util.Date;

import org.adligo.i.log.client.Log;
import org.adligo.i.log.client.LogFactory;
import org.adligo.i.util.client.ArrayCollection;
import org.adligo.i.util.client.I_Iterator;

/**
 * 
 * @author scott@adligo.com
 * @version 1.3
 */

public class Param implements I_TemplateParams {
	static final Log log = LogFactory.getLog(Param.class);
	private static ClassForNameValueMap CLASS_FOR_NAME_VALUE_MAP = new ClassForNameValueMap();
	private static ClassShortNameValueMap CLASS_SHORT_NAME_VALUE_MAP = new ClassShortNameValueMap();
	
	/**
	 * standard name of where clause 
	 * parameter, which shows up in lots of query languages
	 * sql, mdx, fast exc
	 */
	public static final String WHERE = "where";
	/**
	 * 
	 */
	private static final long serialVersionUID = 16L;
	public static final String PARAMS = "params";
	public static final String OPERATORS = "operators";
	public static final String VALUES = "values";

	/**
	 * this version number represents the xml format and should be incremented
	 * only if the format changes
	 */
	public static final String CLASS_VERSION = new String("1.7");
	private String name;
	/**
	 * the instances of the various objects ie Integers, Booleans exc
	 */
	private ArrayCollection values = new ArrayCollection(2);
	/**
	 * The types of the various objects ie instance of ValueType
	 */
	private ArrayCollection valueTypes = new ArrayCollection(2);
	
	private I_TemplateParams params;
	private boolean bAlreadyGotMe = false;
	private I_Operators operators;
	private I_TemplateParams parent;

	/**
	 * Constructors
	 */

	public Param() {
	}
	public Param(String pName) {
		name = pName;
	}
	public Param(String pName, I_Operators pOperators) {
		name = pName;
		operators = pOperators;
	}
	public Param(String pName, String [] pOperators) {
		name = pName;
		operators = new Operators(pOperators);
	}
	public Param(String pName, I_TemplateParams pParams) {
		name = pName;
		params = pParams;
	}

	/**
	 * package private constructor, since its all a object here but the public
	 * api doesn't know that if you really needed a new type you could extend
	 * these classes but generally String, Integer, Short, Long, Float, Double
	 * and Date are enough for query languages
	 * 
	 * @param pName
	 * @param value
	 * @param pParams
	 */
	private Param(String pName, Object value, I_TemplateParams pParams, I_Operators pOperators) {
		name = pName;
		values.add(value);
		params = pParams;
		operators = pOperators;
	}
	
	public Param(String pName, String value, I_TemplateParams pParams) {
		this(pName, (Object) value, pParams, null);
		valueTypes.add(ValueTypes.STRING);
	}

	public Param(String pName, double value, I_TemplateParams pParams) {
		this(pName, new Double(value), pParams, null);
		valueTypes.add(ValueTypes.DOUBLE);
	}
	
	public Param(String pName, String operator, String value) {
		this(pName, value, null,new Operators(operator));
		valueTypes.add(ValueTypes.STRING);
	}

	public Param(String pName, String operator, int value) {
		this(pName, new Integer(value), null, new Operators(operator));
		valueTypes.add(ValueTypes.INTEGER);
	}
	
	public Param(String pName, String operator, short value) {
		this(pName, new Short(value), null, new Operators(operator));
		valueTypes.add(ValueTypes.SHORT);
	}
	
	public Param(String pName, String operator, long value) {
		this(pName, new Long(value), null, new Operators(operator));
		valueTypes.add(ValueTypes.LONG);
	}
	
	public Param(String pName, String operator, double value) {
		this(pName, new Double(value), null, new Operators(operator));
		valueTypes.add(ValueTypes.DOUBLE);
	}
	
	public Param(String pName, String operator, float value) {
		this(pName, new Float(value), null, new Operators(operator));
		valueTypes.add(ValueTypes.FLOAT);
	}
	
	public Param(String pName, String operator, Date value) {
		this(pName, (Object) value, null, new Operators(operator));
		valueTypes.add(ValueTypes.DATE);
	}
	public Param(String pName, String operator, boolean value) {
		this(pName, new Boolean(value), null, new Operators(operator));
		valueTypes.add(ValueTypes.BOOLEAN);
	}
	
	public void setParent(I_TemplateParams p) {
		parent = p;
	}

	public I_TemplateParams getParent() {
		return parent;
	}

	public void setName(String s) {
		name = s;
	}

	public void clearValue() {
		values.clear();
		valueTypes.clear();
	}
	
	private void setObjectValue(Object p) {
		clearValue();
		values.add(p);
	}
	
	public void setValue(String p) {
		setObjectValue( p);
		valueTypes.add(ValueTypes.STRING);
	}

	public void setValue(int p) {
		setObjectValue(new Integer(p));
		valueTypes.add(ValueTypes.INTEGER);
	}

	public void setValue(short p) {
		setObjectValue(new Short(p));
		valueTypes.add(ValueTypes.SHORT);
	}

	public void setValue(long p) {
		setObjectValue(new Long(p));
		valueTypes.add(ValueTypes.LONG);
	}

	public void setValue(double p) {
		setObjectValue(new Double(p));
		valueTypes.add(ValueTypes.DOUBLE);
	}

	public void setValue(float p) {
		setObjectValue(new Float(p));
		valueTypes.add(ValueTypes.FLOAT);
	}

	public void setValue(Date p) {
		setObjectValue( p);
		valueTypes.add(ValueTypes.DATE);
	}

	public void setValue(boolean p) {
		setObjectValue(new Boolean(p));
		valueTypes.add(ValueTypes.BOOLEAN);
	}
	
	public void setValueBigDecimal(String p) {
		setObjectValue( p);
		valueTypes.add(ValueTypes.BIG_DECIMAL);
	}
	
	public void setValueBigInteger(String p) {
		setObjectValue( p);
		valueTypes.add(ValueTypes.BIG_INTEGER);
	}
	public void addValue(String p) {
		values.add(p);
		valueTypes.add(ValueTypes.STRING);
	}

	public void addValueBigDecimal(String p) {
		values.add(p);
		valueTypes.add(ValueTypes.BIG_DECIMAL);
	}
	
	public void addValueBigInteger(String p) {
		values.add(p);
		valueTypes.add(ValueTypes.BIG_INTEGER);
	}
	
	public void addValue(Date p) {
		values.add(p);
		valueTypes.add(ValueTypes.DATE);
	}

	public void addValue(short p) {
		values.add(new Short(p));
		valueTypes.add(ValueTypes.SHORT);
	}
	
	public void addValue(int p) {
		values.add(new Integer(p));
		valueTypes.add(ValueTypes.INTEGER);
	}
	
	public void addValue(long p) {
		values.add(new Long(p));
		valueTypes.add(ValueTypes.LONG);
	}
	
	public void addValue(float p) {
		values.add(new Float(p));
		valueTypes.add(ValueTypes.FLOAT);
	}
	
	public void addValue(double p) {
		values.add(new Double(p));
		valueTypes.add(ValueTypes.DOUBLE);
	}
	
	public void addValue(boolean p) {
		values.add(new Boolean(p));
		valueTypes.add(ValueTypes.BOOLEAN);
	}
	
	public void setParams(I_TemplateParams pParams) {
		params = pParams;
	}

	public I_TemplateParams getNestedParams() {
		return params;
	}

	public String getName() {
		return name;
	}

	public Object[] getValues() {
		return values.toArray();
	}

	// do nothing for these
	public void First() {
		bAlreadyGotMe = false;
	}

	public boolean getNextParam(String s) {
		if (s.equals(name) && bAlreadyGotMe == false) {
			bAlreadyGotMe = true;
			return true;
		}
		return false;
	}

	public I_Operators getOperators() {
		return operators;
	}

	public void setOperators(I_Operators p) {
		this.operators = p;
	}
	
	public void setOperators(String[] operators) {
		this.operators = new Operators(operators);
	}

	public void setOperator(String operator) {
		this.operators = new Operators(operator);
	}

	public void setOperator(I_Operators p) {
		this.operators = p;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(name);
		sb.append(" values=\"");
		I_Iterator it = values.getIterator();
		boolean first = true;
		while (it.hasNext()) {
			Object o = it.next();
			if (!first) {
				sb.append(",");
			}
			sb.append(o);
			first = false;
		}
		sb.append("\" ");
		sb.append(operators);
		
		if (params != null) {
			sb.append("] ");
			
			sb.append(params.toString());
			sb.append("[/");
			sb.append(name);
			sb.append("]");
		} else {
			sb.append("\\]");
		}
		return sb.toString();
	}

	/*************************************** I_XML_Serilizable ***************************************************/
	public String writeXML() {
		XMLBuilder builder = new XMLBuilder();
		writeXML(builder);
		return builder.toXmlString();
	}

	public void writeXML(I_XMLBuilder builder) {
		writeXML(builder, null);
	}

	public void writeXML(I_XMLBuilder sb, String name) {
		sb.indent();
		sb.append(XMLObject.OBJECT_HEADER);
		sb.append(" ");
		sb.append(XMLObject.CLASS);
		sb.append("=\"");
		sb.append(ClassForNameMap.PARAM_SHORT_NAME);
		sb.append("\" ");
		sb.append(XMLObject.VERSION);
		sb.append("=\"");
		sb.append(CLASS_VERSION);
		sb.append("\" ");
		sb.append(XMLObject.NAME);
		sb.append("=\"");
		if (name != null) {
			sb.append(name);
		} else {
			sb.append(this.getName());
		}

		sb.append("\"");

		if (hasNestedContent()) {
			sb.append(">");
			sb.lineFeed();
	
			sb.addIndentLevel();
	
			writeOperators(sb);
			writeValues(sb);
			if (params != null) {
				params.writeXML(sb, PARAMS);
			}
	
			sb.removeIndentLevel();
	
			sb.indent();
			sb.append(XMLObject.OBJECT_ENDER);
		} else {
			sb.append(" />");
		}
		sb.lineFeed();

		if (log.isDebugEnabled()) {
			log.debug(sb.toString());
		}
	}

	public boolean hasNestedContent() {
		if (operators == null) {
			if (values.size() == 0) {
				if (params == null) {
					return false;
				}
			}
		} else {
			if (operators != null) {
				String [] vals = operators.getValues();
				if (vals != null) {
					if (vals.length == 0) {
						if (values.size() == 0) {
							if (params == null) {
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	private void writeOperators(I_XMLBuilder sb) {
		if (operators == null) {
			return;
		}
		String [] vals = operators.getValues();
		if (vals == null) {
			return;
		}
		if (vals.length == 0) {
			return;
		}
		sb.indent();

		sb.append(XMLObject.OBJECT_HEADER);
		sb.append(" ");
		sb.append(XMLObject.NAME);
		sb.append("=\"operators\">");
		sb.lineFeed();
		sb.addIndentLevel();

		for (int i = 0; i < vals.length; i++) {
			sb.indent();

			sb.append(XMLObject.OBJECT_HEADER);
			sb.append(">");

			String operator = vals[i];
			if (operator == null) {
				sb.append("null");
			} else {
				sb.append(Parser.escapeForXml(operator.toString()));
			}
			sb.append(XMLObject.OBJECT_ENDER);
			sb.lineFeed();
		}
		sb.removeIndentLevel();
		sb.indent();
		sb.append(XMLObject.OBJECT_ENDER);
		sb.lineFeed();
	}

	private void writeValues(I_XMLBuilder sb) {
		if (values.size() == 0) {
			return;
		}
		sb.indent();

		sb.append(XMLObject.OBJECT_HEADER);
		sb.append(" ");
		sb.append(XMLObject.NAME);
		sb.append("=\"values\" ");
		sb.append(">");
		sb.lineFeed();
		sb.addIndentLevel();

		for (int i = 0; i < values.size(); i++) {
			sb.indent();

			Object o = values.get(i);
			ValueType vt = (ValueType) valueTypes.get(i);
			
			sb.append(XMLObject.OBJECT_HEADER);
			sb.append(" ");
			sb.append(XMLObject.CLASS);
			sb.append("=\"");
			sb.append((String) CLASS_SHORT_NAME_VALUE_MAP.getClassFor(o, vt));
			sb.append("\">");
			// implicit to string or null
			if (o == null) {
				sb.append("null");
			} else {
				if (o instanceof Date) {
					sb.append("" + ((Date) o).getTime());
				} else {
					sb.append(Parser.escapeForXml(o.toString()));
				}
			}
			sb.append(XMLObject.OBJECT_ENDER);
			sb.lineFeed();
		}
		sb.removeIndentLevel();
		sb.indent();
		sb.append(XMLObject.OBJECT_ENDER);
		sb.lineFeed();
	}

	public void readXML(String s) {
		readXML(s, null);
	}

	public void readXML(String s, String name) {
		if (log.isDebugEnabled()) {
			log.debug("Param.readXML\n" + s);
		}
		this.name = name;
		int[] iaHeader = Parser.getTagIndexs(s, XMLObject.OBJECT_HEADER, ">"); // get
																				// first
																				// header
																				// tag
		int[] iaObject = Parser.getTagIndexs(s, XMLObject.OBJECT_HEADER,
				XMLObject.OBJECT_ENDER); // get first object tag
		
		int start = iaHeader[1] + 1;
		int end = getStartOfEndTag(iaObject);
		if (start >= end) {
			start = iaObject[0];
			end = iaObject[1];
		}
		
		String body = s.substring(start, end);
		if (log.isDebugEnabled()) {
			log.debug("body\n" + body);
		}

		for (int i = 0; i < 4; i++) {
			if (body.trim().length() == 0) {
				break;
			}
			iaHeader = Parser.getTagIndexs(body, XMLObject.OBJECT_HEADER, ">"); // get
																				// first
																				// header
																				// tag
			iaObject = Parser.getTagIndexs(body, XMLObject.OBJECT_HEADER,
					XMLObject.OBJECT_ENDER); // get first object tag

			String sName = Parser.getAttribute(iaHeader, body, XMLObject.NAME);
			int startOfEndTag = getStartOfEndTag(iaObject);
			
			int startContent = iaHeader[1] + 1;
			int endContent = startOfEndTag;
			if (startContent <= endContent) {
				//deal with nested content
				String content = body.substring(startContent, endContent);
				if (sName.equals(VALUES)) {
					parseValues(content);
				} else if (sName.equals(OPERATORS)) {
					operators = parseOperators(content);
				} else if (sName.equals(PARAMS)) {
					parseParams(body);
				}
			}
			// pull out the object we just parsed
			start = startOfEndTag + XMLObject.OBJECT_ENDER.length();
			if (log.isDebugEnabled()) {
				log.debug("trying to parse " + start + "," + body.length()
						+ "\n" + body);
			}
			body = body.substring(start, body.length());
			if (log.isDebugEnabled()) {
				log.debug("body\n" + body);
			}

		}
	}

	private int getStartOfEndTag(int[] iaObject) {
		int toRet = iaObject[1] - XMLObject.OBJECT_ENDER.length();
		if (toRet < 0) {
			toRet = iaObject[1];
		}
		return toRet;
	}

	private I_Operators parseOperators(String operatorList) {
		if (log.isDebugEnabled()) {
			log.debug("parseOperators:\n" + operatorList
					+ "\nEnd parseOperators:");
		}

		int iCount = Parser.countTags(operatorList, XMLObject.OBJECT_HEADER);
		String[] toRet = new String[iCount];

		for (int i = 0; i < iCount; i++) {
			if (log.isDebugEnabled()) {
				log.debug("Param.parseOperators operator list is "
						+ operatorList);
			}
			int[] itemHeader = Parser.getTagIndexs(operatorList,
					XMLObject.OBJECT_HEADER, ">");
			int itemEnder = operatorList.indexOf(XMLObject.OBJECT_ENDER);

			toRet[i] = operatorList.substring(itemHeader[1], itemEnder);
			toRet[i] = Parser.unescapeFromXml(toRet[i]);
			if (log.isDebugEnabled()) {
				log.debug("added operator '" + toRet[i] + "'");
			}
			operatorList = operatorList.substring(itemEnder
					+ XMLObject.OBJECT_ENDER.length(), operatorList.length());
		}
		if (log.isDebugEnabled()) {
			log.debug("operators:" + toRet);
		}
		return new Operators(toRet);
	}

	/**
	 * Note no generics or annotations for jme compatibility
	 * @param valueList
	 */
	private void parseValues(String valueList) {
		if (log.isDebugEnabled()) {
			log.debug("parseValues:\n" + valueList + "\nEnd parseValues:");
		}

		int iCount = Parser.countTags(valueList, XMLObject.OBJECT_HEADER);
		values.clear();

		for (int i = 0; i < iCount; i++) {
			if (log.isDebugEnabled()) {
				log.debug("Param.parseValueList:\n" + valueList);
			}
			int[] iItemHeader = Parser.getTagIndexs(valueList,
					XMLObject.OBJECT_HEADER, ">");
			int iItemEnder = valueList.indexOf(XMLObject.OBJECT_ENDER);

			String clazz = Parser.getAttributeValue(valueList.substring(
					iItemHeader[0], iItemHeader[1]), XMLObject.CLASS);
			String content = valueList.substring(iItemHeader[1], iItemEnder);
			content = Parser.unescapeFromXml(content);

			ValueConstructionParams valueConsts = new ValueConstructionParams();
			valueConsts.setClazz(clazz);
			valueConsts.setContent(content);
			if (log.isDebugEnabled()) {
				log.debug("trying to parse '" + content + "' a " + clazz);
			}
			Object toAdd = CLASS_FOR_NAME_VALUE_MAP.createNew(valueConsts);

			if (log.isDebugEnabled()) {
				log.debug("added value '" + toAdd + "'");
			}
			valueList = valueList.substring(iItemEnder
					+ XMLObject.OBJECT_ENDER.length(), valueList.length());
			values.add(toAdd);
			
			if (ValueTypes.STRING.getName().equals(clazz)) {
				valueTypes.add(ValueTypes.STRING);
			} else if (ValueTypes.INTEGER.getName().equals(clazz)) {
				valueTypes.add(ValueTypes.INTEGER);
			} else if (ValueTypes.LONG.getName().equals(clazz)) {
				valueTypes.add(ValueTypes.LONG);
			} else if (ValueTypes.SHORT.getName().equals(clazz)) {
				valueTypes.add(ValueTypes.SHORT);
			} else if (ValueTypes.DOUBLE.getName().equals(clazz)) {
				valueTypes.add(ValueTypes.DOUBLE);
			} else if (ValueTypes.FLOAT.getName().equals(clazz)) {
				valueTypes.add(ValueTypes.FLOAT);
			} else if (ValueTypes.DATE.getName().equals(clazz)) {
				valueTypes.add(ValueTypes.DATE);
			} else if (ValueTypes.BOOLEAN.getName().equals(clazz)) {
				valueTypes.add(ValueTypes.BOOLEAN);
			} else if (ValueTypes.BIG_DECIMAL.getName().equals(clazz)) {
				valueTypes.add(ValueTypes.BIG_DECIMAL);
			} else if (ValueTypes.BIG_INTEGER.getName().equals(clazz)) {
				valueTypes.add(ValueTypes.BIG_INTEGER);
			} 
		}
	}

	private void parseParams(String s) {
		if (log.isDebugEnabled()) {
			log.debug("parseParams:\n" + s + "\nEnd parseParams");
		}
		params = (I_TemplateParams) XMLObject.readXML(s);
	}

	public String getClassVersion() {
		return CLASS_VERSION;
	}

	/*************************************** END I_XML_Serilizable ***************************************************/

	public ValueType[] getValueTypes() {
		ValueType [] toRet = new ValueType[valueTypes.size()];
		for (int i = 0; i < toRet.length; i++) {
			toRet[i] = (ValueType) valueTypes.get(i);
		}
		return toRet;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((operators == null) ? 0 : operators.hashCode());
		result = prime * result + ((params == null) ? 0 : params.hashCode());
		result = prime * result
				+ ((valueTypes == null) ? 0 : valueTypes.hashCode());
		result = prime * result + ((values == null) ? 0 : values.hashCode());
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Param other = (Param) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (operators == null) {
			if (other.operators != null)
				return false;
		} else if (!operators.equals(other.operators))
			return false;
		if (params == null) {
			if (other.params != null)
				return false;
		} else if (!params.equals(other.params))
			return false;
		if (valueTypes == null) {
			if (other.valueTypes != null)
				return false;
		} else if (!valueTypes.equals(other.valueTypes))
			return false;
		if (values == null) {
			if (other.values != null)
				return false;
		} else if (!values.equals(other.values))
			return false;
		return true;
	}

}