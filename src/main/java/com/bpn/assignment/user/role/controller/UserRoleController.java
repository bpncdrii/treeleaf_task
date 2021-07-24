package com.bpn.assignment.user.role.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bpn.assignment.user.role.service.UserRoleService;
import com.bpn.assignment.utils.DtoConverter;
import com.bpn.assignment.utils.ResponseMessage;

@RestController
@RequestMapping("/user/role")
public class UserRoleController {

	@Autowired
	private UserRoleService service;

	@GetMapping
	public ResponseEntity getAllRole() {
		return ResponseMessage
				.success(service.getAll().stream().map(DtoConverter::convert).collect(Collectors.toList()));
	}

}
