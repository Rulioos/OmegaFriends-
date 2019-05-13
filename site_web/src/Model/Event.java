package Model;

public class Event {
	private String name; // name of the event
	private String date; //starting  date of the event
	private String localization; //Where is the event
	/*
	 * Localization may become an object on his own.
	 */
	private double price; //price of event
	private User creator; //who created the event
	
	
	//Constructor
	public Event(String name, String date, String localization, double price, User creator) {
		super();
		this.name = name;
		this.date = date;
		this.localization = localization;
		this.price = price;
		this.creator = creator;
	}
	
	//Getters and setters
	public String getLocalization() {
		return localization;
	}
	public void setLocalization(String localization) {
		this.localization = localization;
	}
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
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
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	

}