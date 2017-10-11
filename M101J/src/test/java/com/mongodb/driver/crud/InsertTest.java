package com.mongodb.driver.crud;

import org.bson.Document;

import java.util.Arrays;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.m101j.util.Helpers.printJson;

public class InsertTest {

	public static void main(String[] args) {

		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("course");
		MongoCollection<Document> col = db.getCollection("insertTest");

		col.drop();

		Document smith = new Document("name", "Smith").append("age", 30).append("profession", "programmer");

		Document jones = new Document("name", "Jones").append("age", 33).append("profession", "chef");

		printJson(smith);

		col.insertOne(smith);
		
		
		//col.insertMany(asList(smith,jones));
		printJson(smith);

	}

}
