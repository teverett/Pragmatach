// $ANTLR 3.5 com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g 2013-02-26 20:03:40

    package com.khubla.pragmatach.framework.uri.antlr;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;


@SuppressWarnings("all")
public class RouteSpecificationParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ALPHANUM", "AMPER", "'/'"
	};
	public static final int EOF=-1;
	public static final int T__6=6;
	public static final int ALPHANUM=4;
	public static final int AMPER=5;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public RouteSpecificationParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public RouteSpecificationParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor) {
		this.adaptor = adaptor;
	}
	public TreeAdaptor getTreeAdaptor() {
		return adaptor;
	}
	@Override public String[] getTokenNames() { return RouteSpecificationParser.tokenNames; }
	@Override public String getGrammarFileName() { return "com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g"; }


	public static class routespecification_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "routespecification"
	// com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:18:1: routespecification : ( '/' ! ( segment )? )+ ;
	public final RouteSpecificationParser.routespecification_return routespecification() throws RecognitionException {
		RouteSpecificationParser.routespecification_return retval = new RouteSpecificationParser.routespecification_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token char_literal1=null;
		ParserRuleReturnScope segment2 =null;

		CommonTree char_literal1_tree=null;

		try {
			// com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:19:2: ( ( '/' ! ( segment )? )+ )
			// com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:19:4: ( '/' ! ( segment )? )+
			{
			root_0 = (CommonTree)adaptor.nil();


			// com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:19:4: ( '/' ! ( segment )? )+
			int cnt2=0;
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==6) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:19:5: '/' ! ( segment )?
					{
					char_literal1=(Token)match(input,6,FOLLOW_6_in_routespecification74); if (state.failed) return retval;
					// com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:19:10: ( segment )?
					int alt1=2;
					int LA1_0 = input.LA(1);
					if ( ((LA1_0 >= ALPHANUM && LA1_0 <= AMPER)) ) {
						alt1=1;
					}
					switch (alt1) {
						case 1 :
							// com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:19:10: segment
							{
							pushFollow(FOLLOW_segment_in_routespecification77);
							segment2=segment();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, segment2.getTree());

							}
							break;

					}

					}
					break;

				default :
					if ( cnt2 >= 1 ) break loop2;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(2, input);
					throw eee;
				}
				cnt2++;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "routespecification"


	public static class segment_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "segment"
	// com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:20:1: segment : ( pathsegment | idsegment );
	public final RouteSpecificationParser.segment_return segment() throws RecognitionException {
		RouteSpecificationParser.segment_return retval = new RouteSpecificationParser.segment_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope pathsegment3 =null;
		ParserRuleReturnScope idsegment4 =null;


		try {
			// com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:21:2: ( pathsegment | idsegment )
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0==ALPHANUM) ) {
				alt3=1;
			}
			else if ( (LA3_0==AMPER) ) {
				alt3=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 3, 0, input);
				throw nvae;
			}

			switch (alt3) {
				case 1 :
					// com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:21:4: pathsegment
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_pathsegment_in_segment89);
					pathsegment3=pathsegment();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, pathsegment3.getTree());

					}
					break;
				case 2 :
					// com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:21:18: idsegment
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_idsegment_in_segment93);
					idsegment4=idsegment();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, idsegment4.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "segment"


	public static class idsegment_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "idsegment"
	// com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:22:1: idsegment : AMPER ^ id ;
	public final RouteSpecificationParser.idsegment_return idsegment() throws RecognitionException {
		RouteSpecificationParser.idsegment_return retval = new RouteSpecificationParser.idsegment_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token AMPER5=null;
		ParserRuleReturnScope id6 =null;

		CommonTree AMPER5_tree=null;

		try {
			// com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:23:2: ( AMPER ^ id )
			// com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:23:4: AMPER ^ id
			{
			root_0 = (CommonTree)adaptor.nil();


			AMPER5=(Token)match(input,AMPER,FOLLOW_AMPER_in_idsegment102); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			AMPER5_tree = (CommonTree)adaptor.create(AMPER5);
			root_0 = (CommonTree)adaptor.becomeRoot(AMPER5_tree, root_0);
			}

			pushFollow(FOLLOW_id_in_idsegment106);
			id6=id();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, id6.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "idsegment"


	public static class pathsegment_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "pathsegment"
	// com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:24:1: pathsegment : ALPHANUM ^;
	public final RouteSpecificationParser.pathsegment_return pathsegment() throws RecognitionException {
		RouteSpecificationParser.pathsegment_return retval = new RouteSpecificationParser.pathsegment_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token ALPHANUM7=null;

		CommonTree ALPHANUM7_tree=null;

		try {
			// com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:25:2: ( ALPHANUM ^)
			// com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:25:4: ALPHANUM ^
			{
			root_0 = (CommonTree)adaptor.nil();


			ALPHANUM7=(Token)match(input,ALPHANUM,FOLLOW_ALPHANUM_in_pathsegment115); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ALPHANUM7_tree = (CommonTree)adaptor.create(ALPHANUM7);
			root_0 = (CommonTree)adaptor.becomeRoot(ALPHANUM7_tree, root_0);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "pathsegment"


	public static class id_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "id"
	// com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:26:1: id : ALPHANUM ;
	public final RouteSpecificationParser.id_return id() throws RecognitionException {
		RouteSpecificationParser.id_return retval = new RouteSpecificationParser.id_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token ALPHANUM8=null;

		CommonTree ALPHANUM8_tree=null;

		try {
			// com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:27:2: ( ALPHANUM )
			// com/khubla/pragmatach/framework/uri/antlr/RouteSpecification.g:27:4: ALPHANUM
			{
			root_0 = (CommonTree)adaptor.nil();


			ALPHANUM8=(Token)match(input,ALPHANUM,FOLLOW_ALPHANUM_in_id125); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ALPHANUM8_tree = (CommonTree)adaptor.create(ALPHANUM8);
			adaptor.addChild(root_0, ALPHANUM8_tree);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "id"

	// Delegated rules



	public static final BitSet FOLLOW_6_in_routespecification74 = new BitSet(new long[]{0x0000000000000072L});
	public static final BitSet FOLLOW_segment_in_routespecification77 = new BitSet(new long[]{0x0000000000000042L});
	public static final BitSet FOLLOW_pathsegment_in_segment89 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_idsegment_in_segment93 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_AMPER_in_idsegment102 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_id_in_idsegment106 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ALPHANUM_in_pathsegment115 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ALPHANUM_in_id125 = new BitSet(new long[]{0x0000000000000002L});
}
