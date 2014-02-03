package org.adligo.models.params.client;
/**
 * this class could have been a enum, but they don't work on j2me
 * @author scott
 *
 */
public class ValueTypes {
	public static final Short STRING = new Short((short) 0);
	public static final Short LONG = new Short((short) 1);
	public static final Short INTEGER = new Short((short) 2);
	public static final Short SHORT = new Short((short) 3);
	public static final Short DOUBLE = new Short((short) 4);
	public static final Short FLOAT = new Short((short) 5);
	public static final Short DATE = new Short((short) 6);
	public static final Short TIMESTAMP = new Short((short) 7);
	public static final Short BOOLEAN = new Short((short) 8);
	public static final Short BIG_DECIMAL = new Short((short) 9);
	public static final Short BIG_INTEGER = new Short((short) 10);
}
