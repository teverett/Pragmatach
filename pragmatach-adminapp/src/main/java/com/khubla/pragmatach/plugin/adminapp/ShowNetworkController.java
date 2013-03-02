package com.khubla.pragmatach.plugin.adminapp;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;

/**
 * @author tome
 */
@Controller(name = "pragmatachShowNetworkController")
@View(view = "pragmatach/admin/network.html")
public class ShowNetworkController extends AbstractAdminController {
   /**
    * ips of this host
    */
   private List<String> ips;

   private List<String> findIPs() throws PragmatachException {
      try {
         final List<String> ret = new ArrayList<String>();
         final Enumeration<NetworkInterface> enumer = NetworkInterface.getNetworkInterfaces();
         while (enumer.hasMoreElements()) {
            final NetworkInterface networkInterface = enumer.nextElement();
            for (final InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
               if (false == interfaceAddress.getAddress().isLoopbackAddress()) {
                  final String ip = interfaceAddress.getAddress().getHostAddress();
                  if (false == ip.contains(":")) {
                     ret.add(ip);
                  }
               }
            }
         }
         return ret;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in findIPs", e);
      }
   }

   public List<String> getIps() {
      return ips;
   }

   @Route(uri = "/pragmatach/admin/network")
   public Response render() throws PragmatachException {
      ips = findIPs();
      return super.render();
   }

   public void setIps(List<String> ips) {
      this.ips = ips;
   }
}
