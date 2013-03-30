package com.khubla.pragmatach.plugin.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.khubla.pragmatach.framework.application.Application;

/**
 * @author tome
 */
public class HibernateSessionUtil {
   /**
    * the hibernate session factory
    */
   private static final SessionFactory sessionFactory = buildSessionFactory();
   /**
    * the hibernate service registry
    */
   private static ServiceRegistry serviceRegistry;

   private static SessionFactory buildSessionFactory() {
      try {
         final Configuration configuration = new Configuration();
         configuration.setProperty("hibernate.driver", Application.getConfiguration().getParameter("hibernate.driver"));
         configuration.setProperty("hibernate.dialect", Application.getConfiguration().getParameter("hibernate.dialect"));
         configuration.setProperty("hibernate.connection.url", Application.getConfiguration().getParameter("hibernate.connection.url"));
         configuration.setProperty("hibernate.connection.username", Application.getConfiguration().getParameter("hibernate.connection.username"));
         configuration.setProperty("hibernate.connection.password", Application.getConfiguration().getParameter("hibernate.connection.password"));
         serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
         return configuration.buildSessionFactory(serviceRegistry);
      } catch (final Throwable ex) {
         System.err.println("Initial SessionFactory creation failed." + ex);
         throw new ExceptionInInitializerError(ex);
      }
   }

   public static SessionFactory getSessionFactory() {
      return sessionFactory;
   }

   /**
    * check that we can get a sessionfactory
    */
   public static boolean isValid() throws Exception {
      try {
         return (null != getSessionFactory());
      } catch (final Exception e) {
         throw new Exception("Exception in isValid", e);
      }
   }

   public static void shutdown() {
      getSessionFactory().close();
   }
}
