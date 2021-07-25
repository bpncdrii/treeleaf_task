package com.bpn.assignment.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bpn.assignment.comment.model.dto.CommentDto;
import com.bpn.assignment.comment.service.CommentService;
import com.bpn.assignment.utils.Constants;
import com.bpn.assignment.utils.DtoConverter;
import com.bpn.assignment.utils.ResponseMessage;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService service;

	@PostMapping
	@PreAuthorize("hasAnyRole('" + Constants.ROLE_ADMIN + "', '" + Constants.ROLE_CLIENT + "')")
	public ResponseEntity save(@RequestBody CommentDto dto) {
		return ResponseMessage.success(DtoConverter.convert(service.save(DtoConverter.convert(dto))));
	}

	@PutMapping
	@PreAuthorize("hasAnyRole('" + Constants.ROLE_ADMIN + "', '" + Constants.ROLE_CLIENT + "')")
	public ResponseEntity update(@RequestBody CommentDto dto) {
		return ResponseMessage.success(DtoConverter.convert(service.update(dto)));
	}

	@DeleteMapping
	@PreAuthorize("hasAnyRole('" + Constants.ROLE_ADMIN + "', '" + Constants.ROLE_CLIENT + "')")
	public ResponseEntity delete(@RequestParam("id") long id) {
		return ResponseMessage.success(service.delete(id));
	}
}
