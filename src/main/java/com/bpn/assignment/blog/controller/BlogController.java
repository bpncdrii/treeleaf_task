package com.bpn.assignment.blog.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bpn.assignment.blog.model.dto.BlogDto;
import com.bpn.assignment.blog.service.BlogService;
import com.bpn.assignment.utils.Constants;
import com.bpn.assignment.utils.DtoConverter;
import com.bpn.assignment.utils.ResponseMessage;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private BlogService service;

	@PostMapping
	@PreAuthorize("hasAnyRole('" + Constants.ROLE_ADMIN + "', '" + Constants.ROLE_CLIENT + "')")
	public ResponseEntity save(@RequestBody BlogDto dto) {
		return ResponseMessage.success(DtoConverter.convert(service.save(DtoConverter.convert(dto))));
	}

	@GetMapping
	@PreAuthorize("hasAnyRole('" + Constants.ROLE_ADMIN + "', '" + Constants.ROLE_CLIENT + "')")
	public ResponseEntity getOne(@RequestParam("id") long id) {
		return ResponseMessage.success(DtoConverter.convert(service.getOne(id)));
	}

	@GetMapping("/all")
	@PreAuthorize("hasAnyRole('" + Constants.ROLE_ADMIN + "', '" + Constants.ROLE_CLIENT + "')")
	public ResponseEntity getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
		return ResponseMessage
				.success(service.getAll(page, size).stream().map(DtoConverter::convert).collect(Collectors.toList()));
	}

	@PutMapping
	@PreAuthorize("hasAnyRole('" + Constants.ROLE_ADMIN + "', '" + Constants.ROLE_CLIENT + "')")
	public ResponseEntity update(@RequestBody BlogDto dto) {
		return ResponseMessage.success(DtoConverter.convert(service.update(dto)));
	}

	@DeleteMapping
	@PreAuthorize("hasAnyRole('" + Constants.ROLE_ADMIN + "', '" + Constants.ROLE_CLIENT + "')")
	public ResponseEntity delete(@RequestParam("id") long id) {
		return ResponseMessage.success(service.delete(id));
	}

	@GetMapping("/by/user")
	@PreAuthorize("hasAnyRole('" + Constants.ROLE_ADMIN + "', '" + Constants.ROLE_CLIENT + "')")
	public ResponseEntity getByUser(@RequestParam("page") int page, @RequestParam("size") int size,
			@RequestParam("id") long id) {
		return ResponseMessage.success(
				service.getByUser(page, size, id).stream().map(DtoConverter::convert).collect(Collectors.toList()));
	}

	@GetMapping("/own")
	@PreAuthorize("hasAnyRole('" + Constants.ROLE_ADMIN + "', '" + Constants.ROLE_CLIENT + "')")
	public ResponseEntity getOwnBlogs(@RequestParam("page") int page, @RequestParam("size") int size) {
		return ResponseMessage.success(
				service.getOwnBlogs(page, size).stream().map(DtoConverter::convert).collect(Collectors.toList()));
	}

}
