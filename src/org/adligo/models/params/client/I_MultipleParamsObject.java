package org.adligo.models.params.client;

/**
 * Title: Description: This is a extension to the I_TemplateParams Object it
 * adds the ability to add parameters to the Query Object and also manipulate
 * the parameters of the Query Object
 * 
 * @author scott@adligo.com
 * @version 1.3
 */
public interface I_MultipleParamsObject extends I_TemplateParams {
	public void addParam(I_TemplateParams p);

	/*
	 * returns the last paramter found with getNextParam(String s)
	 */
	public I_TemplateParams getCurrentParam();

	/*
	 * remove all refrences to this object in the current I_MultipleParamsObject
	 * does not recurse into child objects
	 * 
	 * this method will loose the place holder used in getNextParam(String s)
	 */
	public void removeParam(I_TemplateParams p);

	/*
	 * remove all parameter objects with this name in the current
	 * I_MultipleParamsObject does not recurse into child objects
	 * 
	 * this method will loose the place holder used in getNextParam(String s)
	 */
	public void removeAllParams(String name);
}