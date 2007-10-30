package org.adligo.xml.params;

import org.adligo.i.persistence.I_TemplateParams;

public interface I_OneOrN {
  public int size();
  public I_TemplateParams get(int i);
}
