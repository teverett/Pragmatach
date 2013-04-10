package com.khubla.pragmatach.framework.test;

import java.io.Serializable;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.khubla.pragmatach.framework.application.Application;
import com.khubla.pragmatach.framework.configuration.PropertiesFileConfigurationImpl;
import com.khubla.pragmatach.framework.dao.DAO;
import com.khubla.pragmatach.framework.dao.Pager;
import com.khubla.pragmatach.framework.scanner.AnnotationScanner;

/**
 * @author tome
 */
public abstract class AbstractDAOTest<T, I extends Serializable> {
   /**
    * get the DAO
    */
   public abstract DAO<T, I> getDAO();

   /**
    * get id
    */
   public abstract I getId(T t);

   /**
    * get an instance
    */
   public abstract T getInstance();

   /**
    * for the class
    */
   @BeforeClass
   public void setupClass() {
      /*
       * run the scanner
       */
      try {
         AnnotationScanner.scan(null);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
      /*
       * set the config
       */
      Application.setConfiguration(new PropertiesFileConfigurationImpl());
   }

   /**
    * basic CRUD tests on the DAO
    */
   @Test(enabled = true)
   public void testCRUD() {
      try {
         /*
          * get DAO
          */
         final DAO<T, I> dao = this.getDAO();
         Assert.assertNotNull(dao);
         /*
          * get an instance
          */
         final T t = this.getInstance();
         Assert.assertNotNull(t);
         /*
          * there are no rows
          */
         long count = dao.count();
         Assert.assertTrue(0 == count);
         /*
          * get all
          */
         List<T> all = dao.getAll();
         Assert.assertNotNull(all);
         Assert.assertTrue(all.size() == 0);
         /*
          * save the instance
          */
         dao.save(t);
         final I id = this.getId(t);
         Assert.assertNotNull(id);
         /*
          * there's 1 row
          */
         count = dao.count();
         Assert.assertTrue(1 == count);
         /*
          * get it back
          */
         final T retrieved = dao.findById(id);
         final I retrievedId = this.getId(retrieved);
         Assert.assertNotNull(retrieved);
         Assert.assertNotNull(retrievedId);
         /*
          * get a list of rows
          */
         all = dao.getAll();
         Assert.assertNotNull(all);
         Assert.assertTrue(all.size() == 1);
         /*
          * delete it
          */
         dao.delete(t);
         /*
          * no rows
          */
         count = dao.count();
         Assert.assertTrue(0 == count);
         /*
          * it's gone
          */
         final T retrieved2 = dao.findById(id);
         Assert.assertNull(retrieved2);
         /*
          * nothing in list
          */
         all = dao.getAll();
         Assert.assertNotNull(all);
         Assert.assertTrue(all.size() == 0);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }

   /**
    * test that we can get pager
    */
   @Test(enabled = true)
   public void testPager() {
      try {
         /*
          * get DAO
          */
         final DAO<T, I> dao = this.getDAO();
         Assert.assertNotNull(dao);
         /*
          * get pager
          */
         final Pager<T> pager = dao.getPager(10);
         Assert.assertNotNull(pager);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
