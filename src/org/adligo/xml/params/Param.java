package org.adligo.xml.params;

/**
 * Description:  This class is simply used to hold information in a logical
 *              fashion.
 * Copyright:    GPL http://www.adligo.com/gpl.html
 * Company:      Adligo
 * @author       scott@adligo.com
 * @version 1.3
 */
import java.util.Vector;
import org.adligo.xml.XMLObject;
import org.adligo.xml.Parser;
import org.adligo.i.persistence.I_TemplateParams;
import org.apache.commons.logging.*;

public class Param implements I_TemplateParams {
  static final Log log = LogFactory.getLog(Param.class);
  /**
   * this version number represents the xml format and should be incremented
   * only if the format changes
   */
  public static final String CLASS_VERSION = new String("1.4");
  private String name;
  private String [] values;
  private I_TemplateParams params;
  private boolean bAlreadyGotMe = false;
  private int [] iaOptions = null;
  private I_TemplateParams parent;
  /**
  * Constructors
  */

  protected Param() {}

  public Param(String pName, String [] pValues, I_TemplateParams pParams) {
    name = pName;
    values = pValues;
    params = pParams;
  }
  public Param(String pName, String [] pValues, I_TemplateParams pParams, int [] pOptions) {
    name = pName;
    values = pValues;
    params = pParams;
    iaOptions = pOptions;
  }

