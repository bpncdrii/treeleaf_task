package com.bpn.assignment.blog.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bpn.assignment.blog.model.dto.BlogDto;
import com.bpn.assignment.blog.model.dto.BlogImageDto;
import com.bpn.assignment.blog.model.entity.Blog;
import com.bpn.assignment.blog.model.entity.BlogImages;
import com.bpn.assignment.blog.repository.BlogImageRepository;
import com.bpn.assignment.blog.repository.BlogRepository;
import com.bpn.assignment.blog.service.BlogService;
import com.bpn.assignment.comment.model.entity.Comments;
import com.bpn.assignment.comment.repository.CommentRepository;
import com.bpn.assignment.security.model.MyUserDetails;
import com.bpn.assignment.user.model.entity.User;
import com.bpn.assignment.utils.Constants;
import com.bpn.assignment.utils.DtoConverter;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogRepository repo;

	@Autowired
	private BlogImageRepository imageRepo;

	@Autowired
	private CommentRepository cmntRepo;

	@Override
	public Blog save(BlogDto dto) {

		long id = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getSecUser()
				.getUser().getId();

		User user = new User();
		user.setId(id);

		Blog entity = DtoConverter.convert(dto);

		entity.setUser(user);

		Blog blog = repo.save(entity);

		if (dto.getImages() != null) {
			for (BlogImageDto bi : dto.getImages()) {
				BlogImages images = DtoConverter.convert(bi);
				images.setBlog(blog);
				imageRepo.save(images);
			}
		}

		return blog;
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

		long id = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getSecUser()
				.getUser().getId();

		User users = new User();
		users.setId(id);

		if (id != entity.getUser().getId()) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}

		if (dto.getTitle() != null)
			entity.setTitle(dto.getTitle());
		if (dto.getText() != null)
			entity.setText(dto.getText());
		if (dto.getWriter() != null) {
			User user = new User();
			user.setId(dto.getWriter().getId());
			entity.setUser(user);
		}
		if (dto.getImages() != null) {
			for (BlogImageDto bi : dto.getImages()) {
				BlogImages img = imageRepo.findById(bi.getId())
						.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
								"Image not found with id: " + bi.getId()));
				if (bi.getImageName() != null)
					img.setImageName(bi.getImageName());
				imageRepo.save(img);
			}
		}

		return repo.save(entity);
	}

	@Override
	public boolean delete(long id) {
		Blog entity = repo.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog not found with id: " + id));

		long user_id = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getSecUser().getUser().getId();

		String role = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getSecUser().getRoles();

		User users = new User();
		users.setId(user_id);

		if (!role.equals(Constants.ROLE_ADMIN)) {
			if (user_id != entity.getUser().getId()) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
			}
		}

		if (entity.getImages() != null) {
			for (BlogImages img : entity.getImages()) {
				imageRepo.delete(img);
			}
		}

		if (entity.getComments() != null) {
			for (Comments c : entity.getComments()) {
				cmntRepo.delete(c);
			}
		}

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
