package com.khubla.pragmatach.plugin.adminapp;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;

/**
 * @author tome
 */
@Controller(name = "pragmatachShowJVMController")
@View(view = "pragmatach/admin/jvm.html")
public class ShowJVMController extends AbstractAdminController {
   /**
    * java properties
    */
   private Hashtable<String, String> javaProperties;

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

   public Hashtable<String, String> getJavaProperties() {
      return javaProperties;
   }

   @Route(uri = "/pragmatach/admin/jvm")
   public Response render() throws PragmatachException {
      javaProperties = findJavaProperties();
      return super.render();
   }

   public void setJavaProperties(Hashtable<String, String> javaProperties) {
      this.javaProperties = javaProperties;
   }
}
