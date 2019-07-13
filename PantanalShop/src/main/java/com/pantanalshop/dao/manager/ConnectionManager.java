package com.pantanalshop.dao.manager;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConnectionManager {

    private MongoClient mongoclient;
    private MongoDatabase mongodb;

    public ConnectionManager(String url, String user, String pwd, String database) {

        this.mongoclient = MongoClients.create("mongodb+srv://" + user + ":" + pwd + url);
        this.mongodb = mongoclient.getDatabase(database);
    }

    public MongoDatabase getMongodb() {
        return mongodb;
    }

    public void close(){
        this.mongoclient.close();
    }
}
