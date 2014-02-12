package org.adligo.models.params.shared;

/**
 * Title:
 * Description: <p>This is a generic and reuseable implementation of the
 *              I_TemplateParams interface.  It relies on the TemplateParam
 *              class for storing the name, values and nested I_TemplateParams.
 * Company:      Adligo
 * @author       scott@adligo.com
 * @version 1.3
 */

import java.util.Date;

import org.adligo.i.log.shared.Log;
import org.adligo.i.log.shared.LogFactory;
import org.adligo.i.util.shared.I_Iterator;
import org.adligo.i.util.shared.I_Map;
import org.adligo.i.util.shared.MapFactory;
import org.adligo.i.util.shared.StringUtils;

public class Params implements I_MultipleParamsObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 16L;

	static final Log log = LogFactory.getLog(Params.class);
	/**
	 * this version number represents the xml format and should be incremented
	 * only if the format changes
	 */
	public static final String CLASS_VERSION = new String("1.7");
	private I_Map // String, I_OneOrN
	paramsMap = MapFactory.create();// holds TemplateParam objects
	private I_OneOrN m_currentGroup = null;
	private int counntForThisName = 0;
	private I_TemplateParams param; // the current param that was selected by

	// getNextParam(String s)

	/** Constructors */
	public Params() {
	}

	//TODO create copy constructor
	
	/**
	 * This creates a Param object using the parameters and adds it to the
	 * Collection of Param objects.
	 */
	public Param addParam(String name, String value, I_TemplateParams params) {
		Param parm = new Param(name,params);
		parm.setValue(value);
		addParam(parm);
		return parm;
	}

	public Param addParam(String name, String value) {
		Param parm = new Param(name);
		parm.setValue(value);
		addParam(parm);
		return parm;
	}
		
	public Param addParam(String name, int value, I_TemplateParams params) {
		Param parm = new Param(name,params);
		parm.setValue(value);
		addParam(parm);
		return parm;
	}

	public Param addParam(String name, int value) {
		Param parm = new Param(name);
		parm.setValue(value);
		addParam(parm);
		return parm;
	}
	
	public Param addParam(String name, short value, I_TemplateParams params) {
		Param parm = new Param(name,params);
		parm.setValue(value);
		addParam(parm);
		return parm;
	}

	public Param addParam(String name, short value) {
		Param parm = new Param(name);
		parm.setValue(value);
		addParam(parm);
		return parm;
	}
	
	public Param addParam(String name, long value, I_TemplateParams params) {
		Param parm = new Param(name,params);
		parm.setValue(value);
		addParam(parm);
		return parm;
	}

	public Param addParam(String name, long value) {
		Param parm = new Param(name);
		parm.setValue(value);
		addParam(parm);
		return parm;
	}
	
	public Param addParam(String name, double value, I_TemplateParams params) {
		Param parm = new Param(name,params);
		parm.setValue(value);
		addParam(parm);
		return parm;
	}

	public Param addParam(String name, double value) {
		Param parm = new Param(name);
		parm.setValue(value);
		addParam(parm);
		return parm;
	}
	
	public Param addParam(String name, float value, I_TemplateParams params) {
		Param parm = new Param(name,params);
		parm.setValue(value);
		addParam(parm);
		return parm;
	}

	public Param addParam(String name, float value) {
		Param parm = new Param(name);
		parm.setValue(value);
		addParam(parm);
		return parm;
	}
	
	public Param addParam(String name, Date value, I_TemplateParams params) {
		Param parm = new Param(name,params);
		parm.setValue(value);
		addParam(parm);
		return parm;
	}

	public Param addParam(String name, Date value) {
		Param parm = new Param(name);
		parm.setValue(value);
		addParam(parm);
		return parm;
	}
	
	public Param addParam(String name, boolean value, I_TemplateParams params) {
		Param parm = new Param(name,params);
		parm.setValue(value);
		addParam(parm);
		return parm;
	}
	
	public Param addParam(String name, boolean value) {
		Param parm = new Param(name);
		parm.setValue(value);
		addParam(parm);
		return parm;
	}	

	/**
	 * returns the parameter created
	 * @param name
	 * @return
	 */
	public Param addParam(String name) {
		Param parm = new Param(name);
		addParam(parm);
		return parm;
	}
	
	/**
	 * returns the children of the parameter created
	 * @param name
	 * @return
	 */
	public Params addParams(String name) {
		Params toRet = new Params();
		Param parm = new Param(name,toRet);
		addParam(parm);
		return toRet;
	}
	
	public Params addWhereParams() {
		return this.addParams(Param.WHERE);
	}
	
	public Param addParam(String name, I_TemplateParams params) {
		Param parm = new Param(name,params);
		addParam(parm);
		return parm;
	}
	
	public Param addParam(String name, String operator, String value ) {
		Param parm = new Param(name,operator, value);
		addParam(parm);
		return parm;
	}
	
	public Param addParam(String name, String operator, int value ) {
		Param parm = new Param(name,operator, value);
		addParam(parm);
		return parm;
	}

	public Param addParam(String name, String operator, short value ) {
		Param parm = new Param(name,operator, value);
		addParam(parm);
		return parm;
	}
	
	public Param addParam(String name, String operator, long value ) {
		Param parm = new Param(name,operator, value);
		addParam(parm);
		return parm;
	}
	
	public Param addParam(String name, String operator, double value ) {
		Param parm = new Param(name,operator, value);
		addParam(parm);
		return parm;
	}
	
	public Param addParam(String name, String operator, float value ) {
		Param parm = new Param(name,operator, value);
		addParam(parm);
		return parm;
	}
	
	public Param addParam(String name, String operator, Date value ) {
		Param parm = new Param(name,operator, value);
		addParam(parm);
		return parm;
	}
	
	public Param addParam(String name, String operator, boolean value ) {
		Param parm = new Param(name,operator, value);
		addParam(parm);
		return parm;
	}
	
	public Param addParam(String name, String [] operators, String value ) {
		Param parm = new Param(name,new Operators(operators));
		parm.setValue(value);
		addParam(parm);
		return parm;
	}
	
	public Param addParam(String name, String [] operators) {
		Param parm = new Param(name,operators);
		addParam(parm);
		return parm;
	}
	public Param addParam(String name, String [] operators, int value ) {
		Param parm = new Param(name,new Operators(operators));
		parm.setValue(value);
		addParam(parm);
		return parm;
	}

	public Param addParam(String name, String [] operators, short value ) {
		Param parm = new Param(name,new Operators(operators));
		parm.setValue(value);
		addParam(parm);
		return parm;
	}
	
	public Param addParam(String name, String [] operators, long value ) {
		Param parm = new Param(name,new Operators(operators));
		parm.setValue(value);
		addParam(parm);
		return parm;
	}
	
	public Param addParam(String name, String [] operators, double value ) {
		Param parm = new Param(name,new Operators(operators));
		parm.setValue(value);
		addParam(parm);
		return parm;
	}
	
	public Param addParam(String name, String [] operators, float value ) {
		Param parm = new Param(name,new Operators(operators));
		parm.setValue(value);
		addParam(parm);
		return parm;
	}
	
	public Param addParam(String name, String [] operators, Date value ) {
		Param parm = new Param(name,new Operators(operators));
		parm.setValue(value);
		addParam(parm);
		return parm;
	}
	public Param addParam(String name, String [] operators, boolean value ) {
		Param parm = new Param(name,new Operators(operators));
		parm.setValue(value);
		addParam(parm);
		return parm;
	}
	
	public Param addParam(String name, I_Operators operators, String value ) {
		Param parm = new Param(name,operators);
		parm.setValue(value);
		addParam(parm);
		return parm;
	}
	
	public Param addParamBigDecimal(String name, I_Operators operators, String value ) {
		Param parm = new Param(name,operators);
		parm.setValueBigDecimal(value);
		addParam(parm);
		return parm;
	}
	
	public Param addParamBigInteger(String name, I_Operators operators, String value ) {
		Param parm = new Param(name,operators);
		parm.setValueBigInteger(value);
		addParam(parm);
		return parm;
	}
	
	public Param addParam(String name, I_Operators operators) {
		Param parm = new Param(name,operators);
		addParam(parm);
		return parm;
	}
	public Param addParam(String name, I_Operators operators, int value ) {
		Param parm = new Param(name,operators);
		parm.setValue(value);
		addParam(parm);
		return parm;
	}

	public Param addParam(String name, I_Operators operators, short value ) {
		Param parm = new Param(name,operators);
		parm.setValue(value);
		addParam(parm);
		return parm;
	}
	
	public Param addParam(String name, I_Operators operators, long value ) {
		Param parm = new Param(name,operators);
		parm.setValue(value);
		addParam(parm);
		return parm;
	}
	
	public Param addParam(String name, I_Operators operators, double value ) {
		Param parm = new Param(name,operators);
		parm.setValue(value);
		addParam(parm);
		return parm;
	}
	
	public Param addParam(String name, I_Operators operators, float value ) {
		Param parm = new Param(name,operators);
		parm.setValue(value);
		addParam(parm);
		return parm;
	}
	
	public Param addParam(String name, I_Operators operators, Date value ) {
		Param parm = new Param(name,operators);
		parm.setValue(value);
		addParam(parm);
		return parm;
	}
	public Param addParam(String name, I_Operators operators, boolean value ) {
		Param parm = new Param(name,operators);
		parm.setValue(value);
		addParam(parm);
		return parm;
	}
	
	/**
	 * Adds a I_TemplateParams to the vector of params
	 */
	public void addParam(I_TemplateParams p) {
		if (p == null) {
			throw new NullPointerException("Can't contain a null item");
		}
		if (p.getName() == null) {
			throw new NullPointerException("Can't contain a param "
					+ "with a null name");
		}
		I_OneOrN container = (I_OneOrN) paramsMap.get(p.getName());
		if (container == null) {
			SingleParamContainer toAdd = new SingleParamContainer();
			toAdd.setItem(p);
			paramsMap.put(p.getName(), toAdd);
		} else if (container.size() == 1) {
			NParamContainer newGroup = new NParamContainer();
			newGroup.addItem(container.get(0));
			newGroup.addItem(p);
			paramsMap.put(p.getName(), newGroup);
		} else {
			NParamContainer currentGroup = (NParamContainer) container;
			currentGroup.addItem(p);
		}

		try {
			((Param) p).setParent(this);
		} catch (ClassCastException x) {
		}
	}

	public void removeParam(I_TemplateParams p) {
		if (p == null) {
			throw new NullPointerException("Can't contain a null item");
		}
		if (p.getName() == null) {
			throw new NullPointerException("Can't contain a param "
					+ "with a null name");
		}
		I_OneOrN container = (I_OneOrN) paramsMap.get(p.getName());
		if (container.size() == 1) {
			paramsMap.remove(p.getName());
		} else {
			NParamContainer currentGroup = (NParamContainer) container;
			currentGroup.removeItem(p);
		}
	}

	/**
	 * Implementation of I_TemplateParams see the interfaces documentation.
	 */
	public void First() {
		m_currentGroup = null;
		counntForThisName = 0;
	}

	/**
	 * Implementation of I_TemplateParams see the interfaces documentation.
	 */
	public Object[] getValues() {
		if (param != null) {
			return param.getValues();
		}
		return null;
	}

	/**
	 * Implementation of I_TemplateParams see the interfaces documentation.
	 */
	public I_Operators getOperators() {
		if (param != null) {
			return param.getOperators();
		}
		return null;
	}

	public String getName() {
		String r = new String("");
		if (param != null) {
			r = param.getName();
		}
		return r;
	}

	/**
	 * Implementation of I_TemplateParams see the interfaces documentation.
	 */
	public I_TemplateParams getNestedParams() {
		if (param != null) {
			return param.getNestedParams();
		}
		return null;
	}

	public I_TemplateParams getCurrentParam() {
		return param;
	}

	public void removeAllParams(String name) {
		paramsMap.remove(name);
	}

	/**
	 * Implementation of I_TemplateParams see the interfaces documentation.
	 */
	public boolean getNextParam(String s) {
		if (s == null) {
			return false;
		}
		if (log.isDebugEnabled()) {
			log.debug("getNextParamFool =" + s);
		}
		I_OneOrN currentGroup = (I_OneOrN) this.paramsMap.get(s);

		if (currentGroup == null) {
			if (log.isDebugEnabled()) {
				log.debug("got null I_OneOrN returning");
			}
			param = null;
			return false;
		}
		if (m_currentGroup != null) {
			// yes make sure their the same instace
			if (m_currentGroup == currentGroup) {
				counntForThisName++;
				if (log.isDebugEnabled()) {
					log.debug("got same I_OneOrN count is now "
							+ counntForThisName);
				}
				param = m_currentGroup.get(counntForThisName);
				if (param == null) {
					return false;
				} else {
					return true;
				}
			}
		}
		m_currentGroup = currentGroup;

		param = m_currentGroup.get(0);
		if (param == null) {
			return false;
		} else {
			return true;
		}
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(Params.class.getName());
		sb.append("[\n");

		I_Iterator it = paramsMap.getValuesIterator();
		boolean first = true;
		while (it.hasNext()) {
			if (!first) {
				sb.append(",\n");
			}
			Object val = it.next();
			sb.append(val);
		}
		sb.append("\n]");
		return sb.toString();
	}

	/*************************************** I_XML_Serilizable ***************************************************/
	public String writeXML() {
		XMLBuilder builder = new XMLBuilder();
		writeXML(builder);
		return builder.toXmlString();
	}

	public void writeXML(I_XMLBuilder sb) {
		writeXML(sb, "");
	}

	public void writeXML(I_XMLBuilder sb, String name) {
		sb.indent();
		sb.append(XMLObject.OBJECT_HEADER);
		sb.append(" ");
		sb.append(XMLObject.CLASS);
		sb.append("=\"");
		sb.append(ClassForNameMap.PARAMS_SHORT_NAME);
		sb.append("\" ");
		sb.append(XMLObject.VERSION);
		sb.append("=\"");
		sb.append(CLASS_VERSION);
		sb.append("\"  ");
		sb.append(XMLObject.NAME);
		sb.append("=\"");
		if (!StringUtils.isEmpty(name)) {
			sb.append(name);
		} else {
			sb.append(Param.PARAMS);
		}
		sb.append("\" >");
		sb.lineFeed();
		sb.addIndentLevel();

		I_Iterator it = paramsMap.getValuesIterator();
		while (it.hasNext()) {
			I_OneOrN items = (I_OneOrN) it.next();
			for (int i = 0; i < items.size(); i++) {
				((I_XML_Serilizable) items.get(i)).writeXML(sb);
				if (log.isDebugEnabled()) {
					log.debug(sb.toString());
				}
			}
		}

		sb.removeIndentLevel();
		sb.indent();
		sb.append(XMLObject.OBJECT_ENDER);
		sb.lineFeed();
		if (log.isDebugEnabled()) {
			log.debug(sb.toString());
		}
	}

	public void readXML(String s) {
		readXML(s, null);
	}

	public void readXML(String s, String name) {
		if (log.isDebugEnabled()) {
			log.debug("Reading XML in Params\n" + s);
		}
		int[] iaVectorTags = Parser.getTagIndexs(s, XMLObject.OBJECT_HEADER,
				XMLObject.OBJECT_ENDER); // get vector element
		s = s.substring(iaVectorTags[0], iaVectorTags[1]);
		int[] iaVectorHeader = Parser.getTagIndexs(s, XMLObject.OBJECT_HEADER,
				">"); // get vector header
		String sVectorHeader = s
				.substring(iaVectorHeader[0], iaVectorHeader[1]);

		if (name == null) {
			name = Parser.getAttributeValue(sVectorHeader, XMLObject.NAME);
		}

		int[] iaObject = Parser.getTagIndexs(s, XMLObject.OBJECT_HEADER, ">");
		s = s.substring(iaObject[1] + 1, s.length()); // remove object header
														// name=vParmas
		iaObject = Parser.getTagIndexs(s, XMLObject.OBJECT_HEADER,
				XMLObject.OBJECT_ENDER);

		while (iaObject[1] > 10 && iaObject[0] >= 0) {
			String sVectorObject = s.substring(iaObject[0], iaObject[1]);
			if (log.isDebugEnabled()) {
				log.debug("readXML:\n" + sVectorObject);
			}
			this.addParam((I_TemplateParams) XMLObject.readXML(sVectorObject));
			s = s.substring(iaObject[1] + 1, s.length());
			iaObject = Parser.getTagIndexs(s, XMLObject.OBJECT_HEADER,
					XMLObject.OBJECT_ENDER);
		}
	}

	public String getClassVersion() {
		return CLASS_VERSION;
	}

	/*************************************** END I_XML_Serilizable ***************************************************/

	/**
	 * This is a utility method for manipulateing I_TemplateParams object this
	 * method encapsulates the ability to add a param to another param object
	 * with out adding duplicate (named getName()) params
	 * 
	 * @return the pAddTo object if not null the p object if pAddTo is null and
	 *         p is not null
	 * 
	 * @param pAddTo
	 *            the param to add the other param to
	 * @param p
	 *            the param to add so pAddTo.add(p);
	 * @param bAddDuplicate
	 *            if it should add a pram if there is already one with the same
	 *            name
	 */
	public static I_TemplateParams addParam(I_TemplateParams pAddTo,
			I_TemplateParams p, boolean bAddDuplicate) {
		if (pAddTo == null) {
			return p;
		} else {
			pAddTo.First();
			// if there isn't a param already
			if (!pAddTo.getNextParam(p.getName())) {
				return addParamToParam(pAddTo, p);
			} else if (bAddDuplicate) {
				return addParamToParam(pAddTo, p);
			} else {
				// didn't add anything so we return the same thing
				return pAddTo;
			}
		}
	}

	/**
	 * Adds param p to pAddTo with out looking for dups
	 * 
	 * @param pAddTo
	 * @param p
	 * @return pAddTo
	 */
	private static I_TemplateParams addParamToParam(I_TemplateParams pAddTo,
			I_TemplateParams p) {
		if (pAddTo instanceof I_MultipleParamsObject) {
			((I_MultipleParamsObject) pAddTo).addParam(p);
			return pAddTo;
		} else {
			Params params = new Params();
			params.addParam(pAddTo);
			params.addParam(p);
			return params;
		}
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((paramsMap == null) ? 0 : paramsMap.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Params other = (Params) obj;
		if (paramsMap == null) {
			if (other.paramsMap != null)
				return false;
		} else if (!paramsMap.equals(other.paramsMap))
			return false;
		return true;
	}

	public ValueType[] getValueTypes() {
		return this.param.getValueTypes();
	}
}