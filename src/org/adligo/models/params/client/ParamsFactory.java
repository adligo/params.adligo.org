package org.adligo.models.params.client;

public class ParamsFactory {
	
	public static final String ID = "id";
	public static final String WHERE = "where";
	public static final String DEFAULT = "default";

	public static Params byId(long id) {
		Params params = new Params();
		params.addParam(DEFAULT);
		Params whereParams = new Params();
		params.addParam(WHERE, whereParams);
		whereParams.addParam(ID, SqlOperators.EQUALS, id);
		return params;
	}
	
	public static Params byId(int id) {
		Params params = new Params();
		params.addParam(DEFAULT);
		Params whereParams = new Params();
		params.addParam(WHERE, whereParams);
		whereParams.addParam(ID, SqlOperators.EQUALS, id);
		return params;
	}
	
	public static Params byId(String id) {
		Params params = new Params();
		params.addParam(DEFAULT);
		Params whereParams = new Params();
		params.addParam(WHERE, whereParams);
		whereParams.addParam(ID, SqlOperators.EQUALS, id);
		return params;
	}
	
}
