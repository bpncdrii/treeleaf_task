package com.bpn.assignment.blog.model.dto;

import java.util.List;

import com.bpn.assignment.comment.model.dto.CommentDto1;
import com.bpn.assignment.user.model.dto.UserDto2;

public class BlogDto {

	private long id;
	private String title;
	private String text;

	private UserDto2 writer;

	private List<BlogImageDto> images;

	private List<CommentDto1> comments;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public UserDto2 getWriter() {
		return writer;
	}

	public void setWriter(UserDto2 writer) {
		this.writer = writer;
	}

	public List<BlogImageDto> getImages() {
		return images;
	}

	public void setImages(List<BlogImageDto> images) {
		this.images = images;
	}

	public List<CommentDto1> getComments() {
		return comments;
	}

	public void setComments(List<CommentDto1> comments) {
		this.comments = comments;
	}

}
