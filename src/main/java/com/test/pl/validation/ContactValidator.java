package com.test.pl.validation;

import java.time.LocalDate;

import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.test.pl.model.Contact;
import com.test.pl.model.User;
import com.test.pl.service.ContactService;

@Component
public class ContactValidator implements Validator {

	@Autowired
	private ContactService contactService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
				"Email should not be null or empty! Please, set a correct value.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"Name should not be null or empty! Please, set a correct value.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname",
				"Surname should not be null or empty! Please, set a correct value.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone",
				"Phone should not be null or empty! Please, set a correct value.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthDate",
				"BirthDate should not be null or empty! Please, set a correct value.");

		Contact c = (Contact) target;

		if (c != null && c.getAuthor() != null && c.getBirthDate() != null && c.getEmail() != null
				&& c.getName() != null && c.getSurname() != null && c.getPhone() != null) {
			if (c.getEmail().length() > 255) {
				errors.rejectValue("email", "Email length must be less or equals 255 symbols.");
			}
			try {
				InternetAddress emailAddr = new InternetAddress(c.getEmail());
				emailAddr.validate();
			} catch (Exception e) {
				errors.rejectValue("email", "Email is invalid!");
			}

			if (c.getId() != 0) {
				Contact existing = contactService.getByEmail(c.getEmail());
				if (existing != null) {
					if (existing.getId() != c.getId()) {
						errors.rejectValue("email", "Contact with such email already exists!");
					}
				}
			} else {
				if (contactService.getByEmail(c.getEmail()) != null) {
					errors.rejectValue("email", "Contact with such email already exists!");
				}
			}

			if (c.getName().length() > 120) {
				errors.rejectValue("name", "Name length must be less or equals 120 symbols.");
			}

			if (c.getSurname().length() > 120) {
				errors.rejectValue("surname", "SurName length must be less or equals 120 symbols.");
			}

			if (c.getName().length() < 2) {
				errors.rejectValue("name", "Name is too short! Length must be more or equals to 2 symbols!");
			}

			if (c.getSurname().length() < 2) {
				errors.rejectValue("surname", "SurName is too short! Length must be more or equals to 2 symbols!");
			}

			String regex = "\\d+";

			if (!c.getPhone().matches(regex)) {
				errors.rejectValue("phone", "Phone must contain only digits!");
			}

			if (c.getPhone().length() > 15) {
				errors.rejectValue("phone", "Phone number is too big, it must be less or equals to 15 digits.");
			}

			if (c.getPhone().length() < 5) {
				errors.rejectValue("phone", "Phone number is too short, it must be more or equals to 5 digits.");
			}

			LocalDate l = LocalDate.parse(c.getBirthDate().toString());
			if (l.getYear() < 1900) {
				errors.rejectValue("birthDate", "Birth Year must be higher than 1900.");
			}
		}

	}

}
