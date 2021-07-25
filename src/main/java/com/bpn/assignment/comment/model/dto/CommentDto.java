package com.bpn.assignment.comment.model.dto;

import java.util.Date;

public class CommentDto {

	private long id;
	private String comment;
	private long blogId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public long getBlogId() {
		return blogId;
	}

	public void setBlogId(long blogId) {
		this.blogId = blogId;
	}

}
