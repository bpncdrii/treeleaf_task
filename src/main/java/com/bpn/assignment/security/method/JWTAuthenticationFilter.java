
package com.bpn.assignment.security.method;

import com.bpn.assignment.security.model.MyUserDetails;
import com.bpn.assignment.security.model.SecUser;
import com.bpn.assignment.security.model.dto.LoginFailedResponse;
import com.bpn.assignment.security.model.dto.LoginSuccessResponse;
import com.bpn.assignment.security.service.TokenService;
import com.bpn.assignment.user.credential.model.entity.UserStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;
	private TokenService tokenService;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, TokenService tokenService) {
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {

		try {
			SecUser creds = new ObjectMapper().readValue(req.getInputStream(), SecUser.class);

			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			System.out.println("Exception in Attempt : ");
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		if (((MyUserDetails) auth.getPrincipal()).getSecUser().getUserStatus() == UserStatus.blocked) {
			String json = new Gson().toJson(new LoginFailedResponse(false, "Account is blocked"));
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(json);
			logger.info("json: " + json);
		} else {
			String token = tokenService.setNewTokenByUsername(auth);

			String json = new Gson()
					.toJson(new LoginSuccessResponse(true, ((MyUserDetails) auth.getPrincipal()).getUsername(), token,
							((MyUserDetails) auth.getPrincipal()).getRoles()));

			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(json);
			logger.info("json: " + json);
		}
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse res,
			AuthenticationException failed) throws IOException, ServletException {

		String json = new Gson().toJson(new LoginFailedResponse(false, "Incorrect username or password"));
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(json);
		logger.info("json: " + json);
	}
}
