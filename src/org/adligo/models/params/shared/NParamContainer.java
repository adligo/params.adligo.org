package org.adligo.models.params.shared;

import org.adligo.i.util.shared.ArrayCollection;

public class NParamContainer implements I_OneOrN {
	private ArrayCollection items = new ArrayCollection();

	public int size() {
		return items.size();
	}

	public I_TemplateParams get(int counter) {
		if (counter >= items.size()) {
			return null;
		}
		return (I_TemplateParams) items.get(counter);
	}

	public void addItem(I_TemplateParams item) {
		items.add(item);
	}

	public void removeItem(I_TemplateParams item) {
		items.remove(item);
	}

	public String toString() {
		return "NParamContainer [items=" + items + "]";
	}
}
