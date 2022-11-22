package com.khubla.pragmatach.framework.listener;

import javax.servlet.http.*;

import org.slf4j.*;

import com.khubla.pragmatach.framework.controller.*;

/**
 * @author tome
 */
public class SessionListener implements HttpSessionListener {
	/**
	 * logger
	 */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		try {
			SessionScopedControllers.populateSessionControllers(httpSessionEvent.getSession());
		} catch (final Exception e) {
			logger.error("Exception in contextInitialized", e);
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
	}
}
