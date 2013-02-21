// $ANTLR 3.5 com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g 2013-02-20 19:30:22
package com.khubla.pragmatach.framework.uri.antlr;

import org.antlr.runtime.BitSet;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.TreeAdaptor;

@SuppressWarnings("all")
public class RouteSpecificationParser extends Parser {
   public static class dynamicsegment_return extends ParserRuleReturnScope {
      CommonTree tree;

      @Override
      public CommonTree getTree() {
         return tree;
      }
   }

   public static class expression_return extends ParserRuleReturnScope {
      CommonTree tree;

      @Override
      public CommonTree getTree() {
         return tree;
      }
   }

   public static class id_return extends ParserRuleReturnScope {
      CommonTree tree;

      @Override
      public CommonTree getTree() {
         return tree;
      }
   }

   public static class routespecification_return extends ParserRuleReturnScope {
      CommonTree tree;

      @Override
      public CommonTree getTree() {
         return tree;
      }
   }

   public static class staticsegment_return extends ParserRuleReturnScope {
      CommonTree tree;

      @Override
      public CommonTree getTree() {
         return tree;
      }
   }

   public static final String[] tokenNames = new String[] { "<invalid>", "<EOR>", "<DOWN>", "<UP>", "STRING", "'/'", "'@'", "'{'", "'}'" };
   public static final int EOF = -1;
   public static final int T__5 = 5;
   // delegators
   public static final int T__6 = 6;
   public static final int T__7 = 7;
   public static final int T__8 = 8;
   public static final int STRING = 4;
   protected TreeAdaptor adaptor = new CommonTreeAdaptor();
   public static final BitSet FOLLOW_5_in_routespecification74 = new BitSet(new long[] { 0x0000000000000090L });
   public static final BitSet FOLLOW_staticsegment_in_routespecification77 = new BitSet(new long[] { 0x0000000000000022L });
   public static final BitSet FOLLOW_dynamicsegment_in_routespecification81 = new BitSet(new long[] { 0x0000000000000022L });;
   public static final BitSet FOLLOW_STRING_in_staticsegment95 = new BitSet(new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_7_in_dynamicsegment104 = new BitSet(new long[] { 0x0000000000000010L });;
   public static final BitSet FOLLOW_expression_in_dynamicsegment106 = new BitSet(new long[] { 0x0000000000000100L });
   public static final BitSet FOLLOW_8_in_dynamicsegment108 = new BitSet(new long[] { 0x0000000000000042L });;
   public static final BitSet FOLLOW_6_in_dynamicsegment111 = new BitSet(new long[] { 0x0000000000000010L });
   public static final BitSet FOLLOW_id_in_dynamicsegment113 = new BitSet(new long[] { 0x0000000000000002L });;
   public static final BitSet FOLLOW_STRING_in_expression124 = new BitSet(new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_STRING_in_id133 = new BitSet(new long[] { 0x0000000000000002L });;

   public RouteSpecificationParser(TokenStream input) {
      this(input, new RecognizerSharedState());
   }

   // Delegated rules
   public RouteSpecificationParser(TokenStream input, RecognizerSharedState state) {
      super(input, state);
   }

   // $ANTLR start "dynamicsegment"
   // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:23:1: dynamicsegment : '{' expression '}' ( '@' id )? ;
   public final RouteSpecificationParser.dynamicsegment_return dynamicsegment() throws RecognitionException {
      final RouteSpecificationParser.dynamicsegment_return retval = new RouteSpecificationParser.dynamicsegment_return();
      retval.start = input.LT(1);
      CommonTree root_0 = null;
      Token char_literal5 = null;
      Token char_literal7 = null;
      Token char_literal8 = null;
      ParserRuleReturnScope expression6 = null;
      ParserRuleReturnScope id9 = null;
      CommonTree char_literal5_tree = null;
      CommonTree char_literal7_tree = null;
      CommonTree char_literal8_tree = null;
      try {
         // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:24:2: ( '{' expression '}' ( '@' id )? )
         // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:24:4: '{' expression '}' ( '@' id )?
         {
            root_0 = (CommonTree) adaptor.nil();
            char_literal5 = (Token) match(input, 7, FOLLOW_7_in_dynamicsegment104);
            if (state.failed) {
               return retval;
            }
            if (state.backtracking == 0) {
               char_literal5_tree = (CommonTree) adaptor.create(char_literal5);
               adaptor.addChild(root_0, char_literal5_tree);
            }
            pushFollow(FOLLOW_expression_in_dynamicsegment106);
            expression6 = expression();
            state._fsp--;
            if (state.failed) {
               return retval;
            }
            if (state.backtracking == 0) {
               adaptor.addChild(root_0, expression6.getTree());
            }
            char_literal7 = (Token) match(input, 8, FOLLOW_8_in_dynamicsegment108);
            if (state.failed) {
               return retval;
            }
            if (state.backtracking == 0) {
               char_literal7_tree = (CommonTree) adaptor.create(char_literal7);
               adaptor.addChild(root_0, char_literal7_tree);
            }
            // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:24:23: ( '@' id )?
            int alt3 = 2;
            final int LA3_0 = input.LA(1);
            if ((LA3_0 == 6)) {
               alt3 = 1;
            }
            switch (alt3) {
               case 1:
               // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:24:24: '@' id
               {
                  char_literal8 = (Token) match(input, 6, FOLLOW_6_in_dynamicsegment111);
                  if (state.failed) {
                     return retval;
                  }
                  if (state.backtracking == 0) {
                     char_literal8_tree = (CommonTree) adaptor.create(char_literal8);
                     adaptor.addChild(root_0, char_literal8_tree);
                  }
                  pushFollow(FOLLOW_id_in_dynamicsegment113);
                  id9 = id();
                  state._fsp--;
                  if (state.failed) {
                     return retval;
                  }
                  if (state.backtracking == 0) {
                     adaptor.addChild(root_0, id9.getTree());
                  }
               }
                  break;
            }
         }
         retval.stop = input.LT(-1);
         if (state.backtracking == 0) {
            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         }
      } catch (final RecognitionException re) {
         reportError(re);
         recover(input, re);
         retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);
      } finally {
         // do for sure before leaving
      }
      return retval;
   }

   // $ANTLR end "dynamicsegment"
   // $ANTLR start "expression"
   // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:25:1: expression : STRING ;
   public final RouteSpecificationParser.expression_return expression() throws RecognitionException {
      final RouteSpecificationParser.expression_return retval = new RouteSpecificationParser.expression_return();
      retval.start = input.LT(1);
      CommonTree root_0 = null;
      Token STRING10 = null;
      CommonTree STRING10_tree = null;
      try {
         // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:26:2: ( STRING )
         // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:26:4: STRING
         {
            root_0 = (CommonTree) adaptor.nil();
            STRING10 = (Token) match(input, STRING, FOLLOW_STRING_in_expression124);
            if (state.failed) {
               return retval;
            }
            if (state.backtracking == 0) {
               STRING10_tree = (CommonTree) adaptor.create(STRING10);
               adaptor.addChild(root_0, STRING10_tree);
            }
         }
         retval.stop = input.LT(-1);
         if (state.backtracking == 0) {
            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         }
      } catch (final RecognitionException re) {
         reportError(re);
         recover(input, re);
         retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);
      } finally {
         // do for sure before leaving
      }
      return retval;
   }

