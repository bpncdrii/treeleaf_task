package com.bpn.assignment.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bpn.assignment.blog.model.dto.BlogDto;
import com.bpn.assignment.blog.model.entity.Blog;
import com.bpn.assignment.blog.repository.BlogRepository;
import com.bpn.assignment.blog.service.BlogService;
import com.bpn.assignment.security.model.MyUserDetails;
import com.bpn.assignment.user.model.entity.User;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogRepository repo;

	@Override
	public Blog save(Blog entity) {

		long id = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getSecUser()
				.getUser().getId();

		User user = new User();
		user.setId(id);

		entity.setUser(user);

		return repo.save(entity);
	}

	@Override
	public Blog getOne(long id) {

		return repo.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog not found with id: " + id));
	}

	@Override
	public List<Blog> getAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return repo.getAllBlog(pageable);
	}

	@Override
	public Blog update(BlogDto dto) {
		Blog entity = repo.findById(dto.getId()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog not found with id: " + dto.getId()));

		if (dto.getTitle() != null)
			entity.setTitle(dto.getTitle());
		if (dto.getText() != null)
			entity.setText(dto.getText());
		if (dto.getImage() != null)
			entity.setImage(dto.getImage());
		if (dto.getWriter() != null) {
			User user = new User();
			user.setId(dto.getWriter().getId());
			entity.setUser(user);
		}

		return repo.save(entity);
	}

	@Override
	public boolean delete(long id) {
		Blog entity = repo.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog not found with id: " + id));
		repo.delete(entity);
		return true;
	}

	@Override
	public List<Blog> getByUser(int page, int size, long id) {
		User user = new User();
		user.setId(id);
		Pageable pageable = PageRequest.of(page, size);
		return repo.getBlogByUser(user, pageable);
	}

	@Override
	public List<Blog> getOwnBlogs(int page, int size) {
		long id = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getSecUser()
				.getUser().getId();

		User user = new User();
		user.setId(id);
		Pageable pageable = PageRequest.of(page, size);

		return repo.getBlogByUser(user, pageable);
	}

}
