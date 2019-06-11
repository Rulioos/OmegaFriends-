package model;


import java.util.List;

import com.mongodb.MongoClient;


import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;

public class Database {

	final static Morphia morphia = new Morphia();

	static MongoClient mongoClient = new MongoClient("localhost",27017);
	final static Datastore datastore = morphia.createDatastore(mongoClient, "omega");
	
	public static User findUserByEmail(String email) {
		return datastore.createQuery(User.class).field("email").equal(email).first();
	}
	
	@SuppressWarnings("deprecation")
	public static List<Group> getAllGroups() {
		return datastore.createQuery(Group.class).asList();
	}
	
	public static boolean updateUser(User user,String field,Object value,String password) {
		Query<User> q=datastore.createQuery(User.class).field("email").equal(user.getEmail());
		UpdateOperations<User> ops=datastore.createUpdateOperations(User.class).set(field, value);
		
		if( password.equals (user.getPassword())) {
			datastore.update(q, ops);
			return true;
		}
		return false;
	}

	public static List<Event> getAllEvents() {
		return datastore.createQuery(Event.class).asList();
	
	}
}
