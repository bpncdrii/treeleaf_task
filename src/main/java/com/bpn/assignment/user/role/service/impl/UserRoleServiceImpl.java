package com.bpn.assignment.user.role.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpn.assignment.user.role.model.entity.UserRole;
import com.bpn.assignment.user.role.repository.UserRoleRepository;
import com.bpn.assignment.user.role.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleRepository repo;

	@Override
	public List<UserRole> getAll() {

		return repo.findAll();
	}

}
