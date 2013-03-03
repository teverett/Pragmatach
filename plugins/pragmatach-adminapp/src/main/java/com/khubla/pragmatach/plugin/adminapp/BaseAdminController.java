package com.khubla.pragmatach.plugin.adminapp;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerController;

/**
 * @author tome
 */
@Controller(name = "pragmatachBaseAdminController")
public class BaseAdminController extends FreemarkerController {
   /**
    * the cookie names
    */
   public static final String USERID = "adminUserId";
   public static final String PASSWORD = "adminPassword";

   /**
    * check if user is logged in
    */
   public boolean isLoggedIn() {
      /*
       * get the user controller
       */
      final AdminUserController adminUserController = this.getSessionScopedController(AdminUserController.class);
      /*
       * check
       */
      if (null != adminUserController.getUsername()) {
         return true;
      }
      return false;
   }

   /**
    * log out
    */
   @Route(uri = "pragmatach/admin/logout")
   public Response logout() throws PragmatachException {
      try {
         /*
          * get the user controller
          */
         final AdminUserController adminUserController = this.getSessionScopedController(AdminUserController.class);
         /*
          * remove the login session state
          */
         adminUserController.setUsername(null);
         /*
          * remove the cookies
          */
         getRequest().getCookies().removeCookie(BaseAdminController.USERID);
         getRequest().getCookies().removeCookie(BaseAdminController.PASSWORD);
         /*
          * to login screen
          */
         return super.forward("/pragmatach/admin/");
      } catch (final Exception e) {
         throw new PragmatachException("Exception in logout", e);
      }
   }
}
