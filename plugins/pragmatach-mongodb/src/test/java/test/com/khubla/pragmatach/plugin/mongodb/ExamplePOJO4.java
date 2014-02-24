package test.com.khubla.pragmatach.plugin.mongodb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author tome
 */
@Entity
public class ExamplePOJO4 {
   public static enum EE {
      e1, e2, e3
   }

   /**
    * item id
    */
   @Id
   @GeneratedValue
   private String id;
   private EE ee;

   public EE getEe() {
      return ee;
   }

   public String getId() {
      return id;
   }

   public void setEe(EE ee) {
      this.ee = ee;
   }

   public void setId(String id) {
      this.id = id;
   }
}
