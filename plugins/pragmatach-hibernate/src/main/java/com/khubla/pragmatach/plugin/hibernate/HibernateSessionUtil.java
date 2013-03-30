package com.khubla.pragmatach.plugin.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * @author tome
 */
public class HibernateSessionUtil {
   private static final SessionFactory sessionFactory = buildSessionFactory();

   private static SessionFactory buildSessionFactory() {
      try {
         return new AnnotationConfiguration().configure().buildSessionFactory();
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
