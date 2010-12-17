package org.adligo.models.params.client;

/**
 * Description: Provides a method of saving objects and restoring them just like
 * Serilizable Actually this was written due to problems I was having saving
 * objects to the PostgresSQL database This interface is included in this
 * package because eventually I think it would be nice to send the actual data
 * objects back and forth in xml format instead of serialized java objects in
 * this way it would be easy to write clients and servers in other languages
 * although I am going mostly GWT RPC with all java servers. The exception is
 * when I need a external api.
 * 
 * 
 * @author scott@adligo.com
 * @version 1.3
 */

public interface I_XML_Serilizable {

	/**
	 * this should create a xml file or string that can be used to recreate a
	 * meaningfull object
	 * 
	 * @see writeXML(String s);
	 */
	public String writeXML();

	/**
	 * @see writeXML();
	 * @param s
	 *            the name of the instance variable of this object this is
	 *            useful for parseing a stored object ie; <object
	 *            class="org.adligo.xml.I_XML_Serilizable" version="1.4">
	 *            <object name="ClassLevelInstanceVariable1">text or other
	 *            stuff</object> <object name="myArray"> <element>1</object>
	 *            <object>For suth</object> <object>It's all about wireless (not
	 *            balberings) these days</object> </object> <object
	 *            name="myVector"> <object>Eric Dolphy: 'Out To Lunch' is a
	 *            great record you should buy it!</object>
	 *            <object>Argon-Evolution: 'Human' is a great record you should
	 *            buy it!</object> <object>Don Caballero: 'Don Caballero 2' is a
	 *            great record you should buy it!</object> </object> </object>
	 */
	public String writeXML(XMLBuilder builder);

	public void writeXML(XMLBuilder builder, String name);

	/**
	 * re builds a object from its saved xml *Note this method check for the
	 * class version attribute gotten with the getClassVersion() accessor method
	 * Don't call this directly! It is called from the XMLObject method with the
	 * same name, use that method! ie; Object o = XMLObject.readXML(s);
	 * 
	 * In your implementations of this method expect to get the xml string
	 * missing it head or header as it were. Please view the implementations in
	 * the Param and Params classes in the org.adligo.xml.parsers.template
	 * package.
	 */
	public void readXML(String s);

	public void readXML(String s, String name);

	public String getClassVersion();
}