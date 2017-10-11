package com.mongodb.driver.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;

import static com.mongodb.m101j.util.Helpers.printJson;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

public class FindWithFilterTest {

	public static void main(String[] args) {

		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("course");
		MongoCollection<Document> col = db.getCollection("findWithFilterTest");

		col.drop();

		// Insert 10 documents

		for (int i = 0; i < 10; i++) {
			col.insertOne(new Document().append("x", new Random().nextInt(2)).append("y", new Random().nextInt(100))
					.append("i", i));

			// Bson filter = new Document("x", 0).append("y", new
			// Document("$gt", 10).append("$lt", 90));

			Bson filter = and(eq("x", 0), gt("y", 10), lt("y", 90));

			// This means excluding the x
			// Here specifying the 0 means exclude, if we change it to 1, then
			// it will include
			// Bson projection = new Document("y",1).append("i",
			// 1).append("_id", 0);

			// Other way around
			// It is for include
			Bson projection = include("x", "_id");

			// It is for exclude
			Bson projection1 = exclude("x", "_id");

			// One in all
			Bson projection2 = fields(include("y", "i"), exclude("_id"));

			// Specific Method for ID
			Bson projection3 = fields(Projections.include("y", "i"), excludeId());

			List<Document> all = col.find(filter).projection(projection3).into(new ArrayList<Document>());

			for (Document document : all) {
				printJson(document);
			}

			long count = col.count(filter);
			System.out.println();
			// System.out.println(" Counter IS " + count);
		}
	}

}
