package org.adligo.models.params.client;

/**
 * Description:  This is the interface for any object that wants to be fed
 *                to the TemplateParserEngine to be used as the data to fill
 *                the templates mold. This class is (and all classes under the org.adligo namespace)
 *                are open-source software which is protected by the GNU GENERAL PUBLIC LICENSE.
 * Copyright:    GPL http://www.adligo.com/gpl.html
 * Company:      Adligo
 * @author       scott@adligo.com
 * @version 1.3
 */

import java.io.Serializable;

public interface I_TemplateParams extends Serializable, I_XML_Serilizable {
  /**
   * This should return true if there is a
   * param with the name = s or false if there isn't.  Also this method should
   * set up the I_TemplateParams implementation object so that getValues()
   * returns the String[] values for string s.
   */
  public boolean getNextParam(String s);
  /**
   * This should return the values for the current param.
   * The current param is selected with the getNextParam(String s) method.
   */
  public String [] getValues();
  /**
   * This should return the I_TemplateParams nested inside the current param.
   * This should return null if there are no nested I_TemplateParams
   */
  public I_TemplateParams getNestedParams();
  /**
   * This should return move the param pointer to the first param in the object.
   */
  public void First();

  public String getName();
  public int [] getOptions();
}