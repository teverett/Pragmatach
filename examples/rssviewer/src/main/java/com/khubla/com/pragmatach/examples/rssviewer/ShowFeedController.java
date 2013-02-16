package com.khubla.com.pragmatach.examples.rssviewer;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.Route.HttpMethod;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.api.form.Form;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerController;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerTemplate;

@Controller
@FreemarkerTemplate(template = "showfeed.html")
public class ShowFeedController extends FreemarkerController {
   @Route(uri = "/showfeed", method = HttpMethod.post)
   public Response showFeed() throws PragmatachException {
      /*
       * get the posted form
       */
      Form form = this.getRequest().getFormData();
      if (null != form) {
         /*
          * get the url
          */
         feedURL = form.getFormItemValue("url");
         System.out.println(feedURL);
      }
      /*
       * render
       */
      return super.render();
   }

   public String getFeedURL() {
      return feedURL;
   }

   public void setFeedURL(String feedURL) {
      this.feedURL = feedURL;
   }

   /**
    * the URL
    */
   private String feedURL;
}