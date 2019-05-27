package model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import dev.morphia.annotations.*;

@Entity("users")
public class User {
	@Id
	private ObjectId id;
	private String password; //password in sha512
	private String name; // person's name
	private String surname; //person's surname
	private String email;// person's email
	private String phone;// person's phone
	private String birthDate;// person's birthdate
	@Embedded
	private List<Group> groups=new ArrayList<>();//person's groups
	@Embedded
	private List<String> interests=new ArrayList<>();;//person's interests
	public User() {
		//do nothing
	}
	public User(String password, String name, String surname, String email,
			String birthDate) {
		super();
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.birthDate = birthDate;
	}
	
	

	public List<Group> getGroups() {
		return groups;
	}
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	public List<String> getInterests() {
		return interests;
	}
	public void setInterests(List<String> interests) {
		this.interests = interests;
	}
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	
	
	
	
	

}
