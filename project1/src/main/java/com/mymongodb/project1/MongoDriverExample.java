/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymongodb.project1;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.LinkedList;
import java.util.List;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author hemonth.mandava
 */
public class MongoDriverExample {

//    protected MongoClient mongoClient;
//    
//    protected MongoDatabase db;
//    
//    protected MongoCollection<BsonDocument> collection;
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient();
        
//        MongoDatabase db = mongoClient.getDatabase("movies");
//        MongoCollection<Document> collection = db.getCollection("insertTest");
//        
//        Document hemonth = new Document("name", "hemonth").append("age", 23).append("note", "inserting document using java mongodb driver");
//       // printJson(hemonth);
//        collection.insertOne(hemonth);
//        System.out.println(hemonth.toJson());

//------------------------------------------------------------------------------------------------------
// to test the below code working import the data into mongodb server from grades.json file(copy the file to desktop) using the below shell command:
//mongoimport --drop -d students -c grades grades.json;

        List<Document> finald = new LinkedList<>();
        MongoDatabase db = mongoClient.getDatabase("students");
        MongoCollection<Document> collection = db.getCollection("grades");
        List<Document> list = collection.find().filter(new Document("type","homework")).sort(new Document("student_id",1).append("score", -1)).into(new LinkedList());
        for(Document d: list){
            System.out.println(d.toJson());           
        }
        System.out.println("filtering the highest grade in homework for each student:-------------------");
for(int i=0; i< list.size();i =i+2)
{
    finald.add(list.get(i));
}
for(Document soit: finald){
    System.out.println(soit.toJson());
}System.out.println("deleting all homeworks:-------------------");
List<BsonDocument> lbson = collection.find().filter(new Document("type","homework")).into(new LinkedList());
for(Bson bson: lbson){
    collection.findOneAndDelete(bson);
}
collection.insertMany(finald);
 
List<Document> finallist = collection.find().sort(new Document("student_id",1)).into(new LinkedList());
System.out.println("final collection list:");
for(Document doc: finallist){
    System.out.println(doc.toJson());
}
    }
}
