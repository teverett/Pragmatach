package test.com.khubla.pragmatach.plugin.openjpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.khubla.pragmatach.plugin.openjpa.OpenJPADAO;

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
    * dao
    */
   public static OpenJPADAO<ExamplePOJO, Long> dao = new OpenJPADAO<ExamplePOJO, Long>(ExamplePOJO.class, Long.class);

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
