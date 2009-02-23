package org.adligo.models.params.client;

/**
 * Title:
 * Description: <p>This is a generic and reuseable implementation of the
 *              I_TemplateParams interface.  It relies on the TemplateParam
 *              class for storing the name, values and nested I_TemplateParams.
 * Copyright:    GPL http://www.adligo.com/gpl.html
 * Company:      Adligo
 * @author       scott@adligo.com
 * @version 1.3
 */


import org.adligo.i.log.client.Log;
import org.adligo.i.log.client.LogFactory;
import org.adligo.i.util.client.I_Iterator;
import org.adligo.i.util.client.I_Map;
import org.adligo.i.util.client.MapFactory;

public class Params implements  I_MultipleParamsObject {
  static final Log log = LogFactory.getLog(Params.class);
  /**
   * this version number represents the xml format and should be incremented
   * only if the format changes
   */
  public static final String CLASS_VERSION = new String("1.4");
  private I_Map //String, I_OneOrN
  			paramsMap = MapFactory.create();// holds TemplateParam objects
  private I_OneOrN m_currentGroup = null;
  private int counntForThisName = 0;
  private I_TemplateParams param; // the current param that was selected by
                       //getNextParam(String s)

  /** Constructors */
  public Params() {}

  /**
   * This creates a Param object using the parameters and adds it to
   * the Collection of Param objects.
   */
  public void addParam(String name, String[] values, I_TemplateParams params) {
    addParam(new Param(name,values, params));
  }

  public void addParam(String name, String[] values) {
	    addParam(new Param(name,values, null));
  }

  public void addParam(String name, String value) {
	    addParam(new Param(name,new String[] {value}, null));
  }
  /**
   * This creates a Param object using the parameters and adds it to
   * the Collection of Param objects.
   */
  public void addParam(String name, String[] values, I_TemplateParams params, int [] pOptions) {
    addParam(new Param(name,values, params, pOptions));
  }
  /**
   * Adds a I_TemplateParams to the vector of params
   */
  public void addParam(I_TemplateParams p) {
	  if (p == null) {
		  throw new NullPointerException("Can't contain a null item");
	  }
	  if (p.getName() == null) {
		  throw new NullPointerException("Can't contain a param " +
		  		"with a null name");
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
      ((Param) p).setParent((I_TemplateParams) this);
    } catch (ClassCastException x) {}
  }

