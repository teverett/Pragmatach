package com.khubla.pragmatach.dbtestsuite.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author tome
 */
@Entity
public class ExamplePOJO {
   /**
    * item id
    */
   @Id
   @GeneratedValue
   private Long id;
   /**
    * name
    */
   private String name;
   /**
    * int
    */
   private int intNumber;

   public int getIntNumber() {
      return intNumber;
   }

   public void setIntNumber(int intNumber) {
      this.intNumber = intNumber;
   }

   public double getDoubleNumber() {
      return doubleNumber;
   }

   public void setDoubleNumber(double doubleNumber) {
      this.doubleNumber = doubleNumber;
   }

   /**
    * double
    */
   private double doubleNumber;

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
