package com.bpn.assignment.user.credential.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bpn.assignment.user.credential.model.entity.UserCredential;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential, Long> {

	@Query("select u from UserCredential u where u.email = :email")
	UserCredential findByEmail(@Param("email") String email);

}
