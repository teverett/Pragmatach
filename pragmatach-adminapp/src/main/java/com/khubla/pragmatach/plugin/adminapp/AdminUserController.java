package com.khubla.pragmatach.plugin.adminapp;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.controller.AbstractController;

/**
 * @author tome
 */
@Controller(name = "pragmatachAdminUserController", scope = Controller.Scope.session)
public class AdminUserController extends AbstractController {
   /**
    * the logged in user
    */
   private String username = null;

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }
}
