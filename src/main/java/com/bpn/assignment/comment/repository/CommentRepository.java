package com.bpn.assignment.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bpn.assignment.comment.model.entity.Comments;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {

}
