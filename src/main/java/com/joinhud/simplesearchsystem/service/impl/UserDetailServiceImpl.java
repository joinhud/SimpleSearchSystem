package com.joinhud.simplesearchsystem.service.impl;


import com.joinhud.simplesearchsystem.entity.User;
import com.joinhud.simplesearchsystem.entity.UserRoleEnum;
import com.joinhud.simplesearchsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userService.getByName(s);

        if(user != null) {

            Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
            roles.add(new SimpleGrantedAuthority(UserRoleEnum.USER.name()));

            UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                    user.getName(),
                    user.getPassword(),
                    roles
            );

            return userDetails;
        } else {
            return null;
        }

    }
}
