// Generated from com/khubla/pragmatach/framework/uri/antlr4/RouteSpecification.g4 by ANTLR 4.3
package com.khubla.pragmatach.framework.uri.antlr4;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RouteSpecificationParser}.
 */
public interface RouteSpecificationListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RouteSpecificationParser#idsegment}.
	 * @param ctx the parse tree
	 */
	void enterIdsegment(@NotNull RouteSpecificationParser.IdsegmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteSpecificationParser#idsegment}.
	 * @param ctx the parse tree
	 */
	void exitIdsegment(@NotNull RouteSpecificationParser.IdsegmentContext ctx);

	/**
	 * Enter a parse tree produced by {@link RouteSpecificationParser#routespecification}.
	 * @param ctx the parse tree
	 */
	void enterRoutespecification(@NotNull RouteSpecificationParser.RoutespecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteSpecificationParser#routespecification}.
	 * @param ctx the parse tree
	 */
	void exitRoutespecification(@NotNull RouteSpecificationParser.RoutespecificationContext ctx);

	/**
	 * Enter a parse tree produced by {@link RouteSpecificationParser#segment}.
	 * @param ctx the parse tree
	 */
	void enterSegment(@NotNull RouteSpecificationParser.SegmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteSpecificationParser#segment}.
	 * @param ctx the parse tree
	 */
	void exitSegment(@NotNull RouteSpecificationParser.SegmentContext ctx);

	/**
	 * Enter a parse tree produced by {@link RouteSpecificationParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(@NotNull RouteSpecificationParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteSpecificationParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(@NotNull RouteSpecificationParser.IdContext ctx);

	/**
	 * Enter a parse tree produced by {@link RouteSpecificationParser#pathsegment}.
	 * @param ctx the parse tree
	 */
	void enterPathsegment(@NotNull RouteSpecificationParser.PathsegmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteSpecificationParser#pathsegment}.
	 * @param ctx the parse tree
	 */
	void exitPathsegment(@NotNull RouteSpecificationParser.PathsegmentContext ctx);
}