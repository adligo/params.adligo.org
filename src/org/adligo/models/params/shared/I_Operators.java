package org.adligo.models.params.shared;

/**
 * this is basicly a marker interface around a String []
 * since (new String[] {"foo"}).equals(new String[] {"foo"})
 * returns false, i needed to have a wrapper 
 * 
 * sigh
 * 
 * @author scott
 *
 */
public interface I_Operators {
	public String[] getValues();
}
