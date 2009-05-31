package org.adligo.models.params.client;

/**
 * Probably the mother of all query languages is SQL
 * I think all of these were probably in sql 89
 * but I was looking at sql 92, these should be used unless some special
 * 
 * Your client code should still use something like a I_QueryLangeuageDescriptor
 * to obtain the operator you need, but most sql variants and
 * other query languages will use something like these
 * so the I_QueryLanguageDescriptor implementations probably want to start
 * with these, and override when necessary
 * 
 * @author scott
 *
 */
public class SqlOperators {
	public static final I_Operators EQUALS = new Operators(" = ");
	public static final I_Operators LIKE = new Operators(" LIKE ");
	public static final I_Operators IN = new Operators(new String [] {" IN ( "," ) "});
	public static final I_Operators NOT_IN = new Operators(new String [] {" NOT IN ( "," ) "});
	public static final I_Operators GREATER_THAN = new Operators(new String [] {" > "});
	public static final I_Operators GREATER_THAN_EQUALS = new Operators(new String [] {" >= "});
	public static final I_Operators LESS_THAN = new Operators(new String [] {" < "});
	public static final I_Operators LESS_THAN_EQUALS = new Operators(new String [] {" <= "});
	public static final I_Operators NOT_EQUALS = new Operators(new String [] {" <> "});
	public static final I_Operators IS_NULL = new Operators(new String [] {" IS NULL "});
	public static final I_Operators IS_NOT_NULL = new Operators(new String [] {" NOT IS NULL "});
}
