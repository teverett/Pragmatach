package com.khubla.pragmatach.plugin.jcr;

import com.khubla.pragmatach.framework.controller.impl.template.AbstractTemplateEngineController;

/**
 * @author tome
 */
public class JCRController extends AbstractTemplateEngineController {
   /***
    * JCRSessionFactory
    */
   protected final JCRSessionFactory jcrSessionFactory = new JCRSessionFactory();

   /**
    * ctor
    */
   public JCRController() {
   }
}
