package com.khubla.pragmatach.examples.amazonrdsexample.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.khubla.pragmatach.plugin.ebean.EBeanDAO;

/**
 * @author tome
 */
@Entity
public class AccessLogPOJO {
   /**
    * primary key
    */
   @Id
   @GeneratedValue
   private Long id;
   /**
    * ip
    */
   private String ip;
   /**
    * date
    */
   private Date date;
   /**
    * an EBean DAO for MyExamplePOJOs
    */
   public static EBeanDAO<AccessLogPOJO, Long> dao = new EBeanDAO<AccessLogPOJO, Long>(AccessLogPOJO.class, Long.class);

   public Long getId() {
      return id;
   }

   public String getIp() {
      return ip;
   }

   public void setIp(String ip) {
      this.ip = ip;
   }

   public Date getDate() {
      return date;
   }

   public void setDate(Date date) {
      this.date = date;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public AccessLogPOJO() {
   }

   public AccessLogPOJO(String ip) {
      this.ip = ip;
      this.date = new Date();
   }
}
