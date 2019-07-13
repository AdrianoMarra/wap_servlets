package com.pantanalshop.dao;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClientException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.pantanalshop.model.User;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    Gson mapper = new Gson();

    public User authenticate(MongoDatabase con, String login){

        User user;
        BasicDBObject query = new BasicDBObject();
        query.put("email", login);
        MongoCollection<Document> coll = con.getCollection("users");
        MongoCursor cursor = coll.find(query).iterator();

        try {
            while (cursor.hasNext()) {
                user = mapper.fromJson(mapper.toJson(cursor.next()), User.class);
                user.setKey();
                return user;
            }
        } finally {
            cursor.close();
        }

        return null;
    }

    public int countUsers(MongoDatabase con){

        MongoCollection<Document> coll = con.getCollection("users");
        MongoCursor cursor = coll.find().iterator();
        int count = 0;
        while (cursor.hasNext()){
            count++;
            cursor.next();
        }
        return count;
    }

    public Boolean registerUser(MongoDatabase con, int total, String user, String email, String pass, String address){

        MongoCollection<Document> coll = con.getCollection("users");
        try{
            Document document = new Document();
            document.put("id", (total + 1));
            document.put("name", user);
            document.put("email", email);
            document.put("password", pass);
            document.put("address", address);
            List<Document> cart = new ArrayList<>();
            document.put("cart", cart);
            coll.insertOne(document);
        }catch(MongoClientException e){
            return false;
        }
        return true;
    }
}
