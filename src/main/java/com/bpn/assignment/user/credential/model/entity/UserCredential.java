package com.bpn.assignment.user.credential.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.bpn.assignment.model.entity.CommonEntityModel;
import com.bpn.assignment.user.model.entity.User;
import com.bpn.assignment.user.role.model.entity.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class UserCredential extends CommonEntityModel {

	@Id
	private String email;

	private String password;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private User user;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
