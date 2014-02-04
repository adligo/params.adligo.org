package org.adligo.models.params.client;

import java.io.Serializable;
/**
 * Description: This is the interface for any object that wants to be fed to the
 * TemplateParserEngine to be used as the data to fill the templates mold. 
 * 
 * NOTE to make this compatible with jme I couldn't use Serializable
 * so if you want to Serialize instances of this class 
 * use read and write xml from I_XML_Serilizable
 * 
 * 
 * @author scott@adligo.com
 * @version 1.3
 */

public interface I_TemplateParams extends  I_XML_Serilizable {
	/**
	 * This should return true if there is a param with the name = s or false if
	 * there isn't. Also this method should set up the I_TemplateParams
	 * implementation object so that getValues() returns the String[] values for
	 * string s.
	 */
	public boolean getNextParam(String s);

	/**
	 * This should return the values for the current param. The current param is
	 * selected with the getNextParam(String s) method.
	 * 
	 * Note that generally the toString value is called for generic template
	 * parsing, however if you are intending your parameters to end up in sql
	 * (jdbc) then the setString, setInteger setDate of PrepairedStatement will
	 * be called
	 */
	public Object[] getValues();

	/**
	 * this is just a optimization for
	 * determining the types of the values
	 * see ValueTypes.  This is so the
	 * parser (server) doesn't need
	 * to determine the various types
	 * by calling instance of
	 * @return
	 */
	public ValueType[] getValueTypes();
	/**
	 * This should return the I_TemplateParams nested inside the current param.
	 * This should return null if there are no nested I_TemplateParams
	 */
	public I_TemplateParams getNestedParams();

	/**
	 * This should return move the param pointer to the first param in the
	 * object.
	 */
	public void First();

	public String getName();

	public I_Operators getOperators();
}
