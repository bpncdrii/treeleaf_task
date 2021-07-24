package com.bpn.assignment.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bpn.assignment.user.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
