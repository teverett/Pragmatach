Population of Controller fields via JSON Form POST
------------------------

The JSONController is capable of accepting JSON POSTs and populating atomic controllers fields automatically.  For example posting this:

`{"message":"hello world"}`

To this controller:

<pre><code>
@Controller(name="index")
@View(view = "acceptPost.html")
public class AcceptPostController extends JSONController {
   /**
    * message
    */
   private String message;

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   @Route(uri = "/acceptpost", method = HttpMethod.post)
   public Response acceptJSONpost() throws PragmatachException {
         return super.render();
      } catch (final Exception e) {
         throw new PragmatachException("Exception in showFeed", e);
      }
   }
}
</code></pre>

Will result in the method `setMessage()` being called with the value `"hello world"`.
