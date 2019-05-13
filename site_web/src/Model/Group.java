package Model;
import java.util.List;


public class Group {
	private String name; //name of group
	private List<User> personList; //list of persons
	
	
	
	//Constructor
	public Group(String name, List<User> personList) {
		super();
		this.name = name;
		this.personList = personList;
	}
	
	//getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<User> getPersonList() {
		return personList;
	}
	public void setPersonList(List<User> personList) {
		this.personList = personList;
	}
	
	
	
	
	
}
