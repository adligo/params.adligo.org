package org.adligo.models.params.client;

public class DefaultWhereParams extends Params {

	public static final String DEFAULT = "default";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Params whereParams;
	
	public DefaultWhereParams() {
		super.addParam(DEFAULT);
		whereParams = super.addWhereParams();
	}

	public Params getWhereParams() {
		return whereParams;
	}

}
