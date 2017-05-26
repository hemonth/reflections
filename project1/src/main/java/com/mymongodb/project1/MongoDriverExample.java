/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymongodb.project1;

import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;

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

//        List<Document> finald = new LinkedList<>();
//        MongoDatabase db = mongoClient.getDatabase("students");
//        MongoCollection<Document> collection = db.getCollection("grades");
//        List<Document> list = collection.find().filter(new Document("type","homework")).sort(new Document("student_id",1).append("score", -1)).into(new LinkedList());
//        for(Document d: list){
//            System.out.println(d.toJson());           
//        }
//        System.out.println("filtering the highest grade in homework for each student:-------------------");
//for(int i=0; i< list.size();i =i+2)
//{
//    finald.add(list.get(i));
//}
//for(Document soit: finald){
//    System.out.println(soit.toJson());
//}System.out.println("deleting all homeworks:-------------------");
//List<BsonDocument> lbson = collection.find().filter(new Document("type","homework")).into(new LinkedList());
//for(Bson bson: lbson){
//    collection.findOneAndDelete(bson);
//}
//collection.insertMany(finald);
// 
//List<Document> finallist = collection.find().sort(new Document("student_id",1)).into(new LinkedList());
//System.out.println("final collection list:");
//for(Document doc: finallist){
//    System.out.println(doc.toJson());
//}
//............................................................................................................

//MongoDatabase db = mongoClient.getDatabase("school");
//MongoCollection<Document> collection = db.getCollection("students");
//List<Document> list = collection.find().projection(Projections.slice("scores", 2, 2)).into(new LinkedList());
//list.stream().forEach(System.out::println);


//...............................................................................................................
//Write a program in the language of your choice that will remove the lowest homework score for each student. Since there is a single document for each student containing an array of scores, you will need to update the scores array and remove the homework.
//Remember, just remove a homework score. Don't remove a quiz or an exam!
//doing aggregate iterable on students documents which contain array of scores(unwinding them into seperate documents)
//List<Document> docs = new ArrayList<>();
//AggregateIterable<Document> output = collection.aggregate(Arrays.asList(
//    new Document("$unwind", "$scores"),
//    new Document("$match", new Document("scores.type", "homework")),
//    new Document("$sort", new Document("_id",1).append("scores.score", 1)),
//        new Document("$project", new Document("scores",1).append("_id",1))
//));
//storing all documents into one list of collection
//for (Document doc : output) {
//docs.add(doc);
//}
//update collection
//for(int i=0;i<docs.size();i =i+2){
//Document query = new Document("_id",docs.get(i).get("_id"));
//Document value = new Document("scores", docs.get(i).get("scores")); //in this context scores define array key
//Document update = new Document("$pull",value);
//collection.updateMany(query, update);
//
//}
//..............................................................................................................
MongoClient c =  new MongoClient();
            MongoDatabase db = c.getDatabase("test");
            MongoCollection<Document> animals = db.getCollection("animals");

           Document animal = new Document("animal", "monkey");

            //animals.insertOne(animal);
            //animal.remove("animal");
            animal.append("animal", "cat");
            //animals.insertOne(animal);
            //animal.remove("animal");
            animal.append("animal", "lion");
            animals.insertOne(animal);

    }
}
