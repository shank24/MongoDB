package com.mongodb.driver.crud;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.operation.OrderBy;

import static com.mongodb.m101j.util.Helpers.printJson;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.*;

public class FindWithSortAndSkip {

	public static void main(String[] args) {

		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("course");
		MongoCollection<Document> col = db.getCollection("findWithSortTest");

		col.drop();

		// insert 100 documents with two random integers

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				col.insertOne(new Document().append("i", i).append("j", j));
			}
		}
		
		Bson projection = fields(include("i","j"),excludeId());
		
		Bson sort = new Document("i",1).append("j", -1);
		
		//Other way via Builder
		
		Bson sort1 = orderBy(ascending("i"),descending("j"));
		
		
		List<Document> all = col.find()
								.projection(projection)
								.sort(sort1)
								.skip(10)
								.limit(10)
								.into(new ArrayList<Document>());
		
		for (Document document : all) {
			printJson(document);
		}
	}

}