   // $ANTLR end "expression"
   // delegates
   public Parser[] getDelegates() {
      return new Parser[] {};
   }

   @Override
   public String getGrammarFileName() {
      return "com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g";
   }

   @Override
   public String[] getTokenNames() {
      return RouteSpecificationParser.tokenNames;
   }

   public TreeAdaptor getTreeAdaptor() {
      return adaptor;
   }

   // $ANTLR start "id"
   // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:27:1: id : STRING ;
   public final RouteSpecificationParser.id_return id() throws RecognitionException {
      final RouteSpecificationParser.id_return retval = new RouteSpecificationParser.id_return();
      retval.start = input.LT(1);
      CommonTree root_0 = null;
      Token STRING11 = null;
      CommonTree STRING11_tree = null;
      try {
         // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:28:2: ( STRING )
         // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:28:4: STRING
         {
            root_0 = (CommonTree) adaptor.nil();
            STRING11 = (Token) match(input, STRING, FOLLOW_STRING_in_id133);
            if (state.failed) {
               return retval;
            }
            if (state.backtracking == 0) {
               STRING11_tree = (CommonTree) adaptor.create(STRING11);
               adaptor.addChild(root_0, STRING11_tree);
            }
         }
         retval.stop = input.LT(-1);
         if (state.backtracking == 0) {
            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         }
      } catch (final RecognitionException re) {
         reportError(re);
         recover(input, re);
         retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);
      } finally {
         // do for sure before leaving
      }
      return retval;
   }

   // $ANTLR end "id"
   // $ANTLR start "routespecification"
   // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:18:1: routespecification : ( '/' ( staticsegment | dynamicsegment ) )+ ;
   public final RouteSpecificationParser.routespecification_return routespecification() throws RecognitionException {
      final RouteSpecificationParser.routespecification_return retval = new RouteSpecificationParser.routespecification_return();
      retval.start = input.LT(1);
      CommonTree root_0 = null;
      Token char_literal1 = null;
      ParserRuleReturnScope staticsegment2 = null;
      ParserRuleReturnScope dynamicsegment3 = null;
      CommonTree char_literal1_tree = null;
      try {
         // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:19:2: ( ( '/' ( staticsegment | dynamicsegment ) )+ )
         // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:19:4: ( '/' ( staticsegment | dynamicsegment ) )+
         {
            root_0 = (CommonTree) adaptor.nil();
            // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:19:4: ( '/' ( staticsegment | dynamicsegment ) )+
            int cnt2 = 0;
            loop2: while (true) {
               int alt2 = 2;
               final int LA2_0 = input.LA(1);
               if ((LA2_0 == 5)) {
                  alt2 = 1;
               }
               switch (alt2) {
                  case 1:
                  // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:19:5: '/' ( staticsegment | dynamicsegment )
                  {
                     char_literal1 = (Token) match(input, 5, FOLLOW_5_in_routespecification74);
                     if (state.failed) {
                        return retval;
                     }
                     if (state.backtracking == 0) {
                        char_literal1_tree = (CommonTree) adaptor.create(char_literal1);
                        adaptor.addChild(root_0, char_literal1_tree);
                     }
                     // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:19:9: ( staticsegment | dynamicsegment )
                     int alt1 = 2;
                     final int LA1_0 = input.LA(1);
                     if ((LA1_0 == STRING)) {
                        alt1 = 1;
                     } else if ((LA1_0 == 7)) {
                        alt1 = 2;
                     } else {
                        if (state.backtracking > 0) {
                           state.failed = true;
                           return retval;
                        }
                        final NoViableAltException nvae = new NoViableAltException("", 1, 0, input);
                        throw nvae;
                     }
                     switch (alt1) {
                        case 1:
                        // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:19:10: staticsegment
                        {
                           pushFollow(FOLLOW_staticsegment_in_routespecification77);
                           staticsegment2 = staticsegment();
                           state._fsp--;
                           if (state.failed) {
                              return retval;
                           }
                           if (state.backtracking == 0) {
                              adaptor.addChild(root_0, staticsegment2.getTree());
                           }
                        }
                           break;
                        case 2:
                        // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:19:26: dynamicsegment
                        {
                           pushFollow(FOLLOW_dynamicsegment_in_routespecification81);
                           dynamicsegment3 = dynamicsegment();
                           state._fsp--;
                           if (state.failed) {
                              return retval;
                           }
                           if (state.backtracking == 0) {
                              adaptor.addChild(root_0, dynamicsegment3.getTree());
                           }
                        }
                           break;
                     }
                  }
                     break;
                  default:
                     if (cnt2 >= 1) {
                        break loop2;
                     }
                     if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                     }
                     final EarlyExitException eee = new EarlyExitException(2, input);
                     throw eee;
               }
               cnt2++;
            }
         }
         retval.stop = input.LT(-1);
         if (state.backtracking == 0) {
            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         }
      } catch (final RecognitionException re) {
         reportError(re);
         recover(input, re);
         retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);
      } finally {
         // do for sure before leaving
      }
      return retval;
   }

   // $ANTLR end "routespecification"
   public void setTreeAdaptor(TreeAdaptor adaptor) {
      this.adaptor = adaptor;
   }

   // $ANTLR start "staticsegment"
   // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:21:1: staticsegment : STRING ;
   public final RouteSpecificationParser.staticsegment_return staticsegment() throws RecognitionException {
      final RouteSpecificationParser.staticsegment_return retval = new RouteSpecificationParser.staticsegment_return();
      retval.start = input.LT(1);
      CommonTree root_0 = null;
      Token STRING4 = null;
      CommonTree STRING4_tree = null;
      try {
         // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:22:2: ( STRING )
         // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:22:4: STRING
         {
            root_0 = (CommonTree) adaptor.nil();
            STRING4 = (Token) match(input, STRING, FOLLOW_STRING_in_staticsegment95);
            if (state.failed) {
               return retval;
            }
            if (state.backtracking == 0) {
               STRING4_tree = (CommonTree) adaptor.create(STRING4);
               adaptor.addChild(root_0, STRING4_tree);
            }
         }
         retval.stop = input.LT(-1);
         if (state.backtracking == 0) {
            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         }
      } catch (final RecognitionException re) {
         reportError(re);
         recover(input, re);
         retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);
      } finally {
         // do for sure before leaving
      }
      return retval;
   }
   // $ANTLR end "staticsegment"
}
