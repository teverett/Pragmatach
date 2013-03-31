package com.khubla.pragmatach.plugin.adminapp;

import java.lang.management.ManagementFactory;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;

/**
 * @author tome
 */
@Controller(name = "pragmatachShowServerController")
@View(view = "pragmatach/admin/server.html")
public class ShowServerController extends SecuredAdminController {
   /**
    * find the current process id
    */
   private static String findProcessId() throws PragmatachException {
      try {
         final String jvmName = ManagementFactory.getRuntimeMXBean().getName();
         final int index = jvmName.indexOf('@');
         if (index < 1) {
            return null;
         } else {
            return Long.toString(Long.parseLong(jvmName.substring(0, index)));
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getProcessId", e);
      }
   }

   /**
    * server info
    */
   private String serverinfo;
   /**
    * hostname
    */
   private String hostname;
   /**
    * process id
    */
   private String processId;

   public String getHostname() {
      return hostname;
   }

   public String getProcessId() {
      return processId;
   }

   public String getServerinfo() {
      return serverinfo;
   }

   @Route(uri = "/pragmatach/admin/server")
   public Response render() throws PragmatachException {
      serverinfo = getRequest().getHttpServletRequest().getSession().getServletContext().getServerInfo();
      processId = findProcessId();
      try {
         hostname = java.net.InetAddress.getLocalHost().getHostName();
      } catch (final Exception e) {
         hostname = "";
      }
      return super.render();
   }

   public void setHostname(String hostname) {
      this.hostname = hostname;
   }

   public void setProcessId(String processId) {
      this.processId = processId;
   }

   public void setServerinfo(String serverinfo) {
      this.serverinfo = serverinfo;
   }
}
