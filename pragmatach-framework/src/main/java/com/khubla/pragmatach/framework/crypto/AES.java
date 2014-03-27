package com.khubla.pragmatach.framework.crypto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

/**
 * @author tome
 */
public class AES {
	/**
	 * keyspec
	 */
	private final static String KEYSPEC = "AES/ECB/ISO10126Padding";

	/**
	 * decrypt
	 */
	public static String decrypt(String encryptedPayload, String key)
			throws Exception {
		try {
			final SecretKeySpec secretKeySpec = new SecretKeySpec(
					makeAESKey(key), "AES");
			final Cipher cipher = Cipher.getInstance(KEYSPEC);
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			final CipherInputStream CipherInputStream = new CipherInputStream(
					new ByteArrayInputStream(
							Base64.decodeBase64(encryptedPayload
									.getBytes("UTF-8"))), cipher);
			IOUtils.copy(CipherInputStream, baos);
			return baos.toString("UTF-8");
		} catch (final Exception e) {
			throw new Exception("Exception in decrypt", e);
		}
	}

	/**
	 * encrypt
	 */
	public static String encrypt(String payload, String key) throws Exception {
		try {
			final SecretKeySpec secretKeySpec = new SecretKeySpec(
					makeAESKey(key), "AES");
			final Cipher cipher = Cipher.getInstance(KEYSPEC);
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			final CipherInputStream CipherInputStream = new CipherInputStream(
					new ByteArrayInputStream(payload.getBytes("UTF-8")), cipher);
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			IOUtils.copy(CipherInputStream, baos);
			return Base64.encodeBase64String(baos.toByteArray());
		} catch (final Exception e) {
			throw new Exception("Exception in encrypt", e);
		}
	}

	/**
	 * make a nice AES key
	 */
	private static byte[] makeAESKey(String key) throws Exception {
		try {
			final MessageDigest sha = MessageDigest.getInstance("SHA-1");
			final byte[] rawkey = sha.digest(key.getBytes("UTF-8"));
			return Arrays.copyOf(rawkey, 16); // use only first 128 bit
		} catch (final Exception e) {
			throw new Exception("Exception in makeAESKey", e);
		}
	}
}
