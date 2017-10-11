package com.mongodb.java.M101J;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HelloWorldMongoDBSparkFreemarkerStyle {

	public static void main(String[] args) {

		@SuppressWarnings("deprecation")
		final Configuration conf = new Configuration();
		conf.setClassForTemplateLoading(HelloWorldMongoDBSparkFreemarkerStyle.class, "/");

		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("course");
		final MongoCollection<Document> col = db.getCollection("hello");

		col.drop();

		col.insertOne(new Document("name", "MongoDB"));

		Spark.get("/", new Route() {
			public Object handle(final Request request, final Response response) {

				StringWriter writer = new StringWriter();

				try {
					Template helloTemp = conf.getTemplate("resources/hello.ftl");

					Document doc = col.find().first();

					helloTemp.process(doc, writer);
					System.out.println(writer);
				} catch (Exception e) {

					e.printStackTrace();
				}
				return writer;
			}
		});
	}

}
