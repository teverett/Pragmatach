package com.khubla.pragmatach.plugin.google;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.application.Application;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerController;

/**
 * @author tome
 *         <p>
 *         https://developers.google.com/accounts/docs/OAuth2WebServer
 *         </p>
 */
public class GoogleLoginController extends FreemarkerController {
   /**
    * code
    */
   private String code;
   /**
    * state
    */
   private String state;
   /**
    * google client ID
    */
   private String clientid;
   /**
    * google client secret
    */
   private String clientsecret;
   /**
    * redirect URL
    */
   private final String redirectURL;
   /**
    * access token
    */
   private String accessToken;
   /**
    * scopes to ask for
    */
   private static final String[] SCOPES = { "https://www.googleapis.com/auth/userinfo.profile", "https://www.googleapis.com/auth/userinfo.email" };
   /**
    * Google user id
    */
   private String id;
   /**
    * Google user name
    */
   private String name;
   /**
    * Google user given name
    */
   private String given_name;
   /**
    * Google user family name
    */
   private String family_name;
   /**
    * Google email
    */
   private String email;

   /**
    * ctor
    */
   public GoogleLoginController(String redirectURL) throws PragmatachException {
      this.redirectURL = redirectURL;
      clientid = Application.getConfiguration().getParameter("google.clientid");
      clientsecret = Application.getConfiguration().getParameter("google.clientsecret");
   }

   public Response doLogin() throws PragmatachException {
      final String sessionID = getRequest().getSession().getId();
      if (sessionID != getRequest().getSession().getId()) {
         throw new PragmatachException("CSRF Exception");
      }
      accessToken = getGoogleAccessToken(code);
      getUserInfo(accessToken);
      return super.render();
   }

   public String getAccessToken() {
      return accessToken;
   }

   public String getClientid() {
      return clientid;
   }

   public String getClientsecret() {
      return clientsecret;
   }

   public String getCode() {
      return code;
   }

   public String getEmail() {
      return email;
   }

   public String getFamily_name() {
      return family_name;
   }

   public String getGiven_name() {
      return given_name;
   }

   /**
    * request a token from the Google code
    */
   private String getGoogleAccessToken(String googleCode) throws PragmatachException {
      final String token = null;
      if ((googleCode != null) && !"".equals(googleCode)) {
         final String redirectUrl = getApplicationURL() + "/plugins/google/dologin";
         final String newUrl = "https://accounts.google.com/o/oauth2/token?client_id=" + clientid + "&redirect_uri=" + redirectUrl + "&client_secret=" + clientsecret + "&code=" + googleCode;
         final HttpClient httpclient = new DefaultHttpClient();
         try {
            final HttpPost httppost = new HttpPost(newUrl);
            final List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("code", code));
            nvps.add(new BasicNameValuePair("client_id", clientid));
            nvps.add(new BasicNameValuePair("client_secret", clientsecret));
            nvps.add(new BasicNameValuePair("redirect_uri", redirectUrl));
            nvps.add(new BasicNameValuePair("grant_type", "authorization_code"));
            httppost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            final HttpResponse httpResponse = httpclient.execute(httppost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
               final HttpEntity httpEntity = httpResponse.getEntity();
               final String jsonBody = EntityUtils.toString(httpEntity);
               final JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(jsonBody);
               final String access_token = jsonObject.getString("access_token");
               jsonObject.getString("expires_in");
               jsonObject.getString("token_type");
               return access_token;
            } else {
               System.out.println(httpResponse.getStatusLine().getReasonPhrase());
            }
         } catch (final Exception e) {
            throw new PragmatachException("Exception in getGoogleAccessToken", e);
         } finally {
            httpclient.getConnectionManager().shutdown();
         }
      }
      return token;
   }

   /**
    * get the auth URL to POST to
    */
   public String getGoogleAuthURL() {
      final String sessionId = getRequest().getSession().getId();
      final String redirectUrl = getApplicationURL() + "/plugins/google/dologin";
      final String returnValue = "https://accounts.google.com/o/oauth2/auth?response_type=code&client_id=" + clientid + "&redirect_uri=" + redirectUrl + "&state=" + sessionId + "&scope="
            + getScopes();
      return returnValue;
   }

   public String getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public String getRedirectURL() {
      return redirectURL;
   }

   private String getScopes() {
      String ret = "";
      for (final String s : SCOPES) {
         ret += s + " ";
      }
      return ret;
   }

   public String getState() {
      return state;
   }

   /**
    * get the user info
    */
   private void getUserInfo(String accessToken) throws PragmatachException {
      HttpClient httpclient = new DefaultHttpClient();
      try {
         final String newUrl = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + accessToken;
         httpclient = new DefaultHttpClient();
         final HttpGet httpget = new HttpGet(newUrl);
         final BasicResponseHandler responseHandler = new BasicResponseHandler();
         final String responseBody = httpclient.execute(httpget, responseHandler);
         final JSONObject json = (JSONObject) JSONSerializer.toJSON(responseBody);
         id = json.getString("id");
         name = json.getString("name");
         given_name = json.getString("given_name");
         family_name = json.getString("family_name");
         email = json.getString("email");
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getUserInfo", e);
      } finally {
         httpclient.getConnectionManager().shutdown();
      }
   }

   public void setAccessToken(String accessToken) {
      this.accessToken = accessToken;
   }

   public void setClientid(String clientid) {
      this.clientid = clientid;
   }

   public void setClientsecret(String clientsecret) {
      this.clientsecret = clientsecret;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public void setFamily_name(String family_name) {
      this.family_name = family_name;
   }

   public void setGiven_name(String given_name) {
      this.given_name = given_name;
   }

   public void setId(String id) {
      this.id = id;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setState(String state) {
      this.state = state;
   }
}
