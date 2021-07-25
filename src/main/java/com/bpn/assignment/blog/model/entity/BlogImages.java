package com.bpn.assignment.blog.model.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bpn.assignment.model.entity.DefaultEntityModel;

@Entity
public class BlogImages extends DefaultEntityModel {

	private String imageName;

	@ManyToOne
	@JoinColumn(name = "blog_id", referencedColumnName = "id")
	private Blog blog;

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

}
