package org.zerock.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomNoOpPasswordEncoder implements PasswordEncoder{

	@Override
	public String encode(CharSequence rawPassword) {
		//비밀번호를 인코딩하는 작업
		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		//비밀번호 일치 여부
		return rawPassword.toString().equals(encodedPassword);
	}

}
