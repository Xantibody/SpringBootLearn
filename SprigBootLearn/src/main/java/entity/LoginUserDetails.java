package entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// SpringSecurity内にあるUserDetailsインターフェースを継承して、ユーザーのクラスを作成する
public class LoginUserDetails implements UserDetails {
  private final LoginUser loginUser;
  private final Collection<? extends GrantedAuthority> authorities;

  public LoginUserDetails(LoginUser loginUser) {
    this.loginUser = loginUser;
    this.authorities =
        loginUser.roleList().stream().map(role -> new SimpleGrantedAuthority(role)).toList();
  }

  //	ログインユーザーインスタンス返却
  public LoginUser getLoginUser() {
    return loginUser;
  }

  //	パスワード返却（ハッシュ化済み）
  @Override
  public String getPassword() {
    return loginUser.password();
  }

  // ログインユーザー返却
  @Override
  public String getUsername() {
    return loginUser.email();
  }

  //	ロールコレクション（権限リスト？）の返却
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  //	アカウントがロックしているか
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  //	アカウントが期限切れか
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  //	ユーザーのパースワードが期限切れか
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  //	有効なユーザーか
  @Override
  public boolean isEnabled() {
    return true;
  }

  //	多分だけど本来は継承しているので特別処理がなければ実装しない。
  //	呼び出せば勝手に良しなにしてくれる？

  // https://www.docswell.com/s/MasatoshiTada/KGVY9K-spring-security-intro#p38
}
