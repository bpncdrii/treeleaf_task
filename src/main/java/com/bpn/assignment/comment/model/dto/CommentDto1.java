package com.bpn.assignment.comment.model.dto;

import java.util.Date;

import com.bpn.assignment.user.model.dto.UserDto2;

public class CommentDto1 {

	private long id;
	private String comment;
	private UserDto2 commentBy;
	private Date commentAt;

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

	public UserDto2 getCommentBy() {
		return commentBy;
	}

	public void setCommentBy(UserDto2 commentBy) {
		this.commentBy = commentBy;
	}

	public Date getCommentAt() {
		return commentAt;
	}

	public void setCommentAt(Date commentAt) {
		this.commentAt = commentAt;
	}

}
