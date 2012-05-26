package org.adligo.models.params.client;

public class ParamsFactory {
	
	public static final String ID = "id";
	public static final String WHERE = "where";
	public static final String DEFAULT = "default";
	/**
	 * top level param for the SQL-98 OFFSET clause
	 */
	public static final String OFFSET = "offset";
	/**
	 * param nested inside the OFFSET param 
	 */
	public static final String NUM_ROWS = "num_rows";

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
	
	/**
	 * allow for passing the offset through the params for return in a
	 *  models_core subset
	 * @param params
	 * @return
	 */
	public static int getOffset(I_TemplateParams params) {
		if (params.getNextParam(OFFSET)) {
			Object []  objs = params.getValues();
			if (objs == null) {
				throw new IllegalArgumentException("Didn't find " + OFFSET + " parameter");
			}
			if (objs.length < 1) {
				throw new IllegalArgumentException("Didn't find " + OFFSET + " parameter");
			}
			Object obj = objs[0];
			try {
				int toRet = (Integer) obj;
				return toRet;
			} catch (ClassCastException x) {
				throw new IllegalArgumentException("Didn't find " + OFFSET + " parameter");
			}
		} else {
			throw new IllegalArgumentException("Didn't find " + OFFSET + " parameter");
		}
	}
}
