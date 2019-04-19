package com.jasnowski.utils;


import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;
import org.bson.*;
import org.bson.json.*;

import java.util.*;
import javax.swing.*;


public class MongoUtils {

    private static String CRLF = "\r\n";

    private static MongoClient mongoClient = null;


    /**
     * Lists info the databases / collections and documents
     * @param window
     */
    public static String listDBInfo() {

          StringBuffer buffer = new StringBuffer();

          //buffer.append("Hello Mongo!!" + CRLF);

          buffer.append("Connecting..." + getClient().getAddress() + CRLF);

          return buffer.toString();

    }

    /**
     * Returns all the documents from a collection in a database as one string
     * @param databaseName
     * @param collectionName
     * @return
     */
    public static String getDocuments(String databaseName,
                                      String collectionName){

        // For pretty printing
        //Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //JsonParser parser = new JsonParser();
        JsonWriterSettings settings = new JsonWriterSettings(true);

        StringBuffer b = new StringBuffer();

        if (databaseName != null &&
            collectionName != null &&
            !"".equals(databaseName) &&
            !"".equals(collectionName)) {

            MongoCollection<Document> document =
                    (MongoCollection) getClient().getDatabase(databaseName).getCollection(collectionName);

            for (Document doc : document.find()) {

                b.append(doc.toJson(settings) + "\r\n");
            }

        }

        return b.toString();

    }

    /**
     * Returns all the documents from a collection in a database as a collection of objects
     * @param databaseName
     * @param collectionName
     * @return
     */
    public static MongoCollection<Document> getDocumentObjects(String databaseName,
                                                               String collectionName){

        // For pretty printing
        //Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //JsonParser parser = new JsonParser();
        JsonWriterSettings settings = new JsonWriterSettings(true);

        StringBuffer b = new StringBuffer();

        if (databaseName != null &&
                collectionName != null &&
                !"".equals(databaseName) &&
                !"".equals(collectionName)) {

            MongoCollection<Document> documents =
                    (MongoCollection) getClient().getDatabase(databaseName).getCollection(collectionName);

            return documents;

        }

        return null;

    }

    /**
     * Returns a list of collections for a database
     * @param databaseName
     * @return
     */

    public static ArrayList<String> getCollections(String databaseName){
        ArrayList<String> collectionNames = new ArrayList<String>();

        if (databaseName != null) {
            MongoIterable<String> collections =
                    getClient().getDatabase(databaseName).listCollectionNames();
            for (String collection : collections) {
                collectionNames.add(collection);
            }

        }
        return collectionNames;
    }

    /**
     * Returns a list of database names
     * @return
     */
    public static ArrayList<String> getDatabaseNames(){

           return (ArrayList)getClient().getDatabaseNames();
    }

    /**
     * Returns an instance of the MongoDB Database Client
     * @return
     */

    public static MongoClient getClient(){

        if (mongoClient == null) {

            mongoClient = new MongoClient("localhost", 27017);

        }

        return mongoClient;

    }


}