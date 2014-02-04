package org.adligo.models.params.client;
/**
 * this class could have been a enum, but they don't work on j2me
 * @author scott
 *
 */
public class ValueTypes {
	public static final ValueType STRING = new ValueType((short) 0, "String");
	public static final ValueType LONG = new ValueType((short) 1, "Long");
	public static final ValueType INTEGER = new ValueType((short) 2, "Integer");
	public static final ValueType SHORT = new ValueType((short) 3, "Short");
	public static final ValueType DOUBLE = new ValueType((short) 4, "Double");
	public static final ValueType FLOAT = new ValueType((short) 5, "Float");
	public static final ValueType DATE = new ValueType((short) 6, "Date");
	public static final ValueType TIMESTAMP = new ValueType((short) 7, "Timestamp");
	public static final ValueType BOOLEAN = new ValueType((short) 8, "Boolean");
	public static final ValueType BIG_DECIMAL = new ValueType((short) 9, "BigDecimal");
	public static final ValueType BIG_INTEGER = new ValueType((short) 10, "BigInteger");
}
