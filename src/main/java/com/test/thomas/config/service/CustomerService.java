package com.test.thomas.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by thomas on 2018/3/2.
 */
@Service("customerService")
public class CustomerService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Collection auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority("MANAGER") );
        UserDetails userDetails = new User("thomas",new StandardPasswordEncoder("MD5").encode("12345"),auths);
        return userDetails;
    }
}