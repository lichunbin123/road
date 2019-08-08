package com.example.crud.service.serviceImpl;

import com.example.crud.bean.ApplicationUser;
import com.example.crud.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	private UserDao userDao;
	@Autowired
	public UserDetailServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<ApplicationUser> list = userDao.findByName(username);
		if (list == null){
			System.out.println("用户名不存在");
		}
		ApplicationUser user = list.get(0);
//		List<GrantedAuthority> authorities = new ArrayList<>();//用于添加用户权限，将用户权限添加到authorities中
//
//		authorities.add(new SimpleGrantedAuthority(user.getRole()));

        Collection<? extends GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword().trim());
		if (user == null) {
			System.out.println("用户名不存在");
			throw new UsernameNotFoundException("用户名不存在");
		}

		return new User(user.getUsername(), encodedPassword, authorities);


	}

}
