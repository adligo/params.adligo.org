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
	public static void getLimitOffset(I_TemplateParams params, I_LimitOffset lo) {
		if (params.getNextParam(OFFSET)) {
			Object []  objs = params.getValues();
			if (objs == null) {
				return;
			}
			if (objs.length < 1) {
				return;
			}
			Object obj = objs[0];
			try {
				int offset = (Integer) obj;
				lo.setOffset(offset);
				I_TemplateParams childParams = params.getNestedParams();
				if (childParams != null) {
					if (childParams.getNextParam(NUM_ROWS)) {
						objs = childParams.getValues();
						if (objs == null) {
							return;
						}
						if (objs.length < 1) {
							return;
						}
						obj = objs[0];
						try {
							int limit = (Integer) obj;
							lo.setLimit(limit);
						} catch (ClassCastException x) {
							return;
						}
					}
				}
			} catch (ClassCastException x) {
				return;
			}
		}
	}
	
}
