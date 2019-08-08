package com.example.crud.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.crud.utils.JwtTokenUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static com.example.crud.config.SecurityConstants.*;


public class JWTAuthorizationFIlter extends BasicAuthenticationFilter{

	public JWTAuthorizationFIlter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req,
									  HttpServletResponse res,
									  FilterChain chain) throws IOException, ServletException {
		String header = req.getHeader(HEADER_STRING);
		// 如果请求头中没有Authorization信息则直接放行了
		if(header == null || !header.startsWith(TOKEN_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}
        // 如果请求头中有token，则进行解析，并且设置认证信息
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
		String token = req.getHeader(HEADER_STRING);
		if (token != null) {
//			String user = Jwts.parser()
//					.setSigningKey(SECRET.getBytes())
//					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
//					.getBody()
//					.getSubject();
			String user = JwtTokenUtils.getUsername(token);
			String role = JwtTokenUtils.getUserRole(token);
			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, Collections.singleton(new SimpleGrantedAuthority(role)));
			}
			return null;
		}

		return null;
	}

}
