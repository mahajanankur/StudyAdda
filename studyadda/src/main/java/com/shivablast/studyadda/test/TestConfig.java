package com.shivablast.studyadda.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.shivablast.studyadda.spring.PersistenceJPAConfig;

@ComponentScan({ "com.shivablast.studyadda.persistence.dao" })
public class TestConfig extends PersistenceJPAConfig {

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
	}

}
