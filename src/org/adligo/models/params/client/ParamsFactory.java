package org.adligo.models.params.client;

public class ParamsFactory {
	
	public static Params byId(long id) {
		Params params = new Params();
		params.addParam("default");
		Params whereParams = new Params();
		params.addParam("where", whereParams);
		whereParams.addParam("id", SqlOperators.EQUALS, id);
		return params;
	}
	
	public static Params byId(int id) {
		Params params = new Params();
		params.addParam("default");
		Params whereParams = new Params();
		params.addParam("where", whereParams);
		whereParams.addParam("id", SqlOperators.EQUALS, id);
		return params;
	}
	
	public static Params byId(String id) {
		Params params = new Params();
		params.addParam("default");
		Params whereParams = new Params();
		params.addParam("where", whereParams);
		whereParams.addParam("id", SqlOperators.EQUALS, id);
		return params;
	}
	
}
