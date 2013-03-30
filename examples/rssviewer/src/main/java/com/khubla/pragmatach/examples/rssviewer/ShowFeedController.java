package com.khubla.pragmatach.examples.rssviewer;

import java.net.HttpURLConnection;
import java.net.URL;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.Route.HttpMethod;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerController;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@Controller()
@View(view = "showfeed.html")
public class ShowFeedController extends FreemarkerController {
   /**
    * the URL
    */
   private String feedURL;
   /**
    * the feed
    */
   private SyndFeed syndFeed;

   public String getFeedURL() {
      return feedURL;
   }

   public SyndFeed getSyndFeed() {
      return syndFeed;
   }

   public void setFeedURL(String feedURL) {
      this.feedURL = feedURL;
   }

   public void setSyndFeed(SyndFeed syndFeed) {
      this.syndFeed = syndFeed;
   }

   @Route(uri = "/showfeed", method = HttpMethod.post)
   public Response showFeed() throws PragmatachException {
      try {
         if (null != feedURL) {
            /*
             * get feed
             */
            final URL url = new URL(feedURL);
            final HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            /*
             * parse it
             */
            final SyndFeedInput input = new SyndFeedInput();
            syndFeed = input.build(new XmlReader(httpURLConnection));
         }
         /*
          * render
          */
         return super.render();
      } catch (final Exception e) {
         throw new PragmatachException("Exception in showFeed", e);
      }
   }
}