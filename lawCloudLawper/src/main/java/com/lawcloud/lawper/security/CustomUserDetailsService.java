package com.lawcloud.lawper.security;

import com.lawcloud.lawper.dao.UsersMapper;
import com.lawcloud.lawper.model.GroupAuthorities;
import com.lawcloud.lawper.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private UserCache userCache;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = (User) userCache.getUserFromCache(username);
        if (user == null) {

            Users user_db = usersMapper.getByUsername(username);
            logger.info("User : {}", user_db);
            if (user_db == null) {
                logger.info("User not found");
                throw new UsernameNotFoundException("Username not found");
            }
            user = new User(user_db.getUsername(), user_db.getPassword(),
                    user_db.getEnabled(), true, true, true, getGrantedAuthorities(user_db));
        }
        logger.info("*********************" + username + "***********************");
        logger.info(user.getAuthorities().toString());
        logger.info("********************************************************");

        this.userCache.putUserInCache(user);

        return user;

        /*Users user_db = usersMapper.getByUsername(username);
        logger.info("User : {}", user_db);
        if (user_db == null) {
            logger.info("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        User user = new User(user_db.getUsername(), user_db.getPassword(),
                    user_db.getEnabled(), true, true, true, getGrantedAuthorities(user_db));

        return user;*/
    }

    private List<GrantedAuthority> getGrantedAuthorities(Users user) {

        List<GroupAuthorities> groupAuthorities = usersMapper.getGroupAuthoritiesByUsername(user.getUsername());

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (GroupAuthorities authority : groupAuthorities) {
            logger.info("GroupAuthorities : {}", authority);
            authorities.add(new SimpleGrantedAuthority("ROLE_" + authority.getAuthority()));
        }
        logger.info("authorities : {}", authorities);
        return authorities;
    }

}