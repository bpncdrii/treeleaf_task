package com.bpn.assignment.blog.service;

import java.util.List;

import com.bpn.assignment.blog.model.dto.BlogDto;
import com.bpn.assignment.blog.model.entity.Blog;

public interface BlogService {

	Blog save(BlogDto dto);

	Blog getOne(long id);

	List<Blog> getAll(int page, int size);

	List<Blog> getByUser(int page, int size, long id);

	List<Blog> getOwnBlogs(int page, int size);

	Blog update(BlogDto dto);

	boolean delete(long id);

}
