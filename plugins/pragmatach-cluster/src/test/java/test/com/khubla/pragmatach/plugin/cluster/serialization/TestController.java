package test.com.khubla.pragmatach.plugin.cluster.serialization;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.controller.AbstractController;
import com.khubla.pragmatach.plugin.cluster.annotation.Clustered;

/**
 * @author tome
 */
@Clustered
@Controller(name = "testController")
public class TestController extends AbstractController {
   private String testString;

   public String getTestString() {
      return testString;
   }

   public void setTestString(String testString) {
      this.testString = testString;
   }
}
