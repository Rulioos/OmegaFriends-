package Model;
import java.util.List;


public class Group {
	private String name; //name of group
	private List<Person> personList; //list of persons
	
	
	
	//Constructor
	public Group(String name, List<Person> personList) {
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
	public List<Person> getPersonList() {
		return personList;
	}
	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}
	
	
	
	
	
}
