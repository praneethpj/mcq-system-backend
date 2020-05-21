package com.mcq.webapp.services;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MainUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
        return new User("foo","foo",new ArrayList<>());
    }
}