package com.mongodb.java.M101J;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import spark.http.matching.Halt;

public class HelloWorldSparkFreeStyleTogether {

	public static void main(String[] args) {

		@SuppressWarnings("deprecation")
		final Configuration conf = new Configuration();
		conf.setClassForTemplateLoading(HelloWorldSparkFreeStyleTogether.class, "/");

		Spark.get("/", new Route() {
			public Object handle(final Request request, final Response response) {

				StringWriter writer = new StringWriter();

				try {
					Template helloTemp = conf.getTemplate("resources/hello.ftl");

					Map<String, Object> helloMap = new HashMap<String, Object>();
					helloMap.put("name", "TTN");

					helloTemp.process(helloMap, writer);
					System.out.println(writer);
				} catch (Exception e) {

					e.printStackTrace();
				}
				return writer;
			}
		});
	}

}
