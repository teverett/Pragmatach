package com.khubla.pragmatach.framework.url;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.uri.antlr.RouteSpecificationLexer;
import com.khubla.pragmatach.framework.uri.antlr.RouteSpecificationParser;

/**
 * @author tome
 */
public class RouteSpecification {
   /**
    * parse an inputstream
    */
   private static CommonTree parse(InputStream inputStream) throws Exception {
      try {
         if (null != inputStream) {
            final RouteSpecificationLexer routeSpecificationLexer = new RouteSpecificationLexer(new ANTLRInputStream(inputStream));
            final CommonTokenStream tokens = new CommonTokenStream(routeSpecificationLexer);
            final RouteSpecificationParser routeSpecificationParser = new RouteSpecificationParser(tokens);
            final RouteSpecificationParser.routespecification_return ret = routeSpecificationParser.routespecification();
            final CommonTree tree = ret.getTree();
            return tree;
         } else {
            throw new IllegalArgumentException();
         }
      } catch (final Exception e) {
         throw new Exception("Exception in parse", e);
      }
   }

   public static RouteSpecification parse(String uri) throws PragmatachException {
      try {
         final List<RouteSpecificationSegment> ret = new ArrayList<RouteSpecificationSegment>();
         final CommonTree commonTree = parse(new ByteArrayInputStream(uri.getBytes()));
         if (null != commonTree) {
            for (int i = 0; i < commonTree.getChildCount(); i++) {
               final CommonTree n = (CommonTree) commonTree.getChild(i);
               if (n.getType() == RouteSpecificationParser.STRING) {
                  ret.add(new RouteSpecificationSegment(null, n.getText(), null));
               }
            }
         }
         return new RouteSpecification(ret);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in parse", e);
      }
   }

   private Hashtable<String, RouteSpecificationSegment> ids;
   /**
    * segments
    */
   private final List<RouteSpecificationSegment> segments;

   /**
    * ctor
    */
   private RouteSpecification(List<RouteSpecificationSegment> segments) {
      this.segments = segments;
   }

   public Hashtable<String, RouteSpecificationSegment> getIds() {
      return ids;
   }

   public List<RouteSpecificationSegment> getSegments() {
      return segments;
   }

   public void setIds(Hashtable<String, RouteSpecificationSegment> ids) {
      this.ids = ids;
   }
}
