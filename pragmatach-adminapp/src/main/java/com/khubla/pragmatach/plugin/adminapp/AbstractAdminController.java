package com.khubla.pragmatach.plugin.adminapp;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerController;

/**
 * @author tome
 */
public class AbstractAdminController extends FreemarkerController {
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
         return super.render();
      } else {
         return super.forward("/pragmatach/admin/login");
      }
   }
}
