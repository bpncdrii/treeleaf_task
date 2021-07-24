package com.bpn.assignment.blog.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bpn.assignment.blog.model.entity.Blog;
import com.bpn.assignment.user.model.entity.User;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

	@Query("select b from Blog b order by b.id desc")
	List<Blog> getAllBlog(Pageable pageable);

	@Query("select b from Blog b where b.user = :user order by b.id desc")
	List<Blog> getBlogByUser(@Param("user") User user, Pageable pageable);

}
