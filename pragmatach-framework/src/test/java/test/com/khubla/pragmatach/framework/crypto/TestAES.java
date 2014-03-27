package test.com.khubla.pragmatach.framework.crypto;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.khubla.pragmatach.framework.crypto.AES;

/**
 * @author tome
 */
public class TestAES {
	@Test
	public void test1() {
		try {
			final String payload = "We were very tired, we were very merry. We had gone back and forth all night on the ferry.";
			final String key = "This is my key";
			/*
			 * encrypt
			 */
			final String encryptedPayload = AES.encrypt(payload, key);
			/*
			 * decrypt
			 */
			final String decryptedPayload = AES.decrypt(encryptedPayload, key);
			/*
			 * check
			 */
			Assert.assertTrue(payload.compareTo(decryptedPayload) == 0);
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
