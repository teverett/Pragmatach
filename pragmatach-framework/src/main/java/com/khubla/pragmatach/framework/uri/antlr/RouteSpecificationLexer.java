// $ANTLR 3.5 com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g 2013-02-21 15:59:53
package com.khubla.pragmatach.framework.uri.antlr;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;

@SuppressWarnings("all")
public class RouteSpecificationLexer extends Lexer {
   public static final int EOF = -1;
   public static final int T__5 = 5;
   public static final int T__6 = 6;
   public static final int ALPHANUM = 4;

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

   // $ANTLR start "ALPHANUM"
   public final void mALPHANUM() throws RecognitionException {
      try {
         final int _type = ALPHANUM;
         final int _channel = DEFAULT_TOKEN_CHANNEL;
         // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:29:6: ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )+ )
         // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:29:8: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )+
         {
            // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:29:8: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )+
            int cnt1 = 0;
            loop1: while (true) {
               int alt1 = 2;
               final int LA1_0 = input.LA(1);
               if ((((LA1_0 >= '0') && (LA1_0 <= '9')) || ((LA1_0 >= 'A') && (LA1_0 <= 'Z')) || ((LA1_0 >= 'a') && (LA1_0 <= 'z')))) {
                  alt1 = 1;
               }
               switch (alt1) {
                  case 1:
                  // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:
                  {
                     if (((input.LA(1) >= '0') && (input.LA(1) <= '9')) || ((input.LA(1) >= 'A') && (input.LA(1) <= 'Z')) || ((input.LA(1) >= 'a') && (input.LA(1) <= 'z'))) {
                        input.consume();
                     } else {
                        final MismatchedSetException mse = new MismatchedSetException(null, input);
                        recover(mse);
                        throw mse;
                     }
                  }
                     break;
                  default:
                     if (cnt1 >= 1) {
                        break loop1;
                     }
                     final EarlyExitException eee = new EarlyExitException(1, input);
                     throw eee;
               }
               cnt1++;
            }
         }
         state.type = _type;
         state.channel = _channel;
      } finally {
         // do for sure before leaving
      }
   }

   // $ANTLR end "ALPHANUM"
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
   @Override
   public void mTokens() throws RecognitionException {
      // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:1:8: ( T__5 | T__6 | ALPHANUM )
      int alt2 = 3;
      switch (input.LA(1)) {
         case '/': {
            alt2 = 1;
         }
            break;
         case '@': {
            alt2 = 2;
         }
            break;
         case '0':
         case '1':
         case '2':
         case '3':
         case '4':
         case '5':
         case '6':
         case '7':
         case '8':
         case '9':
         case 'A':
         case 'B':
         case 'C':
         case 'D':
         case 'E':
         case 'F':
         case 'G':
         case 'H':
         case 'I':
         case 'J':
         case 'K':
         case 'L':
         case 'M':
         case 'N':
         case 'O':
         case 'P':
         case 'Q':
         case 'R':
         case 'S':
         case 'T':
         case 'U':
         case 'V':
         case 'W':
         case 'X':
         case 'Y':
         case 'Z':
         case 'a':
         case 'b':
         case 'c':
         case 'd':
         case 'e':
         case 'f':
         case 'g':
         case 'h':
         case 'i':
         case 'j':
         case 'k':
         case 'l':
         case 'm':
         case 'n':
         case 'o':
         case 'p':
         case 'q':
         case 'r':
         case 's':
         case 't':
         case 'u':
         case 'v':
         case 'w':
         case 'x':
         case 'y':
         case 'z': {
            alt2 = 3;
         }
            break;
         default:
            final NoViableAltException nvae = new NoViableAltException("", 2, 0, input);
            throw nvae;
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
         // com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:1:20: ALPHANUM
         {
            mALPHANUM();
         }
            break;
      }
   }
}
