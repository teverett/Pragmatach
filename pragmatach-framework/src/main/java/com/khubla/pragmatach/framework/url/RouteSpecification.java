package com.khubla.pragmatach.framework.url;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
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
    * id fields
    */
   private final List<String> ids;
   /**
    * segments
    */
   private final List<RouteSpecificationSegment> segments;

   /**
    * ctor
    */
   public RouteSpecification(String uri) throws PragmatachException {
      segments = parse(uri);
      if ((null != segments) && (segments.size() > 0)) {
         ids = new ArrayList<String>();
         for (final RouteSpecificationSegment routeSpecificationSegment : segments) {
            if (null != routeSpecificationSegment.getVariableId()) {
               ids.add(routeSpecificationSegment.getVariableId());
            }
         }
      } else {
         ids = null;
      }
   }

   public List<String> getIds() {
      return ids;
   }

   public List<RouteSpecificationSegment> getSegments() {
      return segments;
   }

   /**
    * parse an inputstream
    */
   private CommonTree parse(InputStream inputStream) throws PragmatachException {
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
         throw new PragmatachException("Exception in parse", e);
      }
   }

   /**
    * parse a URI into parts.
    */
   private List<RouteSpecificationSegment> parse(String uri) throws PragmatachException {
      try {
         final List<RouteSpecificationSegment> ret = new ArrayList<RouteSpecificationSegment>();
         final CommonTree commonTree = parse(new ByteArrayInputStream(uri.getBytes()));
         if (null != commonTree) {
            if (commonTree.getChildCount() > 0) {
               for (int i = 0; i < commonTree.getChildCount(); i++) {
                  final CommonTree n = (CommonTree) commonTree.getChild(i);
                  final RouteSpecificationSegment rss = parseNode(n);
                  if (null != rss) {
                     ret.add(parseNode(n));
                  }
               }
            } else {
               final RouteSpecificationSegment rss = parseNode(commonTree);
               ret.add(rss);
            }
         } else {
            ret.add(new RouteSpecificationSegment("/", null));
         }
         return ret;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in parse", e);
      }
   }

   private RouteSpecificationSegment parseNode(CommonTree commonTree) throws PragmatachException {
      try {
         if (commonTree.getType() == RouteSpecificationParser.ALPHANUM) {
            return new RouteSpecificationSegment(commonTree.getText(), null);
         } else {
            final CommonTree m = (CommonTree) commonTree.getChild(0);
            if (m.getType() == RouteSpecificationParser.ALPHANUM) {
               return new RouteSpecificationSegment(null, m.getText());
            } else {
               return null;
            }
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in parseNode", e);
      }
   }
}
