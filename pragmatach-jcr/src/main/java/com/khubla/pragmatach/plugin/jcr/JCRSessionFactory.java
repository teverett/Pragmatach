package com.khubla.pragmatach.plugin.jcr;

import javax.jcr.Repository;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.apache.jackrabbit.commons.JcrUtils;

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

   /**
    * get a repository Session
    */
   public Session getSession() throws PragmatachException {
      try {
         final String repositoryName = getURL();
         if ((null != repositoryName) && (repositoryName.length() > 0)) {
            final Repository repository = JcrUtils.getRepository(repositoryName);
            if (null != repository) {
               final SimpleCredentials credentials = new SimpleCredentials(getUsername(), getPassword());
               return repository.login(credentials);
            } else {
               throw new PragmatachException("Unable to connect to repository '" + repositoryName + "'");
            }
         } else {
            throw new PragmatachException("Please suppy a repository URL");
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getRepository", e);
      }
   }

   /**
    * get url
    */
   private String getURL() throws PragmatachException {
      try {
         return PragmatachServlet.getConfiguration().getParameter("jcr.url");
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getUsername", e);
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
