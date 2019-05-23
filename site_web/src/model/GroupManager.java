package model;

import java.util.List;

public class GroupManager {
	private List<Group> allGroups;
	
	
	public GroupManager() {
		allGroups=Database.datastore.createQuery(Group.class).find().toList();
	}
	
	public boolean deleteGroup(User member,Group group) {
		if(member.equals(group.getOwner())) {
			allGroups.remove(group);
		}
		return false;
	}
}
