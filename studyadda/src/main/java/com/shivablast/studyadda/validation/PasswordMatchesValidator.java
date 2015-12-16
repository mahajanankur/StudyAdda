package com.shivablast.studyadda.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.shivablast.studyadda.persistence.service.UserDto;

public class PasswordMatchesValidator implements
		ConstraintValidator<PasswordMatches, Object> {

	@Override
	public void initialize(final PasswordMatches constraintAnnotation) {
		//
	}

	@Override
	public boolean isValid(final Object obj,
			final ConstraintValidatorContext context) {
		final UserDto user = (UserDto) obj;
		return user.getPassword().equals(user.getMatchingPassword());
	}

}
