package model;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import dev.morphia.Datastore;
import dev.morphia.Morphia;

public class Database {

	final static Morphia morphia = new Morphia();

	static MongoClient mongoClient = new MongoClient("localhost",27017);
	final static Datastore datastore = morphia.createDatastore(mongoClient, "omega");

}