  public void setParent(I_TemplateParams p) { parent = p ; }
  public I_TemplateParams getParent() { return parent; }
  public void setName(String s) { name = s; }
  public void setValues(String [] s) { values = s;}
  public void setParams(I_TemplateParams pParams) { params = pParams; }
  public I_TemplateParams getNestedParams() { return params; }
  public String getName() { return name; }
  public String [] getValues() {
    if (values == null) {
      return new String[] {""};
    }
    return values;
  }
  //do nothing for these
  public void First() { bAlreadyGotMe = false;}
  public boolean getNextParam(String s) {
    if (s.equals(name) && bAlreadyGotMe == false ){
      bAlreadyGotMe = true;
      return true;
    }
    return false;
  }

  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append(name);
    sb.append(" , [");
    sb.append(values);
    sb.append("] ");
    if (params != null) {
        sb.append(params.toString());
    }
    return sb.toString();
  }
  public void setOptions(int [] p) { iaOptions = p; }
  public int [] getOptions() { return iaOptions; }
  public boolean hasOption( int i) {
    if (iaOptions == null ) {
      return false;
    } else {
      for (int iC = 0; iC < iaOptions.length; iC++) {
        if (iaOptions[iC] == i) {
          return true;
        }
      }
    }
    return false;
  }

  /*************************************** I_XML_Serilizable ***************************************************/
  public String writeXML() { return writeXML(null); }

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
    sb.append("\"  ");
    if (s != null) {
      sb.append(XMLObject.NAME);
      sb.append("=\"");
      sb.append(s);
      sb.append("\"");
    }

    sb.append(">\n");
    sb.append("   ");
    sb.append(XMLObject.OBJECT_HEADER);
    sb.append(" ");
    sb.append(XMLObject.NAME);
    sb.append("=\"name\">");
    sb.append(this.getName());
    sb.append(XMLObject.OBJECT_ENDER);
    sb.append("\n");

    if (iaOptions != null) {
      sb.append("   ");
      sb.append(XMLObject.OBJECT_HEADER);
      sb.append(" ");
      sb.append(XMLObject.NAME);
      sb.append("=\"iaOptions\">\n");
      for (int i = 0; i < iaOptions.length; i++) {
          sb.append("      ");
          sb.append(XMLObject.ELEMENT_HEADER);
          sb.append(">");
          sb.append(iaOptions[i]);
          sb.append(XMLObject.ELEMENT_ENDER);
          sb.append("\n");
      }
      sb.append("   ");
      sb.append(XMLObject.OBJECT_ENDER);
      sb.append("\n");
    }
    if (values != null) {
      sb.append("    ");
      sb.append(XMLObject.OBJECT_HEADER);
      sb.append(" ");
      sb.append(XMLObject.NAME);
      sb.append("=\"values\" ");
      sb.append(">\n");
      for (int i = 0; i < values.length; i++) {
          sb.append("      ");
          sb.append(XMLObject.ELEMENT_HEADER);
          sb.append(">");
          sb.append((String) values[i]);
          sb.append(XMLObject.ELEMENT_ENDER);
          sb.append("\n");
      }
      sb.append("   ");
      sb.append(XMLObject.OBJECT_ENDER);
      sb.append("\n");
    }
    if (params != null) {
      sb.append(Parser.tab(params.writeXML("params"),"   "));
    }
    sb.append(XMLObject.OBJECT_ENDER);
    sb.append("\n");
    if (log.isDebugEnabled()) {
      log.debug(sb.toString());
    }
    return sb.toString();
  }

  public void readXML(String s) {
    if (log.isDebugEnabled()) {
      log.debug("Param.readXML\n" + s);
    }
    int [] iaObject = Parser.getTagIndexs(s,XMLObject.OBJECT_HEADER, XMLObject.OBJECT_ENDER); // get first object tag
    int [] iaHeader = Parser.getTagIndexs(s,XMLObject.OBJECT_HEADER, ">" ); // get first header tag
    for (int i = 0; i < 4; i++) {
      if (iaHeader[1] > 10 && iaHeader[0] > 0 && iaHeader[1] > 0) {
        String sName = Parser.getAttributeValue(s.substring(iaHeader[0], iaHeader[1]), XMLObject.NAME);
        parseObject(sName, s.substring(iaObject[0], iaObject[1]));
        s = s.substring(iaObject[1], s.length());
        iaObject = Parser.getTagIndexs(s,XMLObject.OBJECT_HEADER, XMLObject.OBJECT_ENDER); // get next object tag
        iaHeader = Parser.getTagIndexs(s,XMLObject.OBJECT_HEADER, ">" ); // get next header tag
      }
    }
  }

  private void parseObject(String sName,String sObjectXML) {
    if(sName.equals("name") ) {
      parseName(sObjectXML);
    } else if (sName.equals("values")) {
      parseValues(sObjectXML);
    } else if (sName.equals("iaOptions")) {
      parseOptions(sObjectXML);
    } else if (sName.equals("params")) {
      parseParams(sObjectXML);
    }
  }

  private void parseName(String s) {
    if (log.isDebugEnabled()) {
      log.debug("parseName:\n" + s + "\n End parseName");
    }
    s = s.substring(Parser.getTagIndexs(s, XMLObject.OBJECT_HEADER, ">")[1], s.length());
    int iLastChar = s.indexOf(XMLObject.OBJECT_ENDER);
    name = s.substring(0, iLastChar);
    if (log.isDebugEnabled()) {
      System.out.println("Setting Name:" + name);
    }
  }

  private void parseValues(String s) {
    if (log.isDebugEnabled()) {
      log.debug("parseValues:\n" + s + "\nEnd parseValues:");
    }
    int iCount = Parser.countTags(s, XMLObject.ELEMENT_HEADER);
    values = new String[iCount];
    int [] iHeader = Parser.getTagIndexs(s, XMLObject.ELEMENT_HEADER, ">");
    int iElementEnder = s.indexOf(XMLObject.ELEMENT_ENDER);

    for (int i = 0; i < iCount; i++) {
      if (log.isDebugEnabled()) {
        log.debug("Param.parseValues" + s);
      }
      values[i] = s.substring(iHeader[1], iElementEnder);
      if (log.isDebugEnabled()) {
        log.debug("added value\n" + values[i]);
      }
      s = s.substring(iElementEnder + XMLObject.ELEMENT_ENDER.length(), s.length());
      iElementEnder = s.indexOf(XMLObject.ELEMENT_ENDER);
      iHeader =  Parser.getTagIndexs(s, XMLObject.ELEMENT_HEADER,">");
    }
    if (log.isDebugEnabled()) {
      log.debug("values:" + values);
    }
  }

  private void parseOptions(String s) {
    if (log.isDebugEnabled()) {
      log.debug("parseValues:\n" + s + "\nEnd parseValues:");
    }
    int iCount = Parser.countTags(s, XMLObject.ELEMENT_HEADER);
    values = new String[iCount];
    int [] iHeader = Parser.getTagIndexs(s, XMLObject.ELEMENT_HEADER, ">");
    int iElementEnder = s.indexOf(XMLObject.ELEMENT_ENDER);
    iaOptions = new int[iCount];

    for (int i = 0; i < iCount; i++) {
      if (log.isDebugEnabled()) {
        log.debug("Param.parseValues" + s);
      }
      iaOptions[i] = Integer.parseInt(s.substring(iHeader[1], iElementEnder));
      if (log.isDebugEnabled()) {
        log.debug("added value\n" + values[i]);
      }
      s = s.substring(iElementEnder + XMLObject.ELEMENT_ENDER.length(), s.length());
      iElementEnder = s.indexOf(XMLObject.ELEMENT_ENDER);
      iHeader =  Parser.getTagIndexs(s, XMLObject.ELEMENT_HEADER,">");
    }
    if (log.isDebugEnabled() ) {
      log.debug("values:" + values);
    }
  }

  private void parseParams(String s) {
    if (log.isDebugEnabled() ) {
      log.debug("parseParams:\n" + s + "\nEnd parseParams");
    }
    int [] iaObject = Parser.getTagIndexs(s, XMLObject.OBJECT_HEADER, XMLObject.OBJECT_ENDER);
    params = (I_TemplateParams) XMLObject.readXML(s.substring(iaObject[0],iaObject[1]));
  }

  public String getClassVersion() { return CLASS_VERSION; }
  /*************************************** END I_XML_Serilizable ***************************************************/
}