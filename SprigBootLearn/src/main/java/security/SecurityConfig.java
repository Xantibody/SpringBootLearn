package security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityfilterchain (HttpSecurity http) 
			throws Exception{
		// ログイン設定
		http.formLogin(login -> login			//フォーム認証の設定変数をラムダ式で更新
				.loginProcessingUrl("/login")	//フォームのパラメータを送るURL
				.loginPage("/login")			//ログイン画面のURL
				.defaultSuccessUrl("/home")		//ログイン成功時の遷移URL
				.failureUrl("/login?error")		//ログイン失敗時の遷移URL
				.permitAll()		
		
		// ログアウト設定		
		).logout(logout -> logout
				.logoutSuccessUrl("/index")		//ログアウト成功時URL
		
		// ロール設定		
		).authorizeHttpRequests(authz -> authz
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations())
					.permitAll()				//拡張子がcssやjsの物は許可する
				.requestMatchers("/general")		
					.hasRole("GENERAL")			//GENERALのロールがアクセス可能
				.requestMatchers("/admin")
					.hasRole("ADMIN")			//ADMINのロールがアクセス可能
				.anyRequest().authenticated()	//他はログイン後のみ 
		);
		
		//securityの参考：https://youtu.be/uIR4Fqx3q7I?t=1038
		//mvcMatchersで記載していたが、6.0で非推奨になったため変更
		//permitAllの代わりに.ignoringが使われていたらしいが、Springが全く監視しなくなるため非推奨
		return http.build();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		// https://www.docswell.com/s/MasatoshiTada/KGVY9K-spring-security-intro#p35
	}
}
