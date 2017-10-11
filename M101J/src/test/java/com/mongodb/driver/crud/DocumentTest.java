package com.mongodb.driver.crud;

import java.util.Arrays;
import java.util.Date;

import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.m101j.util.Helpers.printJson;

public class DocumentTest {

	public static void main(String[] args) {

		Document doc = new Document().append("str", "MongoDB, Hello").append("int", 42).append("l", 1L)
				.append("double", 1.1).append("bool", true).append("date", new Date())
				.append("objectId", new ObjectId()).append("null", null).append("embeddedDoc", new Document("x", 0))
				.append("List", Arrays.asList(1, 2, 3));

		printJson(doc);
	}

}
