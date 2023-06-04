package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class LoginUserRepository {
  // 仮作成
  @Autowired PasswordEncoder passwordEncoder;

  // DBにROLEを保存するときに命名注意
  // hasRoleで読み込む際に命名規則として"ROLE_" + "ROLE名"で判断される
  // そのためDBの値がADMINだけのRole名だとhasRoleに認識されない
  // http://tono-n-chi.com/blog/2014/05/spring-security-role/
  public Optional<LoginUser> findByEmail(String email) {
    List<String> list = new ArrayList<String>();
    switch (email) {
      case "admin":
        list.add("ROLE_ADMIN");
        return Optional.ofNullable(
            new LoginUser("admin", "admin", passwordEncoder.encode("admin"), list));

      case "general":
        list.add("ROLE_GENERAL");
        return Optional.ofNullable(
            new LoginUser("general", "general", passwordEncoder.encode("general"), list));
        // ROLE名空文字だと怒られた
      case "user":
        list.add("USER");
        return Optional.ofNullable(
            new LoginUser("user", "user", passwordEncoder.encode("user"), list));

      default:
        return null;
    }
  }
}
