
package com.bpn.assignment.security.model;

import org.springframework.security.core.Authentication;

import com.bpn.assignment.utils.SecurityConstants;

import java.time.LocalDateTime;

public class AuthToken {
	private String token;
	private Authentication authentication;
	private LocalDateTime dateTime;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public boolean isExpired() {
		return dateTime.plusSeconds(SecurityConstants.EXPIRATION_TIME / 1000).isBefore(LocalDateTime.now());
	}

	public void renew() {
		dateTime = LocalDateTime.now();
	}
}
