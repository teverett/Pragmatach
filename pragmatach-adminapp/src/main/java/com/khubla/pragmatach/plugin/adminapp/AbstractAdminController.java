package com.khubla.pragmatach.plugin.adminapp;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerController;

/**
 * @author tome
 */
public class AbstractAdminController extends FreemarkerController {
   /**
    * the cookie name
    */
   public static final String USERID = "adminUserId";

   /**
    * check security
    */
   public Response render() throws PragmatachException {
      /*
       * get the user controller
       */
      final AdminUserController adminUserController = this.getSessionScopedController(AdminUserController.class);
      /*
       * user logged in?
       */
      if (null != adminUserController.getUsername()) {
         /*
          * user is logged in
          */
         return super.render();
      } else {
         /*
          * check for the cookie
          */
         final String userId = getRequest().getCookies().getCookie(USERID);
         if (null != userId) {
            /*
             * set the session state from the cookie
             */
            adminUserController.setUsername(userId);
            /*
             * redirect back and try again
             */
            return super.forward("/pragmatach/admin/login");
         } else {
            /*
             * log in
             */
            return super.forward("/pragmatach/admin/login");
         }
      }
   }
}
