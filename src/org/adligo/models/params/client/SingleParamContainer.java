package org.adligo.models.params.client;

public class SingleParamContainer implements I_OneOrN {
	private I_TemplateParams item = null;

	public int size() {
		return 1;
	}

	public I_TemplateParams get(int counter) {
		if (counter >= 1) {
			return null;
		}
		return item;
	}

	public void setItem(I_TemplateParams item) {
		this.item = item;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SingleParamContainer other = (SingleParamContainer) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		return true;
	}

}
