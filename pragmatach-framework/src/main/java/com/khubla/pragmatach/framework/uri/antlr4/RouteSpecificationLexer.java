// Generated from com/khubla/pragmatach/framework/uri/antlr4/RouteSpecification.g4 by ANTLR 4.3
package com.khubla.pragmatach.framework.uri.antlr4;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({ "all", "warnings", "unchecked", "unused", "cast" })
public class RouteSpecificationLexer extends Lexer {
   static {
      RuntimeMetaData.checkVersion("4.3", RuntimeMetaData.VERSION);
   }
   protected static final DFA[] _decisionToDFA;
   protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();
   public static final int T__0 = 1, ALPHANUM = 2, AMPER = 3;
   public static String[] modeNames = { "DEFAULT_MODE" };
   public static final String[] tokenNames = { "'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'" };
   public static final String[] ruleNames = { "T__0", "ALPHANUM", "AMPER" };
   public static final String _serializedATN = "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\5\22\b\1\4\2\t\2" + "\4\3\t\3\4\4\t\4\3\2\3\2\3\3\6\3\r\n\3\r\3\16\3\16\3\4\3\4\2\2\5\3\3\5"
         + "\4\7\5\3\2\3\6\2,,\62;C\\c|\22\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\3\t" + "\3\2\2\2\5\f\3\2\2\2\7\20\3\2\2\2\t\n\7\61\2\2\n\4\3\2\2\2\13\r\t\2\2"
         + "\2\f\13\3\2\2\2\r\16\3\2\2\2\16\f\3\2\2\2\16\17\3\2\2\2\17\6\3\2\2\2\20" + "\21\7B\2\2\21\b\3\2\2\2\4\2\16\2";
   public static final ATN _ATN = new ATNDeserializer().deserialize(_serializedATN.toCharArray());
   static {
      _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
      for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
         _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
      }
   }

   public RouteSpecificationLexer(CharStream input) {
      super(input);
      _interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
   }

   @Override
   public ATN getATN() {
      return _ATN;
   }

   @Override
   public String getGrammarFileName() {
      return "RouteSpecification.g4";
   }

   @Override
   public String[] getModeNames() {
      return modeNames;
   }

   @Override
   public String[] getRuleNames() {
      return ruleNames;
   }

   @Override
   public String getSerializedATN() {
      return _serializedATN;
   }

   @Override
   public String[] getTokenNames() {
      return tokenNames;
   }
}