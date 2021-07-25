package com.bpn.assignment.comment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bpn.assignment.comment.model.dto.CommentDto;
import com.bpn.assignment.comment.model.entity.Comments;
import com.bpn.assignment.comment.repository.CommentRepository;
import com.bpn.assignment.comment.service.CommentService;
import com.bpn.assignment.security.model.MyUserDetails;
import com.bpn.assignment.user.model.entity.User;
import com.bpn.assignment.utils.Constants;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository repo;

	@Override
	public Comments save(Comments entity) {

		long id = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getSecUser()
				.getUser().getId();

		User user = new User();
		user.setId(id);
		entity.setUser(user);

		return repo.save(entity);
	}

	@Override
	public Comments update(CommentDto dto) {
		long id = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getSecUser()
				.getUser().getId();

		User user = new User();
		user.setId(id);

		Comments entity = repo.findById(dto.getId()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found with id: " + dto.getId()));

		if (id != entity.getUser().getId()) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}

		if (dto.getComment() != null)
			entity.setComment(dto.getComment());

		return repo.save(entity);
	}

	@Override
	public boolean delete(long id) {
		long user_id = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getSecUser().getUser().getId();

		String role = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getSecUser().getRoles();

		User user = new User();
		user.setId(user_id);

		Comments entity = repo.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found with id: " + id));
		if (!role.equals(Constants.ROLE_ADMIN)) {
			if (user_id != entity.getUser().getId()) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
			}
		}

		repo.delete(entity);
		return true;
	}

}
