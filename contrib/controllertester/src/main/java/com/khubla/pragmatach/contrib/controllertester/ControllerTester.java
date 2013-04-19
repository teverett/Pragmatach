package com.khubla.pragmatach.contrib.controllertester;

import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;

/**
 * @author tome
 */
public class ControllerTester {
   /**
    * controllers file option
    */
   private static final String FILE_OPTION = "file";
   /**
    * target option
    */
   private static final String URL_OPTION = "url";

   /**
    * the usual
    */
   @SuppressWarnings("static-access")
   public static void main(String[] args) {
      try {
         /*
          * options for commons-cli
          */
         final Options options = new Options();
         final Option fo = OptionBuilder.withArgName(FILE_OPTION).isRequired(true).withType(String.class).hasArg().withDescription("controllers file").create(FILE_OPTION);
         options.addOption(fo);
         final Option uo = OptionBuilder.withArgName(URL_OPTION).isRequired(true).withType(String.class).hasArg().withDescription("application url").create(URL_OPTION);
         options.addOption(uo);
         /*
          * parse
          */
         final CommandLineParser parser = new PosixParser();
         CommandLine cmd = null;
         try {
            cmd = parser.parse(options, args);
         } catch (final Exception e) {
            e.printStackTrace();
            final HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("posix", options);
            System.exit(0);
         }
         /*
          * vars
          */
         final String url = cmd.getOptionValue(URL_OPTION);
         final String file = cmd.getOptionValue(FILE_OPTION);
         /*
          * read
          */
         final List<RouteUrl> controllerUrls = RouteUrl.readRoutes(new FileInputStream(file));
         /*
          * test
          */
         testUrls(controllerUrls, url);
      } catch (final Exception e) {
         e.printStackTrace();
      }
   }

   /**
    * test the routes
    */
   private static void testUrls(List<RouteUrl> controllerUrls, String url) throws Exception {
      try {
         for (final RouteUrl routeUrl : controllerUrls) {
            RouteTester.testRoute(url, routeUrl, 1);
         }
      } catch (final Exception e) {
         throw new Exception("Exception in testUrls", e);
      }
   }
}
