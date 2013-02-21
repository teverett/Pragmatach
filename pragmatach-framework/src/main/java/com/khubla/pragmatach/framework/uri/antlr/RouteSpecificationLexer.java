// $ANTLR 3.5 com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g 2013-02-20 19:30:22
package com.khubla.pragmatach.framework.uri.antlr;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;

@SuppressWarnings("all")
public class RouteSpecificationLexer extends Lexer {
   public static final int EOF = -1;
   public static final int T__5 = 5;
   public static final int T__6 = 6;
   public static final int T__7 = 7;
   public static final int T__8 = 8;
   public static final int STRING = 4;

   public RouteSpecificationLexer() {
   }

   public RouteSpecificationLexer(CharStream input) {
      this(input, new RecognizerSharedState());
   }

   public RouteSpecificationLexer(CharStream input, RecognizerSharedState state) {
      super(input, state);
   }

   // delegates
   // delegators
   public Lexer[] getDelegates() {
      return new Lexer[] {};
   }

   @Override
   public String getGrammarFileName() {
      return "com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g";
   }

   // $ANTLR start "STRING"
   public final void mSTRING() throws RecognitionException {
      try {
         final int _type = STRING;
         final int _channel = DEFAULT_TOKEN_CHANNEL;
         // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:30:6: ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' )* )
         // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:31:6: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' )*
         {
            // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:31:6: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' )*
            loop1: while (true) {
               int alt1 = 2;
               final int LA1_0 = input.LA(1);
               if (((LA1_0 == '-') || ((LA1_0 >= '0') && (LA1_0 <= '9')) || ((LA1_0 >= 'A') && (LA1_0 <= 'Z')) || ((LA1_0 >= 'a') && (LA1_0 <= 'z')))) {
                  alt1 = 1;
               }
               switch (alt1) {
                  case 1:
                  // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:
                  {
                     if ((input.LA(1) == '-') || ((input.LA(1) >= '0') && (input.LA(1) <= '9')) || ((input.LA(1) >= 'A') && (input.LA(1) <= 'Z')) || ((input.LA(1) >= 'a') && (input.LA(1) <= 'z'))) {
                        input.consume();
                     } else {
                        final MismatchedSetException mse = new MismatchedSetException(null, input);
                        recover(mse);
                        throw mse;
                     }
                  }
                     break;
                  default:
                     break loop1;
               }
            }
         }
         state.type = _type;
         state.channel = _channel;
      } finally {
         // do for sure before leaving
      }
   }

   // $ANTLR end "STRING"
   // $ANTLR start "T__5"
   public final void mT__5() throws RecognitionException {
      try {
         final int _type = T__5;
         final int _channel = DEFAULT_TOKEN_CHANNEL;
         // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:6:6: ( '/' )
         // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:6:8: '/'
         {
            match('/');
         }
         state.type = _type;
         state.channel = _channel;
      } finally {
         // do for sure before leaving
      }
   }

   // $ANTLR end "T__5"
   // $ANTLR start "T__6"
   public final void mT__6() throws RecognitionException {
      try {
         final int _type = T__6;
         final int _channel = DEFAULT_TOKEN_CHANNEL;
         // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:7:6: ( '@' )
         // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:7:8: '@'
         {
            match('@');
         }
         state.type = _type;
         state.channel = _channel;
      } finally {
         // do for sure before leaving
      }
   }

   // $ANTLR end "T__6"
   // $ANTLR start "T__7"
   public final void mT__7() throws RecognitionException {
      try {
         final int _type = T__7;
         final int _channel = DEFAULT_TOKEN_CHANNEL;
         // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:8:6: ( '{' )
         // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:8:8: '{'
         {
            match('{');
         }
         state.type = _type;
         state.channel = _channel;
      } finally {
         // do for sure before leaving
      }
   }

   // $ANTLR end "T__7"
   // $ANTLR start "T__8"
   public final void mT__8() throws RecognitionException {
      try {
         final int _type = T__8;
         final int _channel = DEFAULT_TOKEN_CHANNEL;
         // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:9:6: ( '}' )
         // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:9:8: '}'
         {
            match('}');
         }
         state.type = _type;
         state.channel = _channel;
      } finally {
         // do for sure before leaving
      }
   }

   // $ANTLR end "T__8"
   @Override
   public void mTokens() throws RecognitionException {
      // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:1:8: ( T__5 | T__6 | T__7 | T__8 | STRING )
      int alt2 = 5;
      switch (input.LA(1)) {
         case '/': {
            alt2 = 1;
         }
            break;
         case '@': {
            alt2 = 2;
         }
            break;
         case '{': {
            alt2 = 3;
         }
            break;
         case '}': {
            alt2 = 4;
         }
            break;
         default:
            alt2 = 5;
      }
      switch (alt2) {
         case 1:
         // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:1:10: T__5
         {
            mT__5();
         }
            break;
         case 2:
         // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:1:15: T__6
         {
            mT__6();
         }
            break;
         case 3:
         // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:1:20: T__7
         {
            mT__7();
         }
            break;
         case 4:
         // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:1:25: T__8
         {
            mT__8();
         }
            break;
         case 5:
         // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:1:30: STRING
         {
            mSTRING();
         }
            break;
      }
   }
}
