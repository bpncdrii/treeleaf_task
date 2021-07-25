package com.bpn.assignment.comment.service;

import com.bpn.assignment.comment.model.dto.CommentDto;
import com.bpn.assignment.comment.model.entity.Comments;

public interface CommentService {

	Comments save(Comments entity);

	Comments update(CommentDto dto);

	boolean delete(long id);

}
