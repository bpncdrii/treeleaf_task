package com.bpn.assignment.user.model.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.bpn.assignment.blog.model.entity.Blog;
import com.bpn.assignment.comment.model.entity.Comments;
import com.bpn.assignment.model.entity.DefaultEntityModel;
import com.bpn.assignment.user.credential.model.entity.UserCredential;
import com.bpn.assignment.user.credential.model.entity.UserStatus;
import com.bpn.assignment.user.role.model.entity.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User extends DefaultEntityModel {

	private String firstName;
	private String lastName;
	private String address;
	private String phone;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	private UserCredential userCredential;

	@ManyToOne
	@JoinColumn(name = "user_role", referencedColumnName = "id")
	private UserRole userRole;

	private UserStatus userStatus;

	@OneToMany(mappedBy = "user")
	private Set<Blog> blogs;

	@OneToMany(mappedBy = "user")
	private Set<Comments> comments;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public UserCredential getUserCredential() {
		return userCredential;
	}

	public void setUserCredential(UserCredential userCredential) {
		this.userCredential = userCredential;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public Set<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(Set<Blog> blogs) {
		this.blogs = blogs;
	}

	public Set<Comments> getComments() {
		return comments;
	}

	public void setComments(Set<Comments> comments) {
		this.comments = comments;
	}

}
