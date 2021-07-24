
package com.bpn.assignment.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bpn.assignment.security.model.MyUserDetails;
import com.bpn.assignment.security.model.SecUser;
import com.bpn.assignment.user.credential.model.entity.UserCredential;
import com.bpn.assignment.user.credential.repository.UserCredentialRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserCredentialRepository secUserCredentialRepository;

	@Override
	public MyUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		System.out.println("-----------------------------------------------");

		UserCredential user = secUserCredentialRepository.findByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("Not Found : " + email);
		}

		SecUser secUser = new SecUser();
		secUser.setEmail(user.getEmail());
		secUser.setPassword(user.getPassword());
		String roles = "";
		String rolePrefix = "ROLE_";
		boolean active = true;

		roles = rolePrefix + user.getUser().getUserRole().getName();

		secUser.setActive(active);
		secUser.setRoles(roles);
		secUser.setUser(user.getUser());

		MyUserDetails myUserDetails = new MyUserDetails(secUser);

		return myUserDetails;
	}

}