  public void removeParam(I_TemplateParams p) { 
	  if (p == null) {
		  throw new NullPointerException("Can't contain a null item");
	  }
	  if (p.getName() == null) {
		  throw new NullPointerException("Can't contain a param " +
		  		"with a null name");
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
   *  Implementation of I_TemplateParams see the interfaces documentation.
   */
  public void First() { 
	  m_currentGroup = null;
	  counntForThisName = 0;
  }
  /**
   *  Implementation of I_TemplateParams see the interfaces documentation.
   */
  public String [] getValues() {
    if (param != null) {
      return param.getValues();
    }
    return null;
  }
  /**
   *  Implementation of I_TemplateParams see the interfaces documentation.
   */
  public int [] getOptions() {
    if (param != null) {
      return param.getOptions();
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
   *  Implementation of I_TemplateParams see the interfaces documentation.
   */
  public I_TemplateParams getNestedParams() {
    if (param != null ) {
      return param.getNestedParams();
    }
    return null;
  }
  
  public I_TemplateParams getCurrentParam() { return param; }
  public void removeAllParams(String name) {
	  paramsMap.remove(name);
  }
  /**
   *  Implementation of I_TemplateParams see the interfaces documentation.
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
    	//yes make sure their the same instace
    	if (m_currentGroup == currentGroup) {
    		counntForThisName++;
    		if (log.isDebugEnabled()) {
    		      log.debug("got same I_OneOrN count is now " +
    		    		  counntForThisName);
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
	StringBuilder sb = new StringBuilder();
    sb.append("Params to String \n");
    
    I_Iterator it = paramsMap.getIterator();
    boolean first = true;
    while (it.hasNext()) {
    	if (!first) {
    		sb.append(",");
    	}
    	sb.append(it.next());
    }
    return sb.toString();
  }
  /*************************************** I_XML_Serilizable ***************************************************/
  public String writeXML() { return writeXML(""); }

  public String writeXML(String s) {
    StringBuffer sb = new StringBuffer();
    sb.append(XMLObject.OBJECT_HEADER);
    sb.append(" ");
    sb.append(XMLObject.CLASS);
    sb.append("=\"");
    sb.append(this.getClass().getName());
    sb.append("\" ");
    sb.append(XMLObject.VERSION);
    sb.append("=\"");
    sb.append(CLASS_VERSION);
    sb.append("\"  " );
    if (s != null) {
      sb.append(XMLObject.NAME);
      sb.append("=\"");
      sb.append(s);
      sb.append("\" ");
    }
    sb.append(">\r\n");

    sb.append("   ");
    sb.append(XMLObject.OBJECT_HEADER);
    sb.append(" ");
    sb.append(XMLObject.NAME);
    sb.append("=\"paramsList\" ");
    sb.append(">\r\n");
    
    I_Iterator it = paramsMap.getIterator();
    while (it.hasNext()) {
    	I_OneOrN items = (I_OneOrN) paramsMap.get(it.next());
    	for (int i = 0; i < items.size(); i++) {
	       sb.append(Parser.tab( ((I_XML_Serilizable) items.get(i)).writeXML(),"      "));
	       if (log.isDebugEnabled()) {
	        log.debug(sb.toString());
	       }
	    }
    }
    
    sb.append("   ");
    sb.append(XMLObject.OBJECT_ENDER);
    sb.append("\r\n");
    sb.append(XMLObject.OBJECT_ENDER);
    sb.append("\r\n");
    if (log.isDebugEnabled()) {
      log.debug(sb.toString());
    }
    return sb.toString();
  }

  public void readXML(String s) {
    if (log.isDebugEnabled()) {
      log.debug("Reading XML in Params\n" + s);
    }
    int [] iaVectorTags = Parser.getTagIndexs(s,XMLObject.OBJECT_HEADER, XMLObject.OBJECT_ENDER ); // get vector element
    s = s.substring(iaVectorTags[0], iaVectorTags[1]);
    int [] iaVectorHeader = Parser.getTagIndexs(s,XMLObject.OBJECT_HEADER, ">" ); // get vector header
    String sVectorHeader = s.substring(iaVectorHeader[0], iaVectorHeader[1]);
    //Make sure this is the paramsList Vector object
    if (Parser.getAttributeValue(sVectorHeader, XMLObject.NAME).equals("paramsList") ) {
      int [] iaObject = Parser.getTagIndexs(s, XMLObject.OBJECT_HEADER, ">" );
      s = s.substring(iaObject[1] + 1, s.length()); // remove object header name=vParmas
      String sVectorObject = s.substring(iaObject[0], iaObject[1]);//get first object in paramsList vector
      iaObject = Parser.getTagIndexs(s, XMLObject.OBJECT_HEADER, XMLObject.OBJECT_ENDER );

      while (iaObject[1] > 10 && iaObject[0] >= 0) {
        sVectorObject = s.substring(iaObject[0], iaObject[1]);
        this.addParam((I_TemplateParams) XMLObject.readXML(sVectorObject));
        s = s.substring(iaObject[1] + 1, s.length());
        iaObject = Parser.getTagIndexs(s, XMLObject.OBJECT_HEADER, XMLObject.OBJECT_ENDER );
      }
    }
  }

  public String getClassVersion() { return CLASS_VERSION; }
  /*************************************** END I_XML_Serilizable ***************************************************/
  

  /**
   * This is a utility method for manipulateing I_TemplateParams object
   * this method encapsulates the ability to add a param to another param
   * object with out adding duplicate (named getName()) params 
   * @return 
   *    the pAddTo object if not null 
   *    the p object if pAddTo is null and p is not null
   * 
   * @param pAddTo the param to add the other param to
   * @param p the param to add
   * so pAddTo.add(p);
   * @param bAddDuplicate if it should add a pram if there is already one 
   * with the same name
   */
  public static I_TemplateParams addParam(I_TemplateParams pAddTo, 
  I_TemplateParams p, boolean bAddDuplicate) {
    if ( pAddTo == null) {
      return p;
    } else {
      pAddTo.First();
      // if there isn't a param already
      if (!pAddTo.getNextParam(p.getName())) {
        return addParamToParam(pAddTo, p);
      } else if (bAddDuplicate) {
        return addParamToParam(pAddTo, p);
      }  else {
        // didn't add anything so we return the same thing
        return pAddTo;
      }
    }
  }
  
  /**
   * Adds param p to pAddTo with out looking for dups
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
}