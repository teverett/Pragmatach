package com.khubla.pragmatach.framework.url;

import java.io.*;
import java.util.*;

import org.antlr.v4.runtime.*;

import com.khubla.pragmatach.framework.api.*;
import com.khubla.pragmatach.framework.uri.antlr4.*;
import com.khubla.pragmatach.framework.uri.antlr4.RouteSpecificationParser.*;

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
		String fixedUri = uri;
		if (!uri.startsWith("/")) {
			fixedUri = "/" + uri;
		}
		segments = parse(fixedUri);
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
	private RoutespecificationContext parse(InputStream inputStream) throws PragmatachException {
		try {
			if (null != inputStream) {
				final RouteSpecificationLexer routeSpecificationLexer = new RouteSpecificationLexer(new ANTLRInputStream(inputStream));
				final CommonTokenStream tokens = new CommonTokenStream(routeSpecificationLexer);
				final RouteSpecificationParser routeSpecificationParser = new RouteSpecificationParser(tokens);
				return routeSpecificationParser.routespecification();
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
			/*
			 * special case for "/"
			 */
			if (uri.compareTo("/") == 0) {
				final RouteSpecificationSegment routeSpecificationSegment = new RouteSpecificationSegment("/", null);
				ret.add(routeSpecificationSegment);
			} else {
				final RoutespecificationContext routespecificationContext = parse(new ByteArrayInputStream(uri.getBytes("UTF-8")));
				for (final SegmentContext segmentContext : routespecificationContext.segment()) {
					final RouteSpecificationSegment routeSpecificationSegment = parseSegment(segmentContext);
					ret.add(routeSpecificationSegment);
				}
			}
			return ret;
		} catch (final Exception e) {
			throw new PragmatachException("Exception in parse", e);
		}
	}

	private RouteSpecificationSegment parseSegment(SegmentContext segmentContext) throws PragmatachException {
		try {
			final PathsegmentContext pathsegmentContext = segmentContext.getChild(PathsegmentContext.class, 0);
			if (null != pathsegmentContext) {
				return new RouteSpecificationSegment(pathsegmentContext.getText(), null);
			} else {
				final IdsegmentContext idsegmentContext = segmentContext.getChild(IdsegmentContext.class, 0);
				if (null != idsegmentContext) {
					final String id = idsegmentContext.getChild(1).getText();
					return new RouteSpecificationSegment(null, id);
				}
			}
			return null;
		} catch (final Exception e) {
			throw new PragmatachException("Exception in parseNode", e);
		}
	}
}
