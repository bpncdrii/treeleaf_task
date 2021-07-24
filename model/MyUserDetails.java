package com.addon.bpsbackend.security1.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.jws.soap.SOAPBinding.Use;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class MyUserDetails implements UserDetails{

	
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private boolean active;
	private List<?extends GrantedAuthority> authorities;
	
	private String roles;
	private SecUser user;
	
	
	public MyUserDetails(SecUser user) {
	
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.active = user.isActive();
		this.authorities = Arrays.stream(user.getRoles().split(","))
						.map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList());
		this.roles = user.getRoles();
		this.user = user;
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("--------------------- User Role = :"+authorities);
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	public MyUserDetails() {
		
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return active;
	}

	public String getRoles() {
		return roles;
	}
	
	public SecUser getSecUser() {
		return user;
	}
}
