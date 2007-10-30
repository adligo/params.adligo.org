package org.adligo.xml.params;

import org.adligo.i.persistence.I_TemplateParams;
import java.util.List;
import java.util.ArrayList;

public class NParamContainer implements I_OneOrN {
   private List <I_TemplateParams> items = new ArrayList();
   
    public int size() { return items.size(); }

	public I_TemplateParams get(int counter) {
		if (counter >= items.size()) {
			return null;
		}
		return items.get(counter);
	}
	
	public void addItem(I_TemplateParams item) {
		items.add(item);
	}
   
	public void removeItem(I_TemplateParams item) {
		items.remove(item);
	}
}
