package com.khubla.pragmatach.plugin.adminapp;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;

/**
 * @author tome
 */
public class SecuredAdminController extends BaseAdminController {
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
         final String userId = getRequest().getCookies().getEncryptedCookie(USERID);
         final String password = getRequest().getCookies().getEncryptedCookie(PASSWORD);
         if ((null != userId) && (null != password)) {
            /*
             * check
             */
            if ((userId.compareTo(getConfigurationParameter("pragmatach.adminapp.username")) == 0) && (password.compareTo(getConfigurationParameter("pragmatach.adminapp.password")) == 0)) {
               /*
                * set the session state from the cookie
                */
               adminUserController.setUsername(userId);
               /*
                * redirect back and try again
                */
               return super.render();
            } else {
               /*
                * log in
                */
               return super.forward("/pragmatach/admin/login");
            }
         } else {
            /*
             * log in
             */
            return super.forward("/pragmatach/admin/login");
         }
      }
   }
}
