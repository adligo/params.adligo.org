package org.adligo.xml.params;

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

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.adligo.xml.XMLObject;
import org.adligo.xml.Parser;
import java.lang.reflect.*;
import org.adligo.i.persistence.I_TemplateParams;
import org.adligo.i.persistence.I_MultipleParamsObject;
import org.adligo.i.persistence.I_XML_Serilizable;

import org.apache.commons.logging.*;

public class Params implements  I_MultipleParamsObject {
  static final Log log = LogFactory.getLog(Params.class);
  /**
   * this version number represents the xml format and should be incremented
   * only if the format changes
   */
  public static final String CLASS_VERSION = new String("1.4");
  public List paramsList = new ArrayList();// holds TemplateParam objects
  public int iParam = 0; // the current starting point for searching through paramsList
  public int iTimesForThisParam = 0;
  public I_TemplateParams param; // the current param that was selected by
                       //getNextParam(String s)

  /** Constructors */
  public Params() {}

  /**
   * This creates a Param object using the parameters and adds it to
   * the Vector of Param objects.
   */
  public void addParam(String name, String[] values, I_TemplateParams params) {
    addParam(new Param(name,values, params));
  }
  /**
   * This creates a Param object using the parameters and adds it to
   * the Vector of Param objects.
   */
  public void addParam(String name, String[] values, I_TemplateParams params, int [] pOptions) {
    addParam(new Param(name,values, params, pOptions));
  }
  /**
   * Adds a I_TemplateParams to the vector of params
   */
  public void addParam(I_TemplateParams p) {
    paramsList.add(p);
    try {
      ((Param) p).setParent((I_TemplateParams) this);
    } catch (ClassCastException x) {}
  }

  public void removeParam(I_TemplateParams p) { paramsList.remove(p); }

  /**
   *  Implementation of I_TemplateParams see the interfaces documentation.
   */
  public void First() { iParam = 0; }
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
    if (name == null) {
	    Iterator it = paramsList.iterator();
	    while (it.hasNext()) {
	      I_TemplateParams my_ps = (I_TemplateParams) it.next();
	      if (my_ps.getName() == null) {
	        it.remove();
	      } 
	    }
    } else {
      	Iterator it = paramsList.iterator();
	    while (it.hasNext()) {
	      I_TemplateParams my_ps = (I_TemplateParams) it.next();
	      if (name.equals(my_ps.getName())) {
	           it.remove();
	      }
	    }
    }
  }
  /**
   *  Implementation of I_TemplateParams see the interfaces documentation.
   */
  public boolean getNextParam(String s) {
    if (s == null) {
      return false;
    }
    String sName = new String();
    if (log.isDebugEnabled()) {
      log.debug("getNextParamFool =" + s);
      log.debug("starting at index =" + iParam);
    }
    int iSize = paramsList.size();
    for (int i = iParam; iSize > i; i++) {
        sName = ((I_TemplateParams) paramsList.get(i)).getName();
        if (log.isDebugEnabled()) {
            log.debug(sName);
        }
        if (sName == null) {
          return false;
        }
        if ( sName.equals(s) ) {
          param = (I_TemplateParams) paramsList.get(i);
          try {
            I_MultipleParamsObject i_mpo = (I_MultipleParamsObject) param;
            param.getNextParam(s);
          } catch (ClassCastException e) {}
          if (log.isDebugEnabled()) {
            log.debug("returned " + i);
          }
          iParam = i + 1;
          return true;
        }
    }
    if (log.isDebugEnabled()) {
      log.debug("getNextParamFool =" + s);
    }
    return false;
  }

  public String toString() {
    String s = "Params to String \n";
    for (int i = 0; i < paramsList.size(); i++) {
      s = s + paramsList.get(i).toString();
    }
    return s;
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
    for (int i = 0; i < paramsList.size(); i++) {
       sb.append(Parser.tab( ((I_XML_Serilizable) paramsList.get(i)).writeXML(),"      "));
       if (log.isDebugEnabled()) {
        log.debug(sb.toString());
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
}