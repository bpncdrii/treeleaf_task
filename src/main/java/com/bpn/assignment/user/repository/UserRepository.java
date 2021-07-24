package com.bpn.assignment.user.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bpn.assignment.user.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u order by u.id desc")
	List<User> getAllUser(Pageable pageable);

}
