package com.mongodb.driver.crud;

import org.bson.BsonDocument;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class App {

	public static void main(String args[]) {
		MongoClientOptions options = new MongoClientOptions.Builder().connectionsPerHost(100).build();
		MongoClient client = new MongoClient(new ServerAddress(),options);
		
		MongoDatabase db = client.getDatabase("video").withReadPreference(ReadPreference.secondary());
		
		MongoCollection<BsonDocument> col = db.getCollection("movies",BsonDocument.class);
	}
}
