package Homework;

/*
 * Copyright 2015 MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.ascending;

public class RemoveTest {
    public static void main(final String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase numbersDB = client.getDatabase("school");
        MongoCollection<Document> grades = numbersDB.getCollection("students");

        MongoCursor<Document> cursor = grades.find(eq("type", "homework"))
                                .sort(ascending( "score")).iterator();

        Object _Id = -1;
        try {
            while (cursor.hasNext()) {
                Document entry = cursor.next();
                if (!entry.get("_id").equals(_Id)) {
                    System.out.println("Removing: " + entry);
                    Object id = entry.get("_id");
                    grades.deleteOne(eq("_id", id));

                }
                _Id = entry.get("_Id");
           }
        } finally {
            cursor.close();
        }
    }
}