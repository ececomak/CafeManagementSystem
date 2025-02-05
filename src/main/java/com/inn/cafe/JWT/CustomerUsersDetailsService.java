package com.inn.cafe.JWT;

import com.inn.cafe.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class CustomerUsersDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(CustomerUsersDetailsService.class);

    @Autowired
    private UserDao userDao;

    private com.inn.cafe.POJO.User userDetail;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Inside loadUserByUsername {}", username);
        userDetail = userDao.findByEmailId(username);
        if (userDetail != null) {
            String role = "ROLE_" + (userDetail.getRole() != null ? userDetail.getRole().toUpperCase() : "USER");
            return new User(userDetail.getEmail(),
                            userDetail.getPassword(),
                            Collections.singletonList(new SimpleGrantedAuthority(role)));
        } else {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
    }

    public com.inn.cafe.POJO.User getUserDetail() {
        if (userDetail != null) {
            return userDetail;
        }
        throw new IllegalStateException("User detail is not available. Make sure that the user is authenticated.");
    }
}