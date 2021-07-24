package com.addon.bpsbackend.security1.model;

import com.addon.bpsbackend.organization.model.entity.Organization;
import com.addon.bpsbackend.personperma.model.entity.PersonPerma;
import com.addon.bpsbackend.security.constants.UserStatus;

public class SecUser {
	
	private int id;
	private String userName;
	private String password;
	private boolean active;
	private String roles;
	private UserStatus userStatus;
	private PersonPerma personPerma;
	
	private Organization organization;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public UserStatus getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}
	public PersonPerma getPersonPerma() {
		return personPerma;
	}
	public void setPersonPerma(PersonPerma personPerma) {
		this.personPerma = personPerma;
	}
	
}
