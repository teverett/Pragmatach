package com.khubla.pragmatach.plugin.i8n;

import com.khubla.pragmatach.framework.api.I8NProvider;

/**
 * @author tome
 */
public class I8NImpl implements I8NProvider {
   @Override
   public String getName() {
      return "Pragmatach file-based i8n provider";
   }

   @Override
   public String getString(String name) {
      return "sdsdsdsdsd" + name;
   }
}
