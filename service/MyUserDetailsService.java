package com.addon.bpsbackend.security1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.addon.bpsbackend.security.model.entity.SecUserCredential;
import com.addon.bpsbackend.security.repository.SecUserCredentialRepository;
import com.addon.bpsbackend.security1.model.MyUserDetails;
import com.addon.bpsbackend.security1.model.SecUser;


@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	SecUserCredentialRepository secUserCredentialRepository;

	@Override
	public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SecUserCredential user = secUserCredentialRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("Not Found : " + username);
		}
		
		SecUser secUser = new SecUser();
		secUser.setUserName(user.getEmail());
		secUser.setPassword(user.getPassword());
		secUser.setPersonPerma(user.getPerson());
		secUser.setOrganization(user.getPerson().getOrganization());
		String roles = "";
		String rolePrefix = "ROLE_";
		boolean active = true;
		
		roles = rolePrefix+user.getPerson().getPersonRole().getName();
		
		secUser.setActive(active);
		secUser.setRoles(roles);

		MyUserDetails myUserDetails = new MyUserDetails(secUser);
		
		return myUserDetails;
	}

}
