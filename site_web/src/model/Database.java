package model;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import dev.morphia.Morphia;

public class Database {
	MongoClient mongoClient;
	DB database ;
	DBCollection users;
	DBCollection groups;
	DBCollection events;
	final Morphia morphia = new Morphia();
	
	public Database() {
		try {
			//creating mongoClient on port 28015
			mongoClient= new MongoClient(new MongoClientURI("mongodb://localhost:28015"));
			database = mongoClient.getDB("OmegaFriendsDatabase");
			users = database.getCollection("users");
			groups = database.getCollection("groups");
			events = database.getCollection("events");
			//tell morphia where to find classes
			morphia.mapPackage("model");
			

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


}
