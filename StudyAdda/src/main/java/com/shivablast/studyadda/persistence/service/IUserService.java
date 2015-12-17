package com.shivablast.studyadda.persistence.service;

import com.shivablast.studyadda.persistence.model.PasswordResetToken;
import com.shivablast.studyadda.persistence.model.User;
import com.shivablast.studyadda.persistence.model.VerificationToken;
import com.shivablast.studyadda.validation.EmailExistsException;

public interface IUserService {

	User registerNewUserAccount(UserDto accountDto) throws EmailExistsException;

	User getUser(String verificationToken);

	void saveRegisteredUser(User user);

	void deleteUser(User user);

	void createVerificationTokenForUser(User user, String token);

	VerificationToken getVerificationToken(String VerificationToken);

	VerificationToken generateNewVerificationToken(String token);

	void createPasswordResetTokenForUser(User user, String token);

	User findUserByEmail(String email);

	PasswordResetToken getPasswordResetToken(String token);

	User getUserByPasswordResetToken(String token);

	User getUserByID(long id);

	void changeUserPassword(User user, String password);

	boolean checkIfValidOldPassword(User user, String password);

}
