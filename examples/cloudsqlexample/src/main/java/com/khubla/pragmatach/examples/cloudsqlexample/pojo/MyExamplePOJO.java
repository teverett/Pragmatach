package com.khubla.pragmatach.examples.cloudsqlexample.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.khubla.pragmatach.plugin.ebean.EBeanDAO;

/**
 * @author tome
 */
@Entity
public class MyExamplePOJO {
   /**
    * primary key
    */
   @Id
   @GeneratedValue
   private Long id;
   /**
    * some name
    */
   private String name;
   /**
    * an EBean DAO for MyExamplePOJOs
    */
   public static EBeanDAO<MyExamplePOJO, Long> dao = new EBeanDAO<MyExamplePOJO, Long>(MyExamplePOJO.class, Long.class);

   public Long getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public void setName(String name) {
      this.name = name;
   }
}
