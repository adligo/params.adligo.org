package org.adligo.models.params.client;

import org.adligo.models.params.client.I_TemplateParams;

/**
 * this class can be used to 
 * override certian methods for instance it will be used
 * Also it can be used for adapting the params to
 * the xml template via jdbc.  This can be done by returning
 * a Object[] from getValues of all question marks
 * and keeping track of which question mark is which type
 * so that the resulting String can be compiled into 
 * a jdbc prepaired statement and then the Object can be set with 
 * stmt.setString(index, actualValue);
 * 
 * Also to wrap strings with single quotes 
 * and clean them with I_QueryLanguageDescriptor
 * to provide a 
 * runnable query statement to the program log system
 * or error so that the programmer does not 
 * have to rebuild the query replacing each parameter
 * 
 * @author scott
 *
 */
public class ParamDecorator implements I_TemplateParams {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7337057952332696160L;
	I_TemplateParams delegate;
	
	public ParamDecorator(I_TemplateParams delegate) {
		if (delegate == null) {
			throw new NullPointerException("ParamDecorate can't have a null delegate");
		}
		this.delegate = delegate;
	}
	
	public void First() {
		delegate.First();
	}

	public String getName() {
		return delegate.getName();
	}

	public I_TemplateParams getNestedParams() {
		I_TemplateParams itp = delegate.getNestedParams();
		if (itp == null) {
			return null;
		}
		return new ParamDecorator(itp);
	}

	public boolean getNextParam(String s) {
		return delegate.getNextParam(s);
	}

	public I_Operators getOperators() {
		return delegate.getOperators();
	}

	public Object[] getValues() {
		return delegate.getValues();
	}

	public String getClassVersion() {
		return delegate.getClassVersion();
	}
	
	public void readXML(String s, String name) {
		delegate.readXML(s, name);
	}

	public void readXML(String s) {
		delegate.readXML(s);
	}

	public String writeXML() {
		return delegate.writeXML();
	}

	public void writeXML(I_XMLBuilder builder, String name) {
		delegate.writeXML(builder, name);
	}

	public void writeXML(I_XMLBuilder builder) {
		delegate.writeXML(builder);
	}
	protected synchronized I_TemplateParams getDelegate() {
		return delegate;
	}
	protected synchronized void setDelegate(I_TemplateParams delegate) {
		this.delegate = delegate;
	}
	public ValueType[] getValueTypes() {
		return delegate.getValueTypes();
	}

}
