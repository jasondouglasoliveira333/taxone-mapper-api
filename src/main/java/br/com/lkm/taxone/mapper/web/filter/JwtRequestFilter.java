package br.com.lkm.taxone.mapper.web.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.lkm.taxone.mapper.dto.POCUser;
import br.com.lkm.taxone.mapper.service.JwtUserDetailsService;
import br.com.lkm.taxone.mapper.util.JwtTokenUtil;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader("Authorization");
		System.out.println("request.getRequestURI():" + request.getRequestURI() + " - request.getMethod():" + request.getMethod());
		System.out.println("requestTokenHeader:" + requestTokenHeader);
//		String username = null;
//		String jwtToken = null;
//		// JWT Token is in the form "Bearer token". Remove Bearer word and get
//		// only the Token
//		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
//			jwtToken = requestTokenHeader.substring(7);
//			try {
//				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
//			} catch (IllegalArgumentException e) {
//				System.out.println("Unable to get JWT Token");
//			} catch (ExpiredJwtException e) {
//				System.out.println("JWT Token has expired");
//			}
//		} else {
//			logger.warn("JWT Token does not begin with Bearer String");
//			//!Problem: when backend is called the first time, the httpclient will send a option method and the spring is returning erro (401). 
//			//	this is workaround
//			//this must be write and return from here.
//			if (!((request.getRequestURI().endsWith("authenticate") && request.getMethod().equals("POST"))
//					|| request.getRequestURI().contains("h2-console") 
//					|| request.getRequestURI().contains("swagger-ui")
//					|| request.getRequestURI().contains("/v3/"))){
//				response.addHeader("Access-Control-Allow-Origin", "*");
//			    response.addHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
//			    response.addHeader("Access-Control-Allow-Credentials", "true");
//			    response.addHeader("Access-Control-Max-Age", "1800");
//			    response.addHeader("Access-Control-Allow-Headers","authorization, Content-Type");
//			    response.addHeader("Allow", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
//			    return;
//			}
//		}
//		// Once we get the token validate it.
//		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//			HttpSession session = request.getSession(false);
//			//System.out.println("SecurityContextHolder.getContext().getAuthentication() == null");
//			if (session != null ) {
//				session.getAttributeNames().asIterator().forEachRemaining(attr -> { System.out.println("attr:" + attr);});
//			}
//			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
//			// if token is valid configure Spring Security to manually set
//			// authentication
//			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
//				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//						userDetails, null, userDetails.getAuthorities());
//				usernamePasswordAuthenticationToken
//						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//				// After setting the Authentication in the context, we specify
//				// that the current user is authenticated. So it passes the
//				// Spring Security Configurations successfully.
//				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//			}
//		}
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				new POCUser(1, "WE", "WE", new ArrayList<>()), null, new ArrayList<>());
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		chain.doFilter(request, response);
	}
}