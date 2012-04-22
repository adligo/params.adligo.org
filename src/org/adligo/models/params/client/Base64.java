package org.adligo.models.params.client;

import java.io.UnsupportedEncodingException;

import org.adligo.i.util.client.AppenderFactory;
import org.adligo.i.util.client.I_Appender;

/**
 * 
 * @author scott
 *
 */
public class Base64 {
	public static final String LENGTH_MUST_BE_MULTIPLE_OF_4_WITH_PADDING = "length must be multiple of 4 (with padding)";
	//(RFC 2045)
    static final char [] BASE_64_CHARS = getBase64Chars();


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
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
    }
    
    public static String decode(String data) {
    	I_Appender out = AppenderFactory.create();
        
        
        if (data.length() % 4 != 0)
            throw new IllegalArgumentException(LENGTH_MUST_BE_MULTIPLE_OF_4_WITH_PADDING);

        throw new IllegalStateException("unimplemented method");
        /*
        for (int i = 0; i < data.length();) {
            byte e0 = dtab[data.charAt(i++) & 0x7f];
            byte e1 = dtab[data.charAt(i++) & 0x7f];
            byte e2 = dtab[data.charAt(i++) & 0x7f];
            byte e3 = dtab[data.charAt(i++) & 0x7f];
            
            // Invalid characters in input
            if (e0 == -1 || e1 == -1 || e2 == -1 || e3 == -1)
                return "";
            
            byte d0 = (byte) ((e0 << 2) + ((e1 >>> 4) & 0x03));
            byte d1 = (byte) ((e1 << 4) + ((e2 >>> 2) & 0x0f));
            byte d2 = (byte) ((e2 << 6) + (e3 & 0x3f));
            
            out.append(Character.toString((char) d0));
            if (e2 != 64)
                out.append(Character.toString((char) d1));
            if (e3 != 64)
                out.append(Character.toString((char) d2));
        }
        
        return out.toString();
        */
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
        			eb.copy(lastByte, 4);
        			//wipe the left side for 6 bits
        			eb.setSlotZero(false);
        			eb.setSlotOne(false);
        			append(out, eb);
        			break;
        		case 3: 
        			//pad empty bytes for a 2 byte, 3 character sequence
        			byte left2 = (byte) (b >> 6);
        			EightBit eb3 = new EightBit(left2);
        			eb3.copy(lastByte, 6);
        			eb3.setSlotZero(false);
        			eb3.setSlotOne(false);		
        			append(out, eb3);
        			
        			byte right6 = (byte) (b << 2);
        			right6 = (byte) (right6 >> 2);
        			append(out, right6);
        			break;
        	}
    		
        	lastByte = b;
		}
        if (lastByte != null) {
        	switch (oneTwoThree) {
        		case 1:
        			byte lastTwoBits = (byte) (lastByte << 4 );
            		append(out, lastTwoBits);
        			//pad empty bytes for a 1 byte, 3 character sequence
        			out.append("==");
        			break;
        		case 2:
        			//pad empty bytes for a 2 byte, 3 character sequence
        			EightBit eb = new EightBit(lastByte);
        			eb.setSlotZero(false);
        			eb.setSlotOne(false);
        			eb.setSlotTwo(false);
        			eb.setSlotThree(false);
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
	
	static int unsign(byte p) {
		return p & 0xff;
	}
	
	static int unsign6bit(byte p) {
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