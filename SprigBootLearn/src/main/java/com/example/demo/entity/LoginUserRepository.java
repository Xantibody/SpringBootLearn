package com.example.demo.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class LoginUserRepository {
  // 仮作成
  public Optional<LoginUser> findByEmail(String Email) {
    List<String> list = new ArrayList<String>(Arrays.asList("ADMIN"));
    return Optional.ofNullable(new LoginUser("email", "name", "password", list));
  }
}
