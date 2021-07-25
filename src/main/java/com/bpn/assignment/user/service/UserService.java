package com.bpn.assignment.user.service;

import java.util.List;

import com.bpn.assignment.user.credential.model.entity.UserStatus;
import com.bpn.assignment.user.model.dto.UserDto1;
import com.bpn.assignment.user.model.entity.User;

public interface UserService {

	User save(UserDto1 dto);

	User getOne(long id);

	List<User> getAll(int page, int size);

	User update(UserDto1 dto);

	boolean delete(long id);
	
	User changeUserStatus(long id, UserStatus status);

}
