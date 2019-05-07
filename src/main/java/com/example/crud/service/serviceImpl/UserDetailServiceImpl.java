package com.example.crud.service.serviceImpl;

import static java.util.Collections.emptyList;

import com.example.crud.bean.ApplicationUser;
import com.example.crud.dao.UserDao;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	private UserDao userDao;
	public UserDetailServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("username is ===="+username);
		List<ApplicationUser> list = userDao.findByName(username);
		ApplicationUser user = list.get(0);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getUserpassword().trim());
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(user.getUsername(), encodedPassword, emptyList());
	}
}
