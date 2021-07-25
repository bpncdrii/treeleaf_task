package com.bpn.assignment.blog.model.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.bpn.assignment.comment.model.entity.Comments;
import com.bpn.assignment.model.entity.DefaultEntityModel;
import com.bpn.assignment.user.model.entity.User;

@Entity
public class Blog extends DefaultEntityModel {

	private String title;

	@Column(name = "blog_text", columnDefinition = "Text")
	private String text;

	@ManyToOne
	@JoinColumn(name = "writer_id", referencedColumnName = "id")
	private User user;

	@OneToMany(mappedBy = "blog")
	private Set<BlogImages> images;

	@OneToMany(mappedBy = "blog")
	private Set<Comments> comments;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<BlogImages> getImages() {
		return images;
	}

	public void setImages(Set<BlogImages> images) {
		this.images = images;
	}

	public Set<Comments> getComments() {
		return comments;
	}

	public void setComments(Set<Comments> comments) {
		this.comments = comments;
	}

}
