package com.shivablast.studyadda.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shivablast.studyadda.persistence.model.PasswordResetToken;
import com.shivablast.studyadda.persistence.model.User;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);

    PasswordResetToken findByUser(User user);

}
