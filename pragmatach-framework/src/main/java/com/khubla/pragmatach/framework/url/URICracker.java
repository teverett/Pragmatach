package com.khubla.pragmatach.framework.url;

import com.khubla.pragmatach.framework.api.*;

/**
 * @author tome
 */
public class URICracker {
	/**
	 * parse the URI parameters. If no parameters, returns "/";
	 */
	public static String[] crackURI(String uri) throws PragmatachException {
		try {
			String p = uri;
			if (p.startsWith("/")) {
				p = p.substring(1);
			}
			if ((p.length() > 0) && (p.contains("/"))) {
				return p.split("/");
			} else {
				if (p.length() > 0) {
					return new String[] { p };
				} else {
					return new String[] { "/" };
				}
			}
		} catch (final Exception e) {
			throw new PragmatachException("Exception in parseParameters", e);
		}
	}
}
