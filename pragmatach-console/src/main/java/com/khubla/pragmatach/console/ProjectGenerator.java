package com.khubla.pragmatach.console;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author tome
 */
public class ProjectGenerator {
   /**
    * generate the indexController
    */
   private static void generateIndexController(String dir, String name, String namespace) throws Exception {
      try {
         final Configuration cfg = new Configuration();
         final Template template = new Template("", new InputStreamReader(ProjectGenerator.class.getResourceAsStream("/IndexController.java.ftl")), cfg);
         final Map<String, Object> input = new HashMap<String, Object>();
         input.put("name", name);
         input.put("namespace", namespace);
         final Writer writer = new FileWriter(new File(dir + "/IndexController.java"));
         template.process(input, writer);
      } catch (final Exception e) {
         throw new Exception("Exception in generateIndexController", e);
      }
   }

   /**
    * generate index.ftl
    */
   private static void generateIndexFTL(String dir, String name, String namespace) throws Exception {
      try {
         final Configuration cfg = new Configuration();
         final Template template = new Template("", new InputStreamReader(ProjectGenerator.class.getResourceAsStream("/webapp/index.ftl.ftl")), cfg);
         final Map<String, Object> input = new HashMap<String, Object>();
         input.put("name", name);
         input.put("namespace", namespace);
         final Writer writer = new FileWriter(new File(dir + "/index.ftl"));
         template.process(input, writer);
      } catch (final Exception e) {
         throw new Exception("Exception in generateIndexFTL", e);
      }
   }

   /**
    * generate the log4j.xml
    */
   private static void generateLog4JXML(String dir, String name, String namespace) throws Exception {
      try {
         final Configuration cfg = new Configuration();
         final Template template = new Template("", new InputStreamReader(ProjectGenerator.class.getResourceAsStream("/log4j.xml.ftl")), cfg);
         final Map<String, Object> input = new HashMap<String, Object>();
         input.put("name", name);
         input.put("namespace", namespace);
         final Writer writer = new FileWriter(new File(dir + "/log4j.xml"));
         template.process(input, writer);
      } catch (final Exception e) {
         throw new Exception("Exception in generateWebXML", e);
      }
   }

   /**
    * generate the POM
    */
   private static void generatePOM(String dir, String name, String namespace) throws Exception {
      try {
         final Configuration cfg = new Configuration();
         final Template template = new Template("", new InputStreamReader(ProjectGenerator.class.getResourceAsStream("/pom.xml.ftl")), cfg);
         final Map<String, Object> input = new HashMap<String, Object>();
         input.put("name", name);
         input.put("namespace", namespace);
         final Writer writer = new FileWriter(new File(dir + "/pom.xml"));
         template.process(input, writer);
      } catch (final Exception e) {
         throw new Exception("Exception in generatePOM", e);
      }
   }

   /**
    * generate the web.xml
    */
   private static void generateWebXML(String dir, String name, String namespace) throws Exception {
      try {
         final Configuration cfg = new Configuration();
         final Template template = new Template("", new InputStreamReader(ProjectGenerator.class.getResourceAsStream("/web.xml.ftl")), cfg);
         final Map<String, Object> input = new HashMap<String, Object>();
         input.put("name", name);
         input.put("namespace", namespace);
         final Writer writer = new FileWriter(new File(dir + "/web.xml"));
         template.process(input, writer);
      } catch (final Exception e) {
         throw new Exception("Exception in generateWebXML", e);
      }
   }

   /**
    * namespace to dir
    */
   private static String namespaceToDir(String namespace) throws Exception {
      try {
         return StringUtils.replace(namespace, ".", "/");
      } catch (final Exception e) {
         throw new Exception("Exception in namespaceToDir", e);
      }
   }

   public void generate(String name, String namespace) throws Exception {
      try {
         /*
          * make the dirs
          */
         final String ns = namespaceToDir(namespace);
         final String cwd = System.getProperty("user.dir");
         final String javaDir = cwd + "/" + name + "/src/main/java/" + ns;
         final String resourcesDir = cwd + "/" + name + "/src/main/resources";
         final String javaTestDir = cwd + "/" + name + "/src/test/java/test/" + ns;
         final String javaTestResourcesDir = cwd + "/" + name + "/src/test/resources";
         final String webDir = cwd + "/" + name + "/src/main/webapp/";
         final String webINFDir = cwd + "/" + name + "/src/main/webapp/WEB-INF";
         new File(javaDir).mkdirs();
         new File(resourcesDir).mkdirs();
         new File(javaTestDir).mkdirs();
         new File(webDir).mkdirs();
         new File(javaTestResourcesDir).mkdirs();
         new File(webINFDir).mkdirs();
         generatePOM(cwd + "/" + name, name, namespace);
         generateWebXML(webINFDir, name, namespace);
         generateLog4JXML(resourcesDir, name, namespace);
         generateIndexFTL(webDir, name, namespace);
         generateIndexController(javaDir, name, namespace);
         copyImage(webDir);
      } catch (final Exception e) {
         throw new Exception("Exception in generate", e);
      }
   }

   private void copyImage(String webDir) throws Exception {
      try {
         File f = new File(webDir + "/logo.png");
         IOUtils.copy(ProjectGenerator.class.getResourceAsStream("/webapp/logo.png"), new FileOutputStream(f));
      } catch (final Exception e) {
         throw new Exception("Exception in copyImage", e);
      }
   }
}
