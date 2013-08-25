package test.com.khubla.pragmatach.plugin.mongodb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author tome
 */
@Entity
public class ExamplePOJO1 {
   /**
    * item id
    */
   @Id
   @GeneratedValue
   private String id;
   /**
    * name
    */
   private String name;
   /**
    * int
    */
   private int intNumber;
   /**
    * double
    */
   private double doubleNumber;

   public double getDoubleNumber() {
      return doubleNumber;
   }

   public String getId() {
      return id;
   }

   public int getIntNumber() {
      return intNumber;
   }

   public String getName() {
      return name;
   }

   public void setDoubleNumber(double doubleNumber) {
      this.doubleNumber = doubleNumber;
   }

   public void setId(String id) {
      this.id = id;
   }

   public void setIntNumber(int intNumber) {
      this.intNumber = intNumber;
   }

   public void setName(String name) {
      this.name = name;
   }
}
