package org.adligo.models.params.shared;
/**
 * this class could have been a enum, but they don't work on j2me
 * @author scott
 *
 */
public class ValueTypes {
	public static final short STRING_ID = (short) 0;
	public static final short LONG_ID  = (short)1;
	public static final short INTEGER_ID  = (short) 2;
	public static final short SHORT_ID  = (short) 3;
	public static final short DOUBLE_ID  = (short) 4;
	public static final short FLOAT_ID  = (short) 5;
	public static final short DATE_ID  = (short) 6;
	public static final short TIMESTAMP_ID  = (short) 7;
	public static final short BOOLEAN_ID  = (short) 8;
	public static final short BIG_DECIMAL_ID  = (short) 9;
	public static final short BIG_INTEGER_ID  = (short) 10;
	
	public static final ValueType STRING = new ValueType(STRING_ID, "String");
	
	public static final ValueType LONG = new ValueType(LONG_ID,"Long");
	public static final ValueType INTEGER = new ValueType(INTEGER_ID,"Integer");
	public static final ValueType SHORT = new ValueType(SHORT_ID,"Short");
	public static final ValueType DOUBLE = new ValueType(DOUBLE_ID, "Double");
	public static final ValueType FLOAT = new ValueType(FLOAT_ID, "Float");
	public static final ValueType DATE = new ValueType(DATE_ID,"Date");
	public static final ValueType TIMESTAMP = new ValueType(TIMESTAMP_ID,"Timestamp");
	public static final ValueType BOOLEAN = new ValueType(BOOLEAN_ID,"Boolean");
	public static final ValueType BIG_DECIMAL = new ValueType(BIG_DECIMAL_ID, "BigDecimal");
	public static final ValueType BIG_INTEGER = new ValueType(BIG_INTEGER_ID,"BigInteger");
}
