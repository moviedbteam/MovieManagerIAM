package com.bcefit.security;

import com.bcefit.application.IUserLoginDataService;
import com.bcefit.domaine.UserLoginData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    IUserLoginDataService userLoginDataService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserLoginData userLoginData = userLoginDataService.findByEmail(email);

        List<GrantedAuthority> auth=new ArrayList<>();

        return new User(userLoginData.getEmail(), userLoginData.getPasswordHash(), auth);
    }
}
