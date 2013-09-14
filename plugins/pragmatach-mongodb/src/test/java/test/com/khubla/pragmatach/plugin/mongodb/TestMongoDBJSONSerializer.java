package test.com.khubla.pragmatach.plugin.mongodb;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.khubla.pragmatach.plugin.mongodb.BasicObjectSerializer;
import com.khubla.pragmatach.plugin.mongodb.ObjectSerializer;
import com.mongodb.BasicDBObject;

/**
 * @author tom
 */
public class TestMongoDBJSONSerializer {
   @Test(enabled = true)
   public void testCompoundSerializeDeserialize() {
      try {
         final ObjectSerializer mongoDBJSONSerializer = new BasicObjectSerializer(ExamplePOJO1.class);
         /*
          * pojo
          */
         final ExamplePOJO1 examplePOJO1 = new ExamplePOJO1();
         examplePOJO1.setDoubleNumber(123.4);
         examplePOJO1.setIntNumber(5);
         examplePOJO1.setId("0x545454");
         examplePOJO1.setName("abc123");
         /*
          * serialize
          */
         final BasicDBObject basicDBObject = mongoDBJSONSerializer.serialize(examplePOJO1);
         Assert.assertNotNull(basicDBObject);
         Assert.assertTrue(basicDBObject.size() > 0);
         /*
          * deserialize
          */
         final ExamplePOJO1 examplePOJO2 = new ExamplePOJO1();
         mongoDBJSONSerializer.deserialize(basicDBObject, examplePOJO2);
         /*
          * check
          */
         Assert.assertTrue(examplePOJO2.getDoubleNumber() == examplePOJO1.getDoubleNumber());
         Assert.assertTrue(examplePOJO2.getId().compareTo(examplePOJO1.getId()) == 0);
         Assert.assertTrue(examplePOJO2.getIntNumber() == examplePOJO1.getIntNumber());
         Assert.assertTrue(examplePOJO2.getName().compareTo(examplePOJO1.getName()) == 0);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }

   @Test
   public void testSimpleSerializeDeserialize() {
      try {
         final ObjectSerializer mongoDBJSONSerializer = new BasicObjectSerializer(ExamplePOJO1.class);
         /*
          * pojo
          */
         final ExamplePOJO1 examplePOJO1 = new ExamplePOJO1();
         examplePOJO1.setDoubleNumber(123.4);
         examplePOJO1.setIntNumber(5);
         examplePOJO1.setId("0x545454");
         examplePOJO1.setName("abc123");
         /*
          * serialize
          */
         final BasicDBObject basicDBObject = mongoDBJSONSerializer.serialize(examplePOJO1);
         Assert.assertNotNull(basicDBObject);
         Assert.assertTrue(basicDBObject.size() > 0);
         /*
          * deserialize
          */
         final ExamplePOJO1 examplePOJO2 = new ExamplePOJO1();
         mongoDBJSONSerializer.deserialize(basicDBObject, examplePOJO2);
         /*
          * check
          */
         Assert.assertTrue(examplePOJO2.getDoubleNumber() == examplePOJO1.getDoubleNumber());
         Assert.assertTrue(examplePOJO2.getId().compareTo(examplePOJO1.getId()) == 0);
         Assert.assertTrue(examplePOJO2.getIntNumber() == examplePOJO1.getIntNumber());
         Assert.assertTrue(examplePOJO2.getName().compareTo(examplePOJO1.getName()) == 0);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
