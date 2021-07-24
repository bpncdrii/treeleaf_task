package com.bpn.assignment.user.model.dto;

import com.bpn.assignment.user.credential.model.entity.UserStatus;
import com.bpn.assignment.user.role.model.dto.UserRoleDto;

public class UserDto {

	private long id;
	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	private UserStatus userStatus;

	private UserRoleDto userRole;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public UserRoleDto getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRoleDto userRole) {
		this.userRole = userRole;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

}
