package com.lawcloud.lawper.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawcloud.lawper.dao.UsersMapper;
import com.lawcloud.lawper.model.GroupAuthorities;
import com.lawcloud.lawper.model.Users;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
	private UsersMapper usersMapper;
	
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = usersMapper.getByUsername(username);
		logger.info("User : {}", user);
		if(user==null){
			logger.info("User not found");
			throw new UsernameNotFoundException("Username not found");
		}

		return new User(user.getUsername(), user.getPassword(),
				 user.getEnabled(), true, true, true, getGrantedAuthorities(user));
	}

	private List<GrantedAuthority> getGrantedAuthorities(Users user){

		List<GroupAuthorities> groupAuthorities = usersMapper.getGroupAuthoritiesByUsername(user.getUsername());

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(GroupAuthorities authority : groupAuthorities){
			logger.info("GroupAuthorities : {}", authority);
			authorities.add(new SimpleGrantedAuthority("ROLE_"+authority.getAuthority()));
		}
		logger.info("authorities : {}", authorities);
		return authorities;
	}
	
}