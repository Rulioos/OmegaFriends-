package model;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import dev.morphia.Datastore;
import dev.morphia.Morphia;

public class Database {

	final static Morphia morphia = new Morphia();

	// connecting to cluster
	static MongoClientURI uri = new MongoClientURI(
			"mongodb+srv://richard:<password>@cluster0-ewi0t.mongodb.net/test?retryWrites=true");

	static MongoClient mongoClient = new MongoClient(uri);
	final static Datastore datastore = morphia.createDatastore(mongoClient, "omega");
	


}
