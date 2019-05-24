package model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import dev.morphia.annotations.*;

@Entity("groups")
public class Group {
	@Id
	private ObjectId id;
	private String name; // name of group
	private User owner;// owner of the group
	private List<User> members; // list of persons
	private List<String> interests;//Interests

	// Constructors
	public Group(String name, User owner) {
		super();
		this.name = name;
		this.owner = owner;
		this.members = new ArrayList<>();
		this.interests = new ArrayList<>();
	}

	public Group() {
		// empty constructor
	}

	// methods
	// Add a member in the group
	public boolean AddMember(String email, User member) {
		
		User user = Database.findUserByEmail(email);
		if (user != null) {
			if (members.stream().filter(k -> k.equals(user)).findFirst().orElse(null) == null) {
				members.add(user);
				return true;
			}
		}
		return false;
	}

	// Remove member from the group
	public boolean removeMember(String email, User member) {
		if (!member.equals(owner)) {
			return false;
		}
		User user = members.stream().filter(k -> k.getEmail().equals(email)).findFirst().orElse(null);
		if (user != null) {
			members.remove(user);
			return true;
		}
		return false;
	}
	// Delete the group

	// Add an interest to the group
	public boolean AddInterest(String interest,User member) {
		if (!member.equals(owner)) {
			return false;
		}
		if (interests.stream().filter(k -> k.equals(interest)).findFirst().orElse(null) == null) {
			interests.add(interest);
			return true;
		}
		return false;
	}

	// getters and setters
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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<User> getmembers() {
		return members;
	}

	public void setmembers(List<User> members) {
		this.members = members;
	}

	public List<String> getInterests() {
		return interests;
	}

	public void setInterests(List<String> interests) {
		this.interests = interests;
	}

}
