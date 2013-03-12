package com.khubla.pragmatach.plugin.facebook;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.application.Application;
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
   private String applicationid;
   /**
    * facebook application secret
    */
   private String facebooksecret;
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
    * redirect URL
    */
   private final String redirectURL;

   /**
    * ctor
    */
   public FacebookLoginController(String redirectURL) {
      this.redirectURL = redirectURL;
      applicationid = Application.getConfiguration().getParameter("facebook.applicationid");
      facebooksecret = Application.getConfiguration().getParameter("facebook.facebooksecret");
   }

   public String getRedirectURL() {
      return redirectURL;
   }

   public Response doLogin() throws PragmatachException {
      final String sessionID = getRequest().getSession().getId();
      if (sessionID != getRequest().getSession().getId()) {
         throw new PragmatachException("CSRF Exception");
      }
      final String accessToken = getFacebookAccessToken(code);
      getUserDetails(accessToken);
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
         } catch (final Exception e) {
            throw new PragmatachException("Exception in getFacebookAccessToken", e);
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
      final String returnValue = "https://www.facebook.com/dialog/oauth?client_id=" + applicationid + "&redirect_uri=" + redirectUrl + "&state=" + sessionId;
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

   private String getUserDetails(String accessToken) throws PragmatachException {
      HttpClient httpclient = new DefaultHttpClient();
      try {
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
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getUserDetails", e);
      } finally {
         httpclient.getConnectionManager().shutdown();
      }
      return email;
   }

   public void setApplicationid(String applicationid) {
      this.applicationid = applicationid;
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

   public void setFacebooksecret(String facebooksecret) {
      this.facebooksecret = facebooksecret;
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
