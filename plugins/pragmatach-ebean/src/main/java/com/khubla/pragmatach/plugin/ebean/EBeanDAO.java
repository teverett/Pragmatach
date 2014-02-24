package com.khubla.pragmatach.plugin.ebean;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.Query;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.application.Application;
import com.khubla.pragmatach.framework.dao.AbstractDAO;
import com.khubla.pragmatach.framework.scanner.AnnotationScanner;

/**
 * @author tome
 */
public class EBeanDAO<T, I extends Serializable> extends AbstractDAO<T, I> {
   /**
    * create the EBean server
    */
   private static EbeanServer getEBeanServer() {
      try {
         /*
          * make the config
          */
         final ServerConfig serverConfig = new ServerConfig();
         serverConfig.setDefaultServer(true);
         serverConfig.setName("pragmatach");
         /*
          * DDL options
          */
         final String autoCreate = Application.getConfiguration().getParameter("ebean.autocreate");
         if (null != autoCreate) {
            if (true == Boolean.parseBoolean(autoCreate)) {
               serverConfig.setDdlGenerate(true);
               serverConfig.setDdlRun(true);
            }
         }
         final String dataSource = Application.getConfiguration().getParameter("ebean.datasource");
         if ((null != dataSource) && (dataSource.length() > 0)) {
            serverConfig.setDataSourceJndiName(dataSource);
         } else {
            /*
             * ebean datasource
             */
            final DataSourceConfig dataSourceConfig = new DataSourceConfig();
            dataSourceConfig.setDriver(Application.getConfiguration().getParameter("ebean.driver"));
            dataSourceConfig.setUsername(Application.getConfiguration().getParameter("ebean.username"));
            dataSourceConfig.setPassword(Application.getConfiguration().getParameter("ebean.password"));
            final String url = Application.getConfiguration().getParameter("ebean.url");
            if (null != url) {
               dataSourceConfig.setUrl(url);
            } else {
               throw new Exception("ebean.url must be specified");
            }
            serverConfig.setDataSourceConfig(dataSourceConfig);
         }
         /*
          * add classes
          */
         final Set<Class<?>> entityClasses = getEntityClasses();
         if (null != entityClasses) {
            for (final Class<?> clazz : entityClasses) {
               serverConfig.addClass(clazz);
            }
         }
         /*
          * the server
          */
         return EbeanServerFactory.create(serverConfig);
      } catch (final Exception e) {
         throw new ExceptionInInitializerError(e);
      }
   }

   /**
    * the annotation scanner will have run; we can just query for annotated classes
    */
   protected static Set<Class<?>> getEntityClasses() throws PragmatachException {
      try {
         return AnnotationScanner.getAllClasses(Entity.class);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getAnnotatedClasses", e);
      }
   }

   /**
    * EBean
    */
   private static EbeanServer ebeanServer = getEBeanServer();
   /**
    * the type
    */
   private final Class<T> typeClazz;
   /**
    * the identifier
    */
   private final Class<I> identifierClazz;

   public EBeanDAO(Class<T> typeClazz, Class<I> identifierClazz) {
      this.typeClazz = typeClazz;
      this.identifierClazz = identifierClazz;
   }

   @Override
   public long count() throws PragmatachException {
      return this.find().findRowCount();
   }

   /**
    * delete
    */
   public void delete(T t) throws PragmatachException {
      try {
         ebeanServer.delete(t);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in delete", e);
      }
   }

   /**
    * delete
    */
   public void deletebyId(I i) throws PragmatachException {
      try {
         ebeanServer.delete(typeClazz, i);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in deletebyId", e);
      }
   }

   /**
    * find by fluent query
    */
   public Query<T> find() throws PragmatachException {
      return ebeanServer.find(this.typeClazz);
   }

   /**
    * find by id
    */
   public T findById(I i) throws PragmatachException {
      try {
         return ebeanServer.find(typeClazz, i);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in findById", e);
      }
   }

   /**
    * findall
    */
   public List<T> getAll() throws PragmatachException {
      return ebeanServer.find(this.typeClazz).findList();
   }

   @Override
   public List<T> getAll(int start, int count) throws PragmatachException {
      return this.find().setFirstRow(start).setMaxRows(count).findList();
   }

   public Class<I> getIdentifierClazz() {
      return identifierClazz;
   }

   public Class<T> getTypeClazz() {
      return typeClazz;
   }

   public void reloadConfig() {
      ebeanServer = getEBeanServer();
   }

   /**
    * save object
    */
   public void save(T t) throws PragmatachException {
      try {
         ebeanServer.save(t);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in save", e);
      }
   }

   /**
    * update object
    */
   public void update(T t) throws PragmatachException {
      try {
         ebeanServer.update(t);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in save", e);
      }
   }
}
