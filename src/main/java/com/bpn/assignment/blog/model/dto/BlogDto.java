package com.bpn.assignment.blog.model.dto;

import com.bpn.assignment.user.model.dto.UserDto2;

public class BlogDto {

	private long id;
	private String title;
	private String text;
	private String image;
	private String imageUrl;

	private UserDto2 writer;

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public UserDto2 getWriter() {
		return writer;
	}

	public void setWriter(UserDto2 writer) {
		this.writer = writer;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
