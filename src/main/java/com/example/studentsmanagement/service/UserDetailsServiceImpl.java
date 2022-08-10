package com.example.studentsmanagement.service;

import com.example.studentsmanagement.entity.User;
import com.example.studentsmanagement.models.MyAccountDetail;
import com.example.studentsmanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.getAccountByName(username);
        if (user == null){
            throw new RuntimeException("Could not found account");
        }
        return new MyAccountDetail(user);
    }
}
