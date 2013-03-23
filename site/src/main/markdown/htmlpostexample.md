Population of Controller fields via HTML Form POST
------------------------

A typical HTML Form:

    <form action="/showfeed" method="post" enctype="multipart/form-data">
        <input name="feedURL" type="text">
        <button type="submit" class="btn">Submit</button>
    </form> 

When the form is posted to a controller route of type "HttpMethod.post" the Controller field "feedURL" will be automatically populated

<pre><code>
@Controller(name="index")
@View(view = "acceptPost.html")
public class AcceptPostController extends FreemarkerController {
   /**
    * the URL
    */
   private String feedURL;

   public String getFeedURL() {
      return feedURL;
   }

   public void setFeedURL(String feedURL) {
      this.feedURL = feedURL;
   }

   @Route(uri = "/acceptpost", method = HttpMethod.post)
   public Response acceptpost() throws PragmatachException {
      try {
         if (null != feedURL) {
     
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
</code></pre>
