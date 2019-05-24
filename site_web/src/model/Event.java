package model;

import java.util.List;

import org.bson.types.ObjectId;

import dev.morphia.annotations.*;

@Entity("events")
public class Event {
	@Id
	private ObjectId id;
	private String name; // name of the event
	private String date; //starting  date of the event
	private Adresse adresse; //Where is the event
	private List<String> interests;//theme
	private String description;
	private double price; //price of event
	private User creator; //who created the event
	
	
	//Constructor

	public Event() {
		
	}
	
	
	

}