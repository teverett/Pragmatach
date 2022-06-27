package com.khubla.goxmon;

import com.khubla.pragmatach.framework.annotation.*;
import com.khubla.pragmatach.framework.api.*;
import com.khubla.pragmatach.plugin.freemarker.*;
import com.xeiam.xchange.*;
import com.xeiam.xchange.currency.*;
import com.xeiam.xchange.service.polling.marketdata.*;

/**
 * @author tome
 */
@Controller(name = "IndexController")
@View(view = "index.ftl")
public class IndexController extends FreemarkerController {
	/**
	 * the ticket
	 */
	private String ticker;

	public String getTicker() {
		return ticker;
	}

	@Route(uri = "/")
	public Response render() throws PragmatachException {
		try {
			final Exchange mtGox = ExchangeFactory.INSTANCE.createExchange("com.xeiam.xchange.mtgox.v1.MtGoxExchange");
			final PollingMarketDataService marketDataService = mtGox.getPollingMarketDataService();
			ticker = marketDataService.getTicker(CurrencyPair.BTC_USD).toString();
			return super.render();
		} catch (final Exception e) {
			throw new PragmatachException("Exception in render", e);
		}
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
}