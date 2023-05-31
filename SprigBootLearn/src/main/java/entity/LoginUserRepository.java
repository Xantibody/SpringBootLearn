package entity;

import java.util.Optional;
import entity.LoginUser;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class LoginUserRepository{
	// 仮作成
	public Optional<LoginUser> findByEmail(String Email) {
		List<String> list = new ArrayList<String>(Arrays.asList("Apple", "Orange", "Melon"));
		return Optional.ofNullable(new LoginUser("email","name","password", list));
		
	}
}