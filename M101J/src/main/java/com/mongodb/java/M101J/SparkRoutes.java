package com.mongodb.java.M101J;

import spark.Route;
import spark.Spark;
import spark.Request;
import spark.Response;

public class SparkRoutes {

	public static void main(String[] args) {

		Spark.get("/", new Route() {
			public Object handle(final Request request, final Response response) {
				return "Hello World from Spark\n";
			}
		});

		Spark.get("/test", new Route() {
			public Object handle(final Request request, final Response response) {
				return "This is a Test";
			}
		});

		Spark.get("/echo/:thing", new Route() {
			public Object handle(final Request request, final Response response) {
				return request.params(":thing");
			}
		});
	}

}
