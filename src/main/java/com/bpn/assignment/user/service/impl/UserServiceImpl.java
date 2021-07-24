package com.bpn.assignment.user.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bpn.assignment.user.credential.model.entity.UserCredential;
import com.bpn.assignment.user.credential.model.entity.UserStatus;
import com.bpn.assignment.user.credential.repository.UserCredentialRepository;
import com.bpn.assignment.user.model.dto.UserDto1;
import com.bpn.assignment.user.model.entity.User;
import com.bpn.assignment.user.repository.UserRepository;
import com.bpn.assignment.user.role.model.entity.UserRole;
import com.bpn.assignment.user.service.UserService;
import com.bpn.assignment.utils.DtoConverter;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;

	@Autowired
	private UserCredentialRepository credRepo;

	@Override
	public User save(UserDto1 dto) {

		UserRole role = new UserRole();
		role.setId(2);

		User entity = DtoConverter.convert(dto);
		entity.setUserStatus(UserStatus.active);
		entity.setUserRole(role);

		User user = repo.save(entity);

		UserCredential userCredential = new UserCredential();
		userCredential.setEmail(dto.getEmail());
		userCredential.setUser(user);
		String hasPassword = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
		userCredential.setPassword(hasPassword);

		credRepo.save(userCredential);

		return user;
	}

	@Override
	public User getOne(long id) {

		return repo.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));
	}

	@Override
	public List<User> getAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return repo.getAllUser(pageable);
	}

	@Override
	public User update(UserDto1 dto) {
		User entity = repo.findById(dto.getId()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + dto.getId()));

		if (dto.getFirstName() != null)
			entity.setFirstName(dto.getFirstName());
		if (dto.getLastName() != null)
			entity.setLastName(dto.getLastName());
		if (dto.getAddress() != null)
			entity.setAddress(dto.getAddress());
		if (dto.getPhone() != null)
			entity.setPhone(dto.getPhone());

		return repo.save(entity);
	}

	@Override
	public boolean delete(long id) {
		User user = repo.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));
		repo.delete(user);
		return true;
	}

}
