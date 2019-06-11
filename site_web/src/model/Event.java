package model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import dev.morphia.annotations.*;

@Entity("events")
public class Event {
	@Id
	private ObjectId id;
	private String name; // name of the event
	private String date; //starting  date of the event
	@Embedded
	private Adresse adresse; //Where is the event
	private List<String> interests;//theme
	private String description;
	private double price; //price of event
	@Embedded
	private User creator; //who created the event
	@Embedded
	private List<User> participants=new ArrayList<>();
	
	
	//Constructor

	public Event() {

	}
	
	


	public Event(String name, String date, Adresse adresse, List<String> interests, String description,
			User creator) {
		super();
		this.name = name;
		this.date = date;
		this.adresse = adresse;
		this.interests = interests;
		this.description = description;
		this.creator = creator;
		this.participants=new ArrayList<>();
	}




	public ObjectId getId() {
		return id;
	}


	public void setId(ObjectId id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public Adresse getAdresse() {
		return adresse;
	}


	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}


	public List<String> getInterests() {
		return interests;
	}


	public void setInterests(List<String> interests) {
		this.interests = interests;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public User getCreator() {
		return creator;
	}


	public void setCreator(User creator) {
		this.creator = creator;
	}


	public List<User> getParticipants() {
		return participants;
	}


	public void setParticipants(List<User> participants) {
		this.participants = participants;
	}
	
	
	

}