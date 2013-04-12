package com.khubla.goxmon;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerController;
import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.ExchangeFactory;
import com.xeiam.xchange.currency.Currencies;
import com.xeiam.xchange.service.marketdata.polling.PollingMarketDataService;

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
         ticker = marketDataService.getTicker(Currencies.BTC, Currencies.USD).toString();
         return super.render();
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }

   public void setTicker(String ticker) {
      this.ticker = ticker;
   }
}