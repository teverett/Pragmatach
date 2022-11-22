package com.khubla.pragmatach.contrib.clientproxygenerator;

import java.io.*;
import java.util.*;

/**
 * @author tome
 */
public class ClientGenerator {
	/**
	 * target dir
	 */
	private final String targetDir;

	/**
	 * ctor
	 */
	public ClientGenerator(String targetDir) {
		this.targetDir = targetDir;
	}

	/**
	 * generate a file
	 */
	public void generate(RouteUrl routeUrl, String namespace) throws Exception {
		OutputStreamWriter osw = null;
		FileOutputStream fos = null;
		try {
			/*
			 * name
			 */
			String name = routeUrl.getParts().get(routeUrl.getParts().size() - 1);
			/*
			 * stream
			 */
			String path = System.getProperty("user.dir") + "/" + targetDir;
			new File(path).mkdirs();
			fos = new FileOutputStream(path + "/" + name + ".java", false);
			/*
			 * writer
			 */
			osw = new OutputStreamWriter(fos, "UTF-8");
			/*
			 * package
			 */
			osw.write("package " + namespace + ";\n");
			/*
			 * HTTP Components
			 */
			osw.write("import org.apache.http.client.methods.HttpGet;\n");
			osw.write("import org.apache.http.impl.client.DefaultHttpClient;\n");
			/*
			 * class
			 */
			osw.write("public class " + name + " {\n");
			/*
			 * method
			 */
			osw.write("public void invoke() {\n");
			osw.write("DefaultHttpClient client = new DefaultHttpClient();\n");
			String url = generateUrl(routeUrl);
			osw.write("}\n");
			/*
			 * end of class
			 */
			osw.write("}\n");
		} catch (final Exception e) {
			throw new Exception("Exception in generate: '" + routeUrl.getContext() + "'", e);
		} finally {
			/*
			 * done
			 */
			if (null != osw) {
				osw.close();
			}
			if (null != fos) {
				fos.flush();
				fos.close();
			}
		}
	}

	public String getTargetDir() {
		return targetDir;
	}

	private String generateUrl(RouteUrl routeUrl) {
		String ret = "";
		final List<String> parts = routeUrl.getParts();
		for (final String part : parts) {
			ret += "/" + part;
		}
		return ret;
	}
}
