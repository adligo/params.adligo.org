package org.adligo.models.params.client;

/**
 * Description:  A generic object that can write itself to a xml file
 * 
 * @deprecated the xml_io project is replacing this class with 
 *    a similar concept, but greatly compressed xml that may be interpreted by 
 *    java and non Java languages.  Expected removal 1/1/2020
 *    
 * Company:      Adligo
 * @author       scott@adligo.com
 * @version 1.3
 */
//import java.lang.reflect.*;
import org.adligo.i.log.shared.Log;
import org.adligo.i.log.shared.LogFactory;
import org.adligo.i.util.shared.I_Factory;

public class XMLObject {
	private static final Log log = LogFactory.getLog(XMLObject.class);
	private static I_Factory instanceFactory = new ClassForNameMap();

	public static final String XML_OBJECT_VERSION = new String("1.1");
	public static final String OBJECT_HEADER = new String("<object");
	public static final String OBJECT_ENDER = new String("</object>");
	public static final String CLASS = new String("class");
	public static final String VERSION = new String("version");
	public static final String NAME = new String("name");
	public static final String NULL = new String("null");
	public static final String ARRAY_TYPE = new String("array_type");

	/**
	 * this method will create a object that implements the I_XML_Serilizable
	 * interface from the serilized xml
	 */
	public static Object readXML(String s) {
		if (s == null) {
			log.fatal(" XMLObject.readXML(String s) was passed a null string");
			return null;
		}
		if (s.trim().length() == 0) {
			log.fatal(" XMLObject.readXML(String s) was passed a empty string");
			return null;
		}
		int[] iaObjectHeader = Parser.getTagIndexs(s, OBJECT_HEADER, ">");
		String sClass = Parser.getAttribute(iaObjectHeader, s, CLASS);
		String sVersion = Parser.getAttribute(iaObjectHeader, s, VERSION);
		String sName = Parser.getAttribute(iaObjectHeader, s, NAME);
		Object o = null;

		o = instanceFactory.createNew(sClass);
		if (o == null) {
			throw new RuntimeException(
					"A dynamic instance of "
							+ sClass
							+ " could not be created, please create a I_InstanceFactory "
							+ " and use the static setter in this class!");
		}
		if (!((I_XML_Serilizable) o).getClassVersion().equals(sVersion)) {
			log
					.fatal("The version of the class in your JVM is different than \n the version saved "
							+ "in your xml!  The object can't be instantiated!");
			return null;
		} else {
			((I_XML_Serilizable) o).readXML(s, sName);
		}

		return o;
	}

	public static I_Factory getInstanceFactory() {
		return instanceFactory;
	}

	public static void setInstanceFactory(I_Factory instanceFactory) {
		XMLObject.instanceFactory = instanceFactory;
	}
	
	
	public static ClassInfo getClassInfo(String sXmlChunk) {

		if (sXmlChunk == null) {
			log.fatal(" XMLObject.getClassInfo(String s) was passed a null string");
			return null;
		}
		if (sXmlChunk.trim().length() == 0) {
			log.fatal(" XMLObject.getClassInfo(String s) was passed a empty string");
			return null;
		}
		int[] iaObjectHeader = Parser.getTagIndexs(sXmlChunk, OBJECT_HEADER, ">");
		String sClass = Parser.getAttribute(iaObjectHeader, sXmlChunk, CLASS);
		String sVersion = Parser.getAttribute(iaObjectHeader, sXmlChunk, VERSION);
		
		double d = Double.parseDouble(sVersion);
		return new ClassInfo(sClass, d);
	}
}