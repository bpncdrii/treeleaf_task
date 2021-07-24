
package com.bpn.assignment.security.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	private boolean active;
	private List<? extends GrantedAuthority> authorities;

	private String roles;
	private SecUser user;

	public MyUserDetails(SecUser user) {

		this.email = user.getEmail();
		this.password = user.getPassword();
		this.active = user.isActive();
		this.authorities = Arrays.stream(user.getRoles().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		this.roles = user.getRoles();
		this.user = user;

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("--------------------- User Role = :" + authorities);
		System.out.println();
		System.out.println();
		System.out.println();
	}

	public MyUserDetails() {

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public String getUsername() {

		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return active;
	}

	public String getRoles() {
		return roles;
	}

	public SecUser getSecUser() {
		return user;
	}
}
