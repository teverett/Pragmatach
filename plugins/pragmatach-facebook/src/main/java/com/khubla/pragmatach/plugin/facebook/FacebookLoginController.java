package com.khubla.pragmatach.plugin.facebook;

import java.io.IOException;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.Route.HttpMethod;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.servlet.PragmatachServlet;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerController;

/**
 * @author tome
 */
public class FacebookLoginController extends FreemarkerController {
   /**
    * code
    */
   private String code;
   /**
    * state
    */
   private String state;
   /**
    * facebook application ID
    */
   private final String applicationid;
   /**
    * facebook application secret
    */
   private final String facebooksecret;
   /**
    * user's facebook id
    */
   private String facebookId;
   /**
    * user's firstname
    */
   private String firstName;
   /**
    * user's lastname
    */
   private String lastName;
   /**
    * users email
    */
   private String email;

   /**
    * ctor
    */
   public FacebookLoginController() {
      applicationid = PragmatachServlet.getConfiguration().getParameter("facebook.applicationid");
      facebooksecret = PragmatachServlet.getConfiguration().getParameter("facebook.facebooksecret");
   }

   @Route(uri = "/plugins/facebook/dologin", method = HttpMethod.post)
   public Response doLogin() throws PragmatachException {
      final String accessToken = getFacebookAccessToken(code);
      getUserDetails(accessToken);
      final String sessionID = getRequest().getSession().getId();
      if (sessionID != getRequest().getSession().getId()) {
         throw new PragmatachException("CSRF Exception");
      }
      return super.render();
   }

   public String getApplicationid() {
      return applicationid;
   }

   public String getCode() {
      return code;
   }

   public String getEmail() {
      return email;
   }

   private String getFacebookAccessToken(String faceCode) throws PragmatachException {
      String token = null;
      if ((faceCode != null) && !"".equals(faceCode)) {
         final String redirectUrl = getApplicationURL() + "/plugins/facebook/dologin";
         final String newUrl = "https://graph.facebook.com/oauth/access_token?client_id=" + applicationid + "&redirect_uri=" + redirectUrl + "&client_secret=" + facebooksecret + "&code=" + faceCode;
         final HttpClient httpclient = new DefaultHttpClient();
         try {
            final HttpGet httpget = new HttpGet(newUrl);
            final BasicResponseHandler responseHandler = new BasicResponseHandler();
            final String responseBody = httpclient.execute(httpget, responseHandler);
            token = StringUtils.removeEnd(StringUtils.removeStart(responseBody, "access_token="), "&expires=5180795");
         } catch (final ClientProtocolException e) {
            e.printStackTrace();
         } catch (final IOException e) {
            e.printStackTrace();
         } finally {
            httpclient.getConnectionManager().shutdown();
         }
      }
      return token;
   }

   public String getFacebookId() {
      return facebookId;
   }

   public String getFacebooksecret() {
      return facebooksecret;
   }

   public String getFacebookUrlAuth() {
      final String sessionId = getRequest().getSession().getId();
      final String redirectUrl = getApplicationURL() + "/plugins/facebook/dologin";
      final String returnValue = "https://www.facebook.com/dialog/oauth?client_id=" + applicationid + "&redirect_uri=" + redirectUrl + "&scope=email,user_birthday&state=" + sessionId;
      return returnValue;
   }

   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public String getState() {
      return state;
   }

   private String getUserDetails(String accessToken) {
      HttpClient httpclient = new DefaultHttpClient();
      try {
         if ((accessToken != null) && !"".equals(accessToken)) {
            final String newUrl = "https://graph.facebook.com/me?access_token=" + accessToken;
            httpclient = new DefaultHttpClient();
            final HttpGet httpget = new HttpGet(newUrl);
            final BasicResponseHandler responseHandler = new BasicResponseHandler();
            final String responseBody = httpclient.execute(httpget, responseHandler);
            final JSONObject json = (JSONObject) JSONSerializer.toJSON(responseBody);
            facebookId = json.getString("id");
            firstName = json.getString("first_name");
            lastName = json.getString("last_name");
            email = json.getString("email");
         } else {
            System.err.println("Token for facebook is null");
         }
      } catch (final ClientProtocolException e) {
         e.printStackTrace();
      } catch (final IOException e) {
         e.printStackTrace();
      } finally {
         httpclient.getConnectionManager().shutdown();
      }
      return email;
   }

   public Response render() throws PragmatachException {
      return super.render();
   }

   public void setCode(String code) {
      this.code = code;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public void setFacebookId(String facebookId) {
      this.facebookId = facebookId;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public void setState(String state) {
      this.state = state;
   }
}
