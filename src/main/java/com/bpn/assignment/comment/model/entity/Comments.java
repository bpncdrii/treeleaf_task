package com.bpn.assignment.comment.model.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bpn.assignment.blog.model.entity.Blog;
import com.bpn.assignment.model.entity.DefaultEntityModel;
import com.bpn.assignment.user.model.entity.User;

@Entity
public class Comments extends DefaultEntityModel {

	private String comment;

	@ManyToOne
	@JoinColumn(name = "blog_id", referencedColumnName = "id")
	private Blog blog;

	@ManyToOne
	@JoinColumn(name = "comment_by", referencedColumnName = "id")
	private User user;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
