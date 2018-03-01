package com.test.pl.validation;

import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.test.pl.model.User;
import com.test.pl.service.UserService;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
				"Email should not be null or empty! Please, set a correct value.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nickName",
				"nickName should not be null or empty! Please, set a correct value.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
				"Password should not be null or empty! Please, set a correct value.");

		User u = (User) target;

		if (u != null && u.getPassword() != null && u.getEmail() != null && u.getNickName() != null) {
			if (u.getEmail().length() > 255) {
				errors.rejectValue("email", "Email length must be less or equals 255 symbols.");
			}
			try {
				InternetAddress emailAddr = new InternetAddress(u.getEmail());
				emailAddr.validate();
			} catch (Exception e) {
				errors.rejectValue("email", "Email is invalid!");
			}
			if (userService.getByEmail(u.getEmail()) != null) {
				errors.rejectValue("email", "User with such email already exists!");
			}

			if (u.getPassword().length() > 255) {
				errors.rejectValue("password", "Password length must be less or equals 255 symbols.");
			}

			if (u.getPassword().length() < 8) {
				errors.rejectValue("password", "Password is too short! Length must be more or equals to 8 symbols.");
			}

			if (u.getNickName().length() > 20) {
				errors.rejectValue("nickName", "nickName length must be less or equals 255 symbols.");
			}

			if (u.getNickName().length() < 3) {
				errors.rejectValue("nickName", "NickName is too short! Length must be more or equals to 3 symbols!");
			}
		}

	}

}
