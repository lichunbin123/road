package com.example.crud.config;

import java.io.IOException;
import java.util.*;
import javax.servlet.FilterChain;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.crud.bean.ApplicationUser;
import com.example.crud.utils.JwtTokenUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.example.crud.config.SecurityConstants.EXPIRATION_TIME;
import static com.example.crud.config.SecurityConstants.HEADER_STRING;
import static com.example.crud.config.SecurityConstants.TOKEN_PREFIX;
import static com.example.crud.config.SecurityConstants.SECRET;
import static com.example.crud.config.SecurityConstants.ROLE_CLAIMS;
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	// 从输入流中获取到登录的信息

	public Authentication attemptAuthentication(HttpServletRequest req,
            HttpServletResponse res) throws AuthenticationException {
		try {
			ApplicationUser creds = new ObjectMapper().readValue(req.getInputStream(), ApplicationUser.class);
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							creds.getUsername(),
							creds.getPassword(),
							new ArrayList<>())
					);
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	// 成功验证后调用的方法
    // 如果验证成功，就生成token并返回
	@Override
	protected void successfulAuthentication(HttpServletRequest req,
											  HttpServletResponse res,
											  FilterChain chain,
											  Authentication auth) throws IOException {

//		String token = Jwts.builder()
//	                .setSubject(((User) auth.getPrincipal()).getUsername())
//	                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//	                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
//	                .compact();

		String role = " ";
		Collection<? extends GrantedAuthority> authorities = ((User) auth.getPrincipal()).getAuthorities();
		for (GrantedAuthority authority : authorities){
			role = authority.getAuthority();
		}
        String username = ((User) auth.getPrincipal()).getUsername();
		String token = JwtTokenUtils.createToken(username,role);

		res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
		ServletOutputStream resOut = res.getOutputStream();
		resOut.print(token);
	}


}
