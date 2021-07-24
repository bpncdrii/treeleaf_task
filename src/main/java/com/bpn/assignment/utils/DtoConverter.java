package com.bpn.assignment.utils;

import com.bpn.assignment.blog.model.dto.BlogDto;
import com.bpn.assignment.blog.model.entity.Blog;
import com.bpn.assignment.user.credential.model.dto.UserCredentialDto;
import com.bpn.assignment.user.credential.model.entity.UserCredential;
import com.bpn.assignment.user.model.dto.UserDto;
import com.bpn.assignment.user.model.dto.UserDto1;
import com.bpn.assignment.user.model.dto.UserDto2;
import com.bpn.assignment.user.model.entity.User;
import com.bpn.assignment.user.role.model.dto.UserRoleDto;
import com.bpn.assignment.user.role.model.entity.UserRole;

public class DtoConverter {

	public static UserDto convert(User entity) {
		UserDto dto = new UserDto();
		dto.setId(entity.getId());
		dto.setFirstName(entity.getFirstName());
		dto.setAddress(entity.getAddress());
		dto.setPhone(entity.getPhone());
		dto.setUserStatus(entity.getUserStatus());
		if (entity.getUserRole() != null) {
			dto.setUserRole(DtoConverter.convert(entity.getUserRole()));
		}

		return dto;
	}

	public static User convert(UserDto1 dto) {

		UserRole userRole = new UserRole();
		userRole.setId(dto.getRoleId());

		UserCredential userCredential = new UserCredential();
		userCredential.setEmail(dto.getEmail());
		userCredential.setPassword(dto.getPassword());

		User entity = new User();
		entity.setId(dto.getId());
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setAddress(dto.getAddress());
		entity.setPhone(dto.getPhone());
		entity.setUserStatus(dto.getUserStatus());

		entity.setUserRole(userRole);

		entity.setUserCredential(userCredential);
		return entity;
	}

	public static UserCredentialDto convert(UserCredential entity) {
		UserCredentialDto dto = new UserCredentialDto();
		dto.setEmail(entity.getEmail());
		dto.setPassword(entity.getPassword());
		return dto;
	}

	public static UserCredential convert(UserCredentialDto dto) {
		UserCredential entity = new UserCredential();
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());

		return entity;
	}

	public static UserRoleDto convert(UserRole entity) {
		UserRoleDto dto = new UserRoleDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}

	public static UserRole convert(UserRoleDto dto) {
		UserRole entity = new UserRole();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		return entity;
	}

	public static UserDto2 convertWriter(User entity) {
		UserDto2 dto = new UserDto2();
		dto.setId(entity.getId());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		dto.setAddress(entity.getAddress());

		return dto;
	}

	public static Blog convert(BlogDto dto) {

		Blog entity = new Blog();

		entity.setId(dto.getId());
		entity.setTitle(dto.getTitle());
		entity.setText(dto.getText());
		entity.setImage(dto.getImage());
		if (dto.getWriter() != null) {
			User user = new User();
			user.setId(dto.getWriter().getId());
			entity.setUser(user);
		}
		return entity;
	}

	public static BlogDto convert(Blog entity) {
		BlogDto dto = new BlogDto();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setText(entity.getText());
		dto.setImage(entity.getImage());
		if (entity.getUser() != null) {
			dto.setWriter(DtoConverter.convertWriter(entity.getUser()));
		}
		return dto;
	}
}
