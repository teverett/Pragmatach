package test.com.khubla.pragmatach.plugin.json.controller;

import java.util.List;
import java.util.Set;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.plugin.json.JSONController;

@Controller()
public class CollectionController extends JSONController {
   private List<String> stringList;
   private Set<String> stringSet;

   /**
    * ctor
    */
   public CollectionController() {
   }

   public List<String> getStringList() {
      return stringList;
   }

   public Set<String> getStringSet() {
      return stringSet;
   }

   public void setStringList(List<String> stringList) {
      this.stringList = stringList;
   }

   public void setStringSet(Set<String> stringSet) {
      this.stringSet = stringSet;
   }
}