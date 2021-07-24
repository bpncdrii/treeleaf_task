package com.bpn.assignment.blog.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bpn.assignment.model.entity.DefaultEntityModel;
import com.bpn.assignment.user.model.entity.User;

@Entity
public class Blog extends DefaultEntityModel {

	private String title;

	@Column(name = "blog_text", columnDefinition = "Text")
	private String text;
	private String image;

	@ManyToOne
	@JoinColumn(name = "writer_id", referencedColumnName = "id")
	private User user;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
