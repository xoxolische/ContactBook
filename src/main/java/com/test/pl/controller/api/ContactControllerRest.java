package com.test.pl.controller.api;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.pl.model.Contact;
import com.test.pl.model.UserAuth;
import com.test.pl.service.ContactService;
import com.test.pl.validation.ContactValidator;

@RestController
@RequestMapping(value = "/api/contact")
public class ContactControllerRest {

	@Autowired
	private ContactService contactService;

	@Autowired
	private ContactValidator contactValidator;

	@PostMapping(value = "/create", produces = "application/json")
	public ResponseEntity<?> create(@RequestBody Contact c, BindingResult result, HttpServletRequest request) {
		contactValidator.validate(c, result);
		if (result.hasErrors()) {
			List<String> errorList = new LinkedList<>();
			for (ObjectError e : result.getAllErrors()) {
				errorList.add(e.getCode());
			}
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorList);
		} else {
			contactService.create(c);
			return new ResponseEntity<Contact>(c, HttpStatus.OK);
		}
	}

	@PostMapping(value = "/update/{id}", produces = "application/json")
	public ResponseEntity<?> update(@PathVariable long id, @RequestBody Contact c, BindingResult result,
			HttpServletRequest request, Authentication auth) {
		UserAuth currentUser = (UserAuth) auth.getPrincipal();
		Contact updatebleContact = contactService.get(id);
		if (updatebleContact.getAuthor().getId() == currentUser.getId()) {
			contactValidator.validate(c, result);
			if (result.hasErrors()) {
				List<String> errorList = new LinkedList<>();
				for (ObjectError e : result.getAllErrors()) {
					errorList.add(e.getCode());
				}
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorList);
			} else {
				contactService.update(c);
				return new ResponseEntity<Contact>(c, HttpStatus.OK);
			}
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied!");
		}
	}

	@GetMapping(value = "/getMore/{lastDate}", produces = "application/json")
	public List<Contact> getAll(@PathVariable long lastDate) {
		if (lastDate == 0) {
			lastDate = Long.MAX_VALUE;
		}
		return contactService.getMore(lastDate, 5);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable long id, Authentication auth) {
		if (auth != null) {
			UserAuth currentUser = (UserAuth) auth.getPrincipal();
			Contact dbContact = contactService.get(id);

			if (dbContact.getAuthor().getId() == currentUser.getId()) {
				contactService.delete(id);
				return new ResponseEntity<Contact>(dbContact, HttpStatus.OK);
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied!");
			}
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied!");
		}
	}
}
