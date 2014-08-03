// Generated from com/khubla/pragmatach/framework/uri/antlr4/RouteSpecification.g4 by ANTLR 4.3
package com.khubla.pragmatach.framework.uri.antlr4;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RouteSpecificationParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, ALPHANUM=2, AMPER=3;
	public static final String[] tokenNames = {
		"<INVALID>", "'/'", "ALPHANUM", "'@'"
	};
	public static final int
		RULE_routespecification = 0, RULE_segment = 1, RULE_idsegment = 2, RULE_pathsegment = 3, 
		RULE_id = 4;
	public static final String[] ruleNames = {
		"routespecification", "segment", "idsegment", "pathsegment", "id"
	};

	@Override
	public String getGrammarFileName() { return "RouteSpecification.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RouteSpecificationParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RoutespecificationContext extends ParserRuleContext {
		public List<SegmentContext> segment() {
			return getRuleContexts(SegmentContext.class);
		}
		public SegmentContext segment(int i) {
			return getRuleContext(SegmentContext.class,i);
		}
		public RoutespecificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_routespecification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteSpecificationListener ) ((RouteSpecificationListener)listener).enterRoutespecification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteSpecificationListener ) ((RouteSpecificationListener)listener).exitRoutespecification(this);
		}
	}

	public final RoutespecificationContext routespecification() throws RecognitionException {
		RoutespecificationContext _localctx = new RoutespecificationContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_routespecification);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(14); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(10); match(T__0);
				setState(12);
				_la = _input.LA(1);
				if (_la==ALPHANUM || _la==AMPER) {
					{
					setState(11); segment();
					}
				}

				}
				}
				setState(16); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SegmentContext extends ParserRuleContext {
		public PathsegmentContext pathsegment() {
			return getRuleContext(PathsegmentContext.class,0);
		}
		public IdsegmentContext idsegment() {
			return getRuleContext(IdsegmentContext.class,0);
		}
		public SegmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_segment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteSpecificationListener ) ((RouteSpecificationListener)listener).enterSegment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteSpecificationListener ) ((RouteSpecificationListener)listener).exitSegment(this);
		}
	}

	public final SegmentContext segment() throws RecognitionException {
		SegmentContext _localctx = new SegmentContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_segment);
		try {
			setState(20);
			switch (_input.LA(1)) {
			case ALPHANUM:
				enterOuterAlt(_localctx, 1);
				{
				setState(18); pathsegment();
				}
				break;
			case AMPER:
				enterOuterAlt(_localctx, 2);
				{
				setState(19); idsegment();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdsegmentContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode AMPER() { return getToken(RouteSpecificationParser.AMPER, 0); }
		public IdsegmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idsegment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteSpecificationListener ) ((RouteSpecificationListener)listener).enterIdsegment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteSpecificationListener ) ((RouteSpecificationListener)listener).exitIdsegment(this);
		}
	}

	public final IdsegmentContext idsegment() throws RecognitionException {
		IdsegmentContext _localctx = new IdsegmentContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_idsegment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22); match(AMPER);
			setState(23); id();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PathsegmentContext extends ParserRuleContext {
		public TerminalNode ALPHANUM() { return getToken(RouteSpecificationParser.ALPHANUM, 0); }
		public PathsegmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pathsegment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteSpecificationListener ) ((RouteSpecificationListener)listener).enterPathsegment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteSpecificationListener ) ((RouteSpecificationListener)listener).exitPathsegment(this);
		}
	}

	public final PathsegmentContext pathsegment() throws RecognitionException {
		PathsegmentContext _localctx = new PathsegmentContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_pathsegment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25); match(ALPHANUM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdContext extends ParserRuleContext {
		public TerminalNode ALPHANUM() { return getToken(RouteSpecificationParser.ALPHANUM, 0); }
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteSpecificationListener ) ((RouteSpecificationListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteSpecificationListener ) ((RouteSpecificationListener)listener).exitId(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27); match(ALPHANUM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\5 \4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\5\2\17\n\2\6\2\21\n\2\r\2\16\2\22\3"+
		"\3\3\3\5\3\27\n\3\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\6\2\2\7\2\4\6\b\n\2\2"+
		"\35\2\20\3\2\2\2\4\26\3\2\2\2\6\30\3\2\2\2\b\33\3\2\2\2\n\35\3\2\2\2\f"+
		"\16\7\3\2\2\r\17\5\4\3\2\16\r\3\2\2\2\16\17\3\2\2\2\17\21\3\2\2\2\20\f"+
		"\3\2\2\2\21\22\3\2\2\2\22\20\3\2\2\2\22\23\3\2\2\2\23\3\3\2\2\2\24\27"+
		"\5\b\5\2\25\27\5\6\4\2\26\24\3\2\2\2\26\25\3\2\2\2\27\5\3\2\2\2\30\31"+
		"\7\5\2\2\31\32\5\n\6\2\32\7\3\2\2\2\33\34\7\4\2\2\34\t\3\2\2\2\35\36\7"+
		"\4\2\2\36\13\3\2\2\2\5\16\22\26";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}