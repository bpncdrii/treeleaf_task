package com.bpn.assignment.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bpn.assignment.blog.model.entity.BlogImages;

@Repository
public interface BlogImageRepository extends JpaRepository<BlogImages, Long> {

}
