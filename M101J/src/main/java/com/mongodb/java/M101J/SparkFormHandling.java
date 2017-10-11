package com.mongodb.java.M101J;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Route;
import spark.Spark;
import spark.Request;
import spark.Response;

public class SparkFormHandling {

	public static void main(String[] args) {

		// Configure Free Marker
		final Configuration conf = new Configuration();
		conf.setClassForTemplateLoading(SparkFormHandling.class, "/");

		// Configure Routes
		Spark.get("/", new Route() {
			public Object handle(final Request request, final Response response) {

				try {
					Map<String, Object> fruitsMap = new HashMap<String, Object>();
					fruitsMap.put("fruits", Arrays.asList("apple", "orange", "banana", "peach"));

					Template fruitPickerTemplate = conf.getTemplate("resources/fruit.ftl");
					StringWriter writer = new StringWriter();
					fruitPickerTemplate.process(fruitsMap, writer);
					System.out.println(writer);
					return writer;
				} catch (Exception e) {

					e.printStackTrace();
					return null;
				}

			}
		});
		
		Spark.post("/favourite_fruit", new Route(){
			
			public Object handle(final Request request, final Response response) {

				final String fruit = request.queryParams("fruit");
				if(fruit==null){
					return "Don't you like Fruits";
				}
				else{
					return "Your favourite fruit is " + fruit;
				}
			}
			
		});
		
	}

}
