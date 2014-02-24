package test.com.khubla.pragmatach.plugin.mongodb;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author tome
 */
@Entity
public class ExamplePOJO6 {
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
    * pojo1
    */
   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
   private ExamplePOJO1 examplePOJO1;

   public ExamplePOJO1 getExamplePOJO1() {
      return examplePOJO1;
   }

   public String getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public void setExamplePOJO1(ExamplePOJO1 examplePOJO1) {
      this.examplePOJO1 = examplePOJO1;
   }

   public void setId(String id) {
      this.id = id;
   }

   public void setName(String name) {
      this.name = name;
   }
}
