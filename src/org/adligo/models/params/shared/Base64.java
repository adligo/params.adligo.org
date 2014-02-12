package org.adligo.models.params.shared;


import java.io.UnsupportedEncodingException;

import org.adligo.i.util.shared.AppenderFactory;
import org.adligo.i.util.shared.I_Appender;
import org.adligo.i.util.shared.I_Map;
import org.adligo.i.util.shared.MapFactory;

/**
 * 
 * @author scott
 *
 */
public class Base64 {
	public static final String BASE64_DOES_NOT_ALLOW_USAGE_OF_THE_CHARACTER = "Base64 does not allow usage of the character ";
	public static final String LENGTH_MUST_BE_MULTIPLE_OF_4_WITH_PADDING = "length must be multiple of 4 (with padding)";
	//(RFC 2045)
    static final char [] BASE_64_CHARS = getBase64Chars();
    static final I_Map CHAR_TO_INDEX = getBase64Index();

    private static char [] getBase64Chars() {
    	try {
    		byte[] bytes = 
    			"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="
    			.getBytes("ASCII");
		
	    	char [] toRet = new char[bytes.length];
	    	for (int i = 0; i < toRet.length; i++) {
				toRet[i] = (char) bytes[i];
			}
	    	return toRet;
    	} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage());
		}
    }
    
    private static I_Map getBase64Index() {
    	I_Map toRet = MapFactory.create();
    	for (int i = 0; i < BASE_64_CHARS.length; i++) {
			toRet.put(new Character(BASE_64_CHARS[i]), new Byte((byte) i));
		}
    	return toRet;
    }
    /**
     * 
     * @param s the Base64 character string only no spaces, or line feeds
     *   
     * @return
     */
    public static byte[] decode(String s) {


    	if (s == null) {
    		return new byte[] {};
    	}
    	if (s.length() < 4) {
    		return new byte []{};
    	}
		// replace any incoming padding with a zero pad (the 'A' character is
		// zero)
    	String endPart = s.substring(s.length() - 3, s.length());
    	int equalsSigns = 0;
    	char [] endPartChars = endPart.toCharArray();
    	for (int i = 0; i < endPartChars.length; i++) {
			char c = endPartChars[i];
			if (c == '=') {
				equalsSigns++;
			}
		}

		s = Parser.replaceAll(s, "=", "A");
		
		int resLength = (int) Math.ceil(((float) (s.length()) / 4f) * 3f);
		byte[] bufIn = new byte[resLength - equalsSigns];
		int bufIn_i = 0;

		// increment over the length of this encrypted string, four characters
		// at a time
		for (int c = 0; c < s.length(); c += 4) {

			// each of these four characters represents a 6-bit index in the
			// base64 characters list which, when concatenated, will give the
			// 24-bit number for the original 3 characters
			char aChar = s.charAt(c);
			byte aByte = getIndexByteForChar(aChar);
			char bChar = s.charAt(c + 1);
			byte bByte = getIndexByteForChar(bChar); 
			char cChar = s.charAt(c + 2);
			int cByte = getIndexByteForChar(cChar);
			char dChar = s.charAt(c + 3);
			byte dByte = getIndexByteForChar(dChar);
			
			int n = (aByte << 18)
					+ (bByte << 12)
					+ (cByte << 6)
					+ dByte;

			// split the 24-bit number into the original three 8-bit (ASCII)
			// characters

			char c1 = (char) ((n >>> 16) & 0xFF);
			char c2 = (char) ((n >>>8) & 0xFF);
			char c3 = (char) (n & 0xFF);

			bufIn[bufIn_i++] = (byte) c1;
			if (bufIn.length > bufIn_i) {
				bufIn[bufIn_i++] = (byte) c2;
			}
			if (bufIn.length > bufIn_i) {
				bufIn[bufIn_i++] = (byte) c3;
			}
		}

		return bufIn;
	}

	private static byte getIndexByteForChar(char aChar) {
		Byte a = (Byte) CHAR_TO_INDEX.get(new Character(aChar));
		if (a == null) {
			throw new IllegalArgumentException(BASE64_DOES_NOT_ALLOW_USAGE_OF_THE_CHARACTER + aChar);
		}
		return a.byteValue();
	}
    
    /**
     * encode any string (UTF-8, ASCII, UTF-16 exc)
     * @param data
     * @return
     */
    public static String encode(String data) {
        return encode(data.getBytes());
    }
    
    /**
     * encode the byte array to a Base64 String
     * @param data
     * @return
     */
    public static String encode(byte [] data) {
        I_Appender out = AppenderFactory.create();   
        
        Byte lastByte = null;
        //keep track of which byte we are in
        // one two or three
        byte oneTwoThree = 1;
        
        for (int i = 0; i < data.length; i++) {
        	if (i != 0) {
        		oneTwoThree++;
        		if (oneTwoThree > 3) {
        			oneTwoThree = 1;
        		}
        	}
        	byte b = data[i];
        	//System.out.println("processing " + (char) b);
        	switch (oneTwoThree) {
        		case 1: 
        			//move the bits from slots 0-5 to slots 2-7
        			byte firstSixBits = (byte) (b >> 2);
            		append(out, firstSixBits);
        			break;
        		case 2: 
        			//use the left 4 bits of the new byte on the right 
        			byte leftB = (byte) (b >> 4);
        			EightBit eb = new EightBit(leftB);
        			//put the left 4 bytes from the old byte
        			eb.copy(lastByte.byteValue(), (byte) 4);
        			//wipe the left side for 6 bits
        			eb.setSlotZero(false);
        			eb.setSlotOne(false);
        			append(out, eb);
        			break;
        		case 3: 
        			//pad empty bytes for a 2 byte, 3 character sequence
        			byte left2 = (byte) (b >> 6);
        			EightBit eb3 = new EightBit(left2);
        			eb3.copy(lastByte.byteValue(), (byte) 6);
        			eb3.setSlotZero(false);
        			eb3.setSlotOne(false);		
        			append(out, eb3);
        			
        			byte right6 = (byte) (b << 2);
        			right6 = (byte) (right6 >> 2);
        			append(out, right6);
        			break;
        	}
    		
        	lastByte = new Byte(b);
		}
        if (lastByte != null) {
        	switch (oneTwoThree) {
        		case 1:
        			//use the last 2 bits of the first byte in slots 2 and 3  with zeros on the right
        			byte lastTwoBits = (byte) (lastByte.byteValue() << 4 );
            		append(out, lastTwoBits);
        			//pad empty bytes for a 1 byte, 3 character sequence
        			out.append("==");
        			break;
        		case 2:
        			//use the last 4 bits of the second byte, padding with two zeros on the right
        			byte padLastByte = (byte) (lastByte.byteValue() << 2);
        			EightBit eb = new EightBit(padLastByte);
        			eb.setSlotZero(false);
        			eb.setSlotOne(false);
        			append(out, eb);
        			out.append("=");
        			break;
        		default:
        			//do nothing even 3 to 4 ratio of bytes to characters
        	}
        }
        
        /*
        int i = 0;
        int r = data.length();
        while (r > 0) {
            byte d0, d1, d2;
            byte e0, e1, e2, e3;
            
            d0 = (byte) data.charAt(i++); --r;
            e0 = (byte) (d0 >>> 2);
            e1 = (byte) ((d0 & 0x03) << 4);
            
            if (r > 0) {
                d1 = (byte) data.charAt(i++); --r;
                e1 += (byte) (d1 >>> 4);
                e2 = (byte) ((d1 & 0x0f) << 2);
            }
            else {
                e2 = 64;
            }
            
            if (r > 0) {
                d2 = (byte) data.charAt(i++); --r;
                e2 += (byte) (d2 >>> 6);
                e3 = (byte) (d2 & 0x3f);
            }
            else {
                e3 = 64;
            }
            out.append(BASE_64_CHARS.charAt(e0));
            out.append(BASE_64_CHARS.charAt(e1));
            out.append(BASE_64_CHARS.charAt(e2));
            out.append(BASE_64_CHARS.charAt(e3));
        }
        */
        return out.toString();
    }

	private static void append(I_Appender out, byte whichChar) {
		int whichOne = unsign6bit(whichChar);
		char c = BASE_64_CHARS[whichOne];
		out.append(c);
	}
	
	
	private static void append(I_Appender out, EightBit eb) {
		int whichOne = eb.unsigned();;
		char c = BASE_64_CHARS[whichOne];
		out.append(c);
	}
	
	public static int unsign(byte p) {
		return p & 0xff;
	}
	
	public static int unsign6bit(byte p) {
		int result = unsign(p);
		if (result >= 128) {
			result = result - 128;
		}
		if (result >= 64) {
			result = result - 64;
		}
		return result;
	}
}