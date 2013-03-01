package com.khubla.pragmatach.plugin.adminapp;

import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

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
public class ShowServerController extends AbstractAdminController {
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
   /**
    * ips of this host
    */
   private List<String> ips;
   /**
    * java properties
    */
   private Hashtable<String, String> javaProperties;
   /**
    * application version from maven pom
    */
   private String applicationVersion;

   private String findApplicationVersion() throws PragmatachException {
      try {
         final InputStream is = ShowServerController.class.getResourceAsStream("/version.properties");
         if (null != is) {
            final Properties properties = new Properties();
            properties.load(is);
            return properties.getProperty("maven.version");
         }
         return null;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in findApplicationVersion", e);
      }
   }

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

   private Hashtable<String, String> findJavaProperties() throws PragmatachException {
      try {
         final Hashtable<String, String> ret = new Hashtable<String, String>();
         final Properties props = System.getProperties();
         final Enumeration<Object> enumer = props.keys();
         while (enumer.hasMoreElements()) {
            final String key = (String) enumer.nextElement();
            ret.put(key, props.getProperty(key));
         }
         return ret;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in findJavaProperties", e);
      }
   }

   public String getApplicationVersion() {
      return applicationVersion;
   }

   public String getHostname() {
      return hostname;
   }

   public List<String> getIps() {
      return ips;
   }

   public Hashtable<String, String> getJavaProperties() {
      return javaProperties;
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
      ips = findIPs();
      javaProperties = findJavaProperties();
      applicationVersion = findApplicationVersion();
      try {
         hostname = java.net.InetAddress.getLocalHost().getHostName();
      } catch (final Exception e) {
         hostname = "";
      }
      return super.render();
   }

   public void setApplicationVersion(String applicationVersion) {
      this.applicationVersion = applicationVersion;
   }

   public void setHostname(String hostname) {
      this.hostname = hostname;
   }

   public void setIps(List<String> ips) {
      this.ips = ips;
   }

   public void setJavaProperties(Hashtable<String, String> javaProperties) {
      this.javaProperties = javaProperties;
   }

   public void setProcessId(String processId) {
      this.processId = processId;
   }

   public void setServerinfo(String serverinfo) {
      this.serverinfo = serverinfo;
   }
}
