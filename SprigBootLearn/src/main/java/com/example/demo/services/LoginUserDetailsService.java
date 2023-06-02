package services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import entity.LoginUser;
import entity.LoginUserDetails;
import entity.LoginUserRepository;

@Service
public class LoginUserDetailsService implements UserDetailsService{
	private final LoginUserRepository repo;
	public LoginUserDetailsService (LoginUserRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) 
		throws UsernameNotFoundException{
		repo.findByEmail(email);
		Optional<LoginUser> userOp = repo.findByEmail(email);
		return userOp.map(user -> new LoginUserDetails(user))
		.orElseThrow(() -> new UsernameNotFoundException("not found"));
	}
}
//実装準備終了？？
//https://www.docswell.com/s/MasatoshiTada/KGVY9K-spring-security-intro#p44