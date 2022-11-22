package com.khubla.pragmatach.framework.crypto;

import java.math.*;
import java.security.*;

import com.khubla.pragmatach.framework.api.*;

/**
 * @author tome
 */
public class HashUtil {
	public static String MD5(String str) throws PragmatachException {
		try {
			final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
			final byte[] digest = messageDigest.digest();
			final BigInteger bigInt = new BigInteger(1, digest);
			String hashtext = bigInt.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (final Exception e) {
			throw new PragmatachException("Exception in MD5", e);
		}
	}
}
