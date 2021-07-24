package com.bpn.assignment.user.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bpn.assignment.user.model.dto.UserDto1;
import com.bpn.assignment.user.service.UserService;
import com.bpn.assignment.utils.DtoConverter;
import com.bpn.assignment.utils.ResponseMessage;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping
	public ResponseEntity save(@RequestBody UserDto1 dto) {
		return ResponseMessage.success(DtoConverter.convert(service.save(dto)));
	}

	@GetMapping
	public ResponseEntity getOne(@RequestParam("id") long id) {
		return ResponseMessage.success(DtoConverter.convert(service.getOne(id)));
	}

	@GetMapping("/all")
	public ResponseEntity getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
		return ResponseMessage
				.success(service.getAll(page, size).stream().map(DtoConverter::convert).collect(Collectors.toList()));
	}

	@PutMapping
	public ResponseEntity update(@RequestBody UserDto1 dto) {
		return ResponseMessage.success(DtoConverter.convert(service.update(dto)));
	}

	@DeleteMapping
	public ResponseEntity delete(@RequestParam("id") long id) {
		return ResponseMessage.success(service.delete(id));
	}

}
