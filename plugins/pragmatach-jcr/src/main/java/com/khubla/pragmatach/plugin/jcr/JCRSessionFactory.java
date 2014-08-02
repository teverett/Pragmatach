package com.khubla.pragmatach.plugin.jcr;

import javax.jcr.Repository;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.apache.jackrabbit.commons.JcrUtils;
import org.apache.log4j.Logger;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.application.Application;

/**
 * @author tome
 */
public class JCRSessionFactory {
   /**
    * logger
    */
   private final Logger logger = Logger.getLogger(this.getClass());

   /**
    * get password
    */
   private char[] getPassword() throws PragmatachException {
      try {
         final String pwd = Application.getConfiguration().getParameter("jcr.password");
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
         /*
          * connect to repo
          */
         final String repositoryURL = getURL();
         if ((null != repositoryURL) && (repositoryURL.length() > 0)) {
            final Repository repository = JcrUtils.getRepository(repositoryURL);
            if (null != repository) {
               /*
                * log
                */
               logger.info("Found repository '" + repositoryURL + "'");
               /*
                * login to workspace
                */
               char[] password = getPassword();
               String username = getUsername();
               if ((null != password) && (null != username)) {
                  final SimpleCredentials credentials = new SimpleCredentials(username, password);
                  final String workspace = getWorkspace();
                  final Session session = repository.login(credentials, workspace);
                  /*
                   * log
                   */
                  logger.info("Logged into workspace '" + workspace + "'");
                  /*
                   * done
                   */
                  return session;
               } else {
                  throw new PragmatachException("Invalid username or password for repository '" + repositoryURL + "'");
               }
            } else {
               throw new PragmatachException("Unable to connect to repository '" + repositoryURL + "'");
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
         return Application.getConfiguration().getParameter("jcr.url");
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getUsername", e);
      }
   }

   /**
    * get username
    */
   private String getUsername() throws PragmatachException {
      try {
         return Application.getConfiguration().getParameter("jcr.username");
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getUsername", e);
      }
   }

   /**
    * get workspace
    */
   private String getWorkspace() throws PragmatachException {
      try {
         return Application.getConfiguration().getParameter("jcr.workspace");
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getUsername", e);
      }
   }
}
