package com.mongodb.driver.crud;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.print.Doc;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.operation.OrderBy;

import static com.mongodb.m101j.util.Helpers.printJson;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.*;
import static com.mongodb.client.model.Updates.*;

public class UpdateTest {

	public static void main(String[] args) {

		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("course");
		MongoCollection<Document> col = db.getCollection("findWithSortTest");

		col.drop();

		// Insert 8 doc, with both _id and x set to the value of the loop
		// variable
		// and y set to true

		for (int i = 0; i < 8; i++) {
			col.insertOne(new Document().append("_id", i).append("x", i).append("y", true));
		}

		// col.replaceOne(eq("x",5), new Document("x",20).append("updated",
		// true));

		// $set will add new variable , it only change the value of existing
		// variable
		// col.updateOne(eq("x", 5), new Document("$set", new Document("x",
		// 20).append("upd", true)));

		// With Builder Class , if the _id is not created like in this _id with 8 is not created, but with upserts it will create
		//col.updateOne(eq("_id", 8), combine(set("x", 888), set("updates", true)), new UpdateOptions().upsert(true));

		//Multiple updates at once
		col.updateMany(gte("x",5), inc("x",1));
		
		for (Document doc : col.find().into(new ArrayList<Document>())) {
			printJson(doc);
		}
	}

}
