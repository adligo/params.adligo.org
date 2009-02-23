package org.adligo.models.params.client;

/**
 * Description:  A generic object that can write itself to a xml file
 * Copyright:    GPL http://www.adligo.com/gpl.html
 * Company:      Adligo
 * @author       scott@adligo.com
 * @version 1.3
 */
//import java.lang.reflect.*;

import org.adligo.i.log.client.Log;
import org.adligo.i.log.client.LogFactory;
import org.adligo.i.util.client.I_InstanceFactory;

public class XMLObject {
  private static final Log log = LogFactory.getLog(XMLObject.class);
  private static I_InstanceFactory instanceFactory = new ClassForNameMap();
  
  public static final String XML_OBJECT_VERSION = new String("1.0");
  public static final String OBJECT_HEADER = new String("<object");
  public static final String OBJECT_ENDER = new String("</object>");
  public static final String CLASS = new String("class");
  public static final String VERSION = new String("version");
  public static final String NAME = new String("name");
  public static final String ARRAY_TYPE = new String("array_type");
  public static final String ELEMENT_HEADER = new String("<element");
  public static final String ELEMENT_ENDER = new String("</element>");

  /**
   * this method will create a object that implements the I_XML_Serilizable interface
   * from the serilized xml
   */
  public static Object readXML(String s){
    if (s == null) {
        log.fatal(" XMLObject.readXML(String s) was passed a null string");
        return null;
    }
    if (s.trim().length() == 0 ) {
        log.fatal(" XMLObject.readXML(String s) was passed a empty string");
        return null;
    }
    int [] iaObjectHeader = Parser.getTagIndexs(s, OBJECT_HEADER, ">");
    String sClass = Parser.getAttributeValue(s.substring(iaObjectHeader[0], iaObjectHeader[1]), CLASS);
    String sVersion = Parser.getAttributeValue(s.substring(iaObjectHeader[0], iaObjectHeader[1]), VERSION);
    Object o = null;

    iaObjectHeader = Parser.getTagIndexs(s,OBJECT_HEADER, ">" );
    s = s.substring(iaObjectHeader[1], s.length()); //strip off object header tag
    
	  o = instanceFactory.createInstance(sClass);
	  if (o == null) {
		  throw new RuntimeException("A dynamic instance of " + sClass + 
				  " could not be created, please create a I_InstanceFactory " +
				  " and use the static setter in this class!");
	  }
	  if (! ((I_XML_Serilizable) o ).getClassVersion().equals(sVersion)) {
	    log.fatal("The version of the class in your JVM is different than \n the version saved " +
	                    "in your xml!  The object can't be instantiated!");
	    return null;
	  } else {
	    ((I_XML_Serilizable) o ).readXML(s);
	  }
   
    return o;
  }

	public static I_InstanceFactory getInstanceFactory() {
		return instanceFactory;
	}
	
	public static void setInstanceFactory(I_InstanceFactory instanceFactory) {
		XMLObject.instanceFactory = instanceFactory;
	}
}