package com.example.demo.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LoginUser;
import com.example.demo.entity.LoginUserDetails;
import com.example.demo.entity.LoginUserRepository;

@Service
public class LoginUserDetailsService implements UserDetailsService {
  private final LoginUserRepository repo;

  public LoginUserDetailsService(LoginUserRepository repo) {
    this.repo = repo;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    repo.findByEmail(email);
    Optional<LoginUser> userOp = repo.findByEmail(email);
    return userOp
        .map(user -> new LoginUserDetails(user))
        .orElseThrow(() -> new UsernameNotFoundException("not found"));
  }
}
