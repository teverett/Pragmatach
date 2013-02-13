package com.khubla.pragmatach.console;

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
public class PragmatachConsole {
   /**
    * operation
    */
   private final static String OPTION_OPERATION = "operation";
   /**
    * name
    */
   private final static String OPTION_NAME = "name";
   /**
    * namespace
    */
   private final static String OPTION_NAMESPACE = "namespace";
   /**
    * operation new
    */
   private final static String OPERATION_NEW = "new";

   @SuppressWarnings("static-access")
   public static void main(String[] args) {
      try {
         System.out.println("khubla.com Pragmatach Web Framework");
         /*
          * options
          */
         final Options options = new Options();
         final Option oo = OptionBuilder.withArgName(OPTION_OPERATION).isRequired(false).withType(String.class).hasArg().withDescription("operation").create(OPTION_OPERATION);
         options.addOption(oo);
         final Option on = OptionBuilder.withArgName(OPTION_NAME).isRequired(false).withType(String.class).hasArg().withDescription("name").create(OPTION_NAME);
         options.addOption(on);
         final Option ons = OptionBuilder.withArgName(OPTION_NAMESPACE).isRequired(false).withType(String.class).hasArg().withDescription("namespace").create(OPTION_NAMESPACE);
         options.addOption(ons);
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
          * ugh
          */
         if (cmd.hasOption(OPTION_OPERATION)) {
            final String operation = cmd.getOptionValue(OPTION_OPERATION);
            if (null != operation) {
               if (operation.compareTo(OPERATION_NEW) == 0) {
                  final String name = cmd.getOptionValue(OPTION_NAME);
                  final String namespace = cmd.getOptionValue(OPTION_NAMESPACE);
                  System.out.println("Generating project '" + name + "' in namespace '" + namespace + "'");
                  final ProjectGenerator projectGenerator = new ProjectGenerator();
                  projectGenerator.generate(name, namespace);
               }
            }
         }
      } catch (final Exception e) {
         e.printStackTrace();
      }
   }
}