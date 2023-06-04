package com.example.demo.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception {
    // ログイン設定
    http.formLogin(
            login ->
                login // フォーム認証の設定変数をラムダ式で更新
                    .loginProcessingUrl("/login") // フォームのパラメータを送るURL
                    .loginPage("/login") // ログイン画面のURL
                    .defaultSuccessUrl("/home") // ログイン成功時の遷移URL
                    .failureUrl("/login?error") // ログイン失敗時の遷移URL
                    .permitAll()

            // ログアウト設定
            )
        .logout(
            logout -> logout.logoutSuccessUrl("/") // ログアウト成功時URL

            // ロール設定
            )
        .authorizeHttpRequests(
            authz ->
                authz
                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                    .permitAll() // 拡張子がcssやjsの物は許可する
                    .requestMatchers("/")
                    .permitAll()
                    .requestMatchers("/general")
                    .hasRole("GENERAL") // GENERALのロールがアクセス可能
                    .requestMatchers("/admin")
                    .hasRole("ADMIN") // ADMINのロールがアクセス可能
                    .anyRequest()
                    .authenticated() // 他はログイン後のみ
            );

    // securityの参考：https://youtu.be/uIR4Fqx3q7I?t=1038
    // mvcMatchersで記載していたが、6.0で非推奨になったため変更
    // permitAllの代わりに.ignoringが使われていたらしいが、Springが全く監視しなくなるため非推奨
    // 結局説明では物を作るところまで説明しないため、別記も事参照
    // https://fintan.jp/wp-content/uploads/2023/03/jsug-springfest2023-sf_4.pdf
    // https://fintan.jp/page/7480/
    // 　うーんこれでもないので、最初の記事のgithubを参考にする
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
