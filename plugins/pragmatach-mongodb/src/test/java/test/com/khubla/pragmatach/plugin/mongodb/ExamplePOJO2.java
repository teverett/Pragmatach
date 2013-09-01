package test.com.khubla.pragmatach.plugin.mongodb;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author tome
 */
@Entity
public class ExamplePOJO2 {
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
   private Set<ExamplePOJO1> examplePOJO1s;

   public Set<ExamplePOJO1> getExamplePOJO1s() {
      return examplePOJO1s;
   }

   public String getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public void setExamplePOJO1s(Set<ExamplePOJO1> examplePOJO1s) {
      this.examplePOJO1s = examplePOJO1s;
   }

   public void setId(String id) {
      this.id = id;
   }

   public void setName(String name) {
      this.name = name;
   }
}
