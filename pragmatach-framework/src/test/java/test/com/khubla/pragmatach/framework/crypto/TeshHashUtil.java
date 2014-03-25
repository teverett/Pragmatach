package test.com.khubla.pragmatach.framework.crypto;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.khubla.pragmatach.framework.crypto.HashUtil;

/**
 * @author tome
 */
public class TeshHashUtil {
	@Test
	public void test1() {
		try {
			final String payload = "We were very tired, we were very merry. We had gone back and forth all night on the ferry.";

			/*
			 * hash
			 */
			final String hashedPayload = HashUtil.MD5(payload);
			/*
			 * check
			 */
			Assert.assertTrue(hashedPayload != null);
			Assert.assertTrue(hashedPayload.length() == 32);
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
