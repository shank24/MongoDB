package com.mongodb.java.M101J;


import spark.Route;
import spark.Spark;
import spark.Request;
import spark.Response;


public class HelloWorldSparkStyle {

	public static void main(String[] args) {

		   Spark.get("/", new Route() {
               public Object handle(final Request request, final Response response){
               return "Hello World from Spark";
           }
       });
	}

}
