package com.khubla.pragmatach.plugin.jcr;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import javax.jcr.Repository;
import javax.jcr.RepositoryFactory;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.servlet.PragmatachServlet;

/**
 * @author tome
 */
public class JCRSessionFactory {
   /**
    * get password
    */
   private char[] getPassword() throws PragmatachException {
      try {
         final String pwd = PragmatachServlet.getConfiguration().getParameter("jcr.password");
         if (null != pwd) {
            return pwd.toCharArray();
         }
         return null;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getPassword", e);
      }
   }

   public Session getSession() throws PragmatachException {
      try {
         final Map parameters = new HashMap();
         Repository repository = null;
         for (final RepositoryFactory factory : ServiceLoader.load(RepositoryFactory.class)) {
            repository = factory.getRepository(parameters);
            if (repository != null) {
               break;
            }
         }
         final SimpleCredentials credentials = new SimpleCredentials(getUsername(), getPassword());
         return repository.login(credentials);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getRepository", e);
      }
   }

   /**
    * get username
    */
   private String getUsername() throws PragmatachException {
      try {
         return PragmatachServlet.getConfiguration().getParameter("jcr.username");
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getUsername", e);
      }
   }
}
