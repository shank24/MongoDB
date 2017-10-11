package com.mongodb.driver.crud;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.m101j.util.Helpers.printJson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FindTest {

	public static void main(String[] args) {

		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("course");
		MongoCollection<Document> col = db.getCollection("findTest");

		col.drop();

		for (int i = 0; i < 10; i++) {
			col.insertOne(new Document("x", i));
		}

		System.out.println("Find One :");
		Document first = col.find().first();
		printJson(first);

		System.out.println("Find all with into");
		List<Document> all = col.find().into(new ArrayList<Document>());

		for (Document cur : all) {
			printJson(cur);
		}

		System.out.println("Find all with Iteration :");
		MongoCursor<Document> cursor = col.find().iterator();
		try {
			while (cursor.hasNext()) {
				Document cur = cursor.next();
				printJson(cur);
			}
		} finally {
			cursor.close();
		}
		
		System.out.println("Count: ");
		long count = col.count();
		System.out.println(count);
		
	}

}
