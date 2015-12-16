package com.shivablast.studyadda.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shivablast.studyadda.persistence.model.User;
import com.shivablast.studyadda.persistence.model.VerificationToken;

public interface VerificationTokenRepository extends
		JpaRepository<VerificationToken, Long> {

	VerificationToken findByToken(String token);

	VerificationToken findByUser(User user);

}
