package com.javajwt.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.http.SecurityHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.javajwt.services.CustomUserdetailsService;
import com.javajwt.util.JwtUtil;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private CustomUserdetailsService customUserdetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String RequestTokenHeader = request.getHeader("Authorization");
		String userName = null;
		String jwtToken = null;
		if (RequestTokenHeader != null && RequestTokenHeader.startsWith("Bearer")) {
			jwtToken = RequestTokenHeader.substring(7);
			try {
				userName = this.jwtUtil.extractUsername(jwtToken);

			} catch (Exception e) {
				e.printStackTrace();
			}

			UserDetails userDetails = this.customUserdetailsService.loadUserByUsername(userName);
			if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(userDetails, null,
						userDetails.getAuthorities());
				upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(upat);
			} else
				System.out.println("Token is not valid");
		}
		filterChain.doFilter(request, response);
	}

}
