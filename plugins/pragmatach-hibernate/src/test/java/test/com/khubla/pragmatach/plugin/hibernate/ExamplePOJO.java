package test.com.khubla.pragmatach.plugin.hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.khubla.pragmatach.plugin.hibernate.HibernateDAO;

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
   public static HibernateDAO<ExamplePOJO, Long> dao = new HibernateDAO<ExamplePOJO, Long>(ExamplePOJO.class, Long.class);

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
