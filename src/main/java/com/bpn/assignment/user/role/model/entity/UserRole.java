package com.bpn.assignment.user.role.model.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.bpn.assignment.model.entity.DefaultEntityModel;
import com.bpn.assignment.user.model.entity.User;

@Entity
public class UserRole extends DefaultEntityModel {

	private String name;

	@OneToMany(mappedBy = "userRole")
	private Set<User> users;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
