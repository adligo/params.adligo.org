package org.adligo.xml.params;

import org.adligo.i.persistence.I_TemplateParams;

public class SingleParamContainer implements I_OneOrN {
   private I_TemplateParams item = null;
   
   public int size() { return 1; }

	public I_TemplateParams get(int counter) {
		if (counter >= 1) {
			return null;
		}
		return item;
	}
	
	public void setItem(I_TemplateParams item) {
		this.item = item;
	}
   
   
}
