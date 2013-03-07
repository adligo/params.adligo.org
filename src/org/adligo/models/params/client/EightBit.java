package org.adligo.models.params.client;

import org.adligo.i.util.client.AppenderFactory;
import org.adligo.i.util.client.I_Appender;

public class EightBit {
	private boolean [] bits = null;
	private byte bigB;
	
	public EightBit() {
		clean();
	}
	
	/**
	 * reads in a byte
	 */
	public EightBit(int b) {
		this((byte) b);
	}
	
	public EightBit(String onesAndZeros) {
		char [] cs = onesAndZeros.toCharArray();
		for (int i = 0; i < 8 - cs.length; i++) {
			bits[i] = false;
		}
		for (int i = 8 - cs.length; i < cs.length; i++) {
			char c = cs[i];
			if (c == '0') {
				bits[i] = false;
			} else {
				bits[i] = true;
			}
		}
	}
	
	public EightBit(Byte b) {
		this(b.byteValue());
	}
	public EightBit(byte b) {
		clean();
		bigB = b;
		toBooleans(bigB);
	}
	
	/**
	 * copy the right most bits into the left most slots
	 * @param b
	 * @param slots
	 */
	public void copy(int b, int slots) {
		copy((byte) b, (byte) slots);
	}
	
	/**
	 * copy the bits from the right of the input byte b
	 * to the left of the slots for the number of slots
	 * 
	 * @param b
	 * @param slots
	 */
	public void copy(byte b, byte slots) {
		if (slots > 8) {
			slots = 8;
		}
		EightBit other = new EightBit(b);
		int nextSlot = Math.abs(1 - slots);
		int rightSlot = 7;
		
		for (int i = 0; i < slots; i++) {
			boolean otherValue = other.bits[rightSlot];
			bits[nextSlot] = otherValue;
			nextSlot--;
			rightSlot--;
		}
	}
	
	private void clean() {
		bits = new boolean[] {false, false, false, false, false, false, false, false};
	}
	
	static int unsign(byte p) {
		return p & 0xff;
	}
	
	public int unsigned() {
		int result = 0;
		if (bits[0]) {
			result = 128;
		}
		if (bits[1]) {
			result = result + 64;
		}
		if (bits[2]) {
			result = result + 32;
		}
		if (bits[3]) {
			result = result + 16;
		}
		if (bits[4]) {
			result = result + 8;
		}
		if (bits[5]) {
			result = result + 4;
		}
		if (bits[6]) {
			result = result + 2;
		}
		if (bits[7]) {
			result = result + 1;
		}
		return result;
	}

	private void toBooleans(byte bigB) {
		int result = unsign(bigB);
		if (result >= 128) {
			result = result - 128;
			bits[0] = true;
		}
		if (result >= 64) {
			result = result - 64;
			bits[1] = true;
		}
		if (result >= 32) {
			result = result - 32;
			bits[2] = true;
		}
		if (result >= 16) {
			result = result - 16;
			bits[3] = true;
		}
		if (result >= 8) {
			result = result - 8;
			bits[4] = true;
		}
		if (result >= 4) {
			result = result - 4;
			bits[5] = true;
		}
		if (result >= 2) {
			result = result - 2;
			bits[6] = true;
		}
		if (result >= 1) {
			result = result - 1;
			bits[7] = true;
		}
	}
	
	public void setSlotZero(boolean p) {
		bits[0] = p;
	}
	public void setSlotOne(boolean p) {
		bits[1] = p;
	}
	public void setSlotTwo(boolean p) {
		bits[2] = p;
	}
	public void setSlotThree(boolean p) {
		bits[3] = p;
	}
	public void setSlotFour(boolean p) {
		bits[4] = p;
	}
	public void setSlotFive(boolean p) {
		bits[5] = p;
	}
	public void setSlotSix(boolean p) {
		bits[6] = p;
	}
	public void setSlotSeven(boolean p) {
		bits[7] = p;
	}
	
	public boolean getSlotZero() {
		return bits[0];
	}
	public boolean getSlotOne() {
		return bits[1];
	}
	public boolean getSlotTwo() {
		return bits[2];
	}
	public boolean getSlotThree() {
		return bits[3];
	}
	public boolean getSlotFour() {
		return bits[4];
	}
	public boolean getSlotFive() {
		return bits[5];
	}
	public boolean getSlotSix() {
		return bits[6];
	}
	public boolean getSlotSeven() {
		return bits[7];
	}
	
	public String toString() {
		I_Appender out = AppenderFactory.create();
		for (int i = 0; i < bits.length; i++) {
			if (bits[i]) {
				out.append("1");
			} else {
				out.append("0");
			}
		}
		
		return out.toString();
	}
}
