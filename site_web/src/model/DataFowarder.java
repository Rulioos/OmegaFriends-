package model;

import java.util.ArrayList;

public class DataFowarder {
	private String date;
	private ArrayList<VkEvent> events;
	private ArrayList<VkEvent> new_cal1;
	private ArrayList<VkEvent> new_cal2;
	
	
	public DataFowarder(String date, ArrayList<VkEvent> events) {
		super();
		this.date = date;
		this.events = events;
	}


	
	
	
	public DataFowarder(ArrayList<VkEvent> new_cal1, ArrayList<VkEvent> new_cal2) {
		super();
		this.new_cal1 = new_cal1;
		this.new_cal2 = new_cal2;
	}





	public String getDate() {
		return date;
	}





	public ArrayList<VkEvent> getEvents() {
		return events;
	}





	public ArrayList<VkEvent> getNew_cal1() {
		return new_cal1;
	}





	public ArrayList<VkEvent> getNew_cal2() {
		return new_cal2;
	}






	
	
	
}
