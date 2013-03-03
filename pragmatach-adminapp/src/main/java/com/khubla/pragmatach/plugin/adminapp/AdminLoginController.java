package com.khubla.pragmatach.plugin.adminapp;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.Route.HttpMethod;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerController;

/**
 * @author tome
 */
@Controller(name = "pragmatachAdminLoginController")
@View(view = "pragmatach/admin/login.html")
public class AdminLoginController extends FreemarkerController {
   /**
    * username
    */
   private String username;
   /**
    * password
    */
   private String password;

   @Route(uri = "/pragmatach/admin/login/doLogin", method = HttpMethod.post)
   public Response doLogin() throws PragmatachException {
      /*
       * valid u/p?
       */
      if ((null != username) && (username.length() > 0) && (null != password) && (password.length() > 0)) {
         /*
          * check that it matches
          */
         if ((username.compareTo(getConfigurationParameter("pragmatach.adminapp.username")) == 0) && (password.compareTo(getConfigurationParameter("pragmatach.adminapp.password")) == 0)) {
            /*
             * set session state
             */
            this.getSessionScopedController(AdminUserController.class).setUsername(username);
            /*
             * set cookie
             */
            getRequest().getCookies().setEncryptedCookie(AbstractAdminController.USERID, username);
            getRequest().getCookies().setEncryptedCookie(AbstractAdminController.PASSWORD, password);
            /*
             * go to main page
             */
            return super.forward("/pragmatach/admin/");
         }
      }
      return super.forward("/pragmatach/admin/login");
   }

   public String getPassword() {
      return password;
   }

   public String getUsername() {
      return username;
   }

   @Route(uri = "/pragmatach/admin/login")
   public Response render() throws PragmatachException {
      return super.render();
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public void setUsername(String username) {
      this.username = username;
   }
}
