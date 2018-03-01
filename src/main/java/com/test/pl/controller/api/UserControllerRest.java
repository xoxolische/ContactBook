package com.test.pl.controller.api;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.pl.model.User;
import com.test.pl.service.UserService;
import com.test.pl.validation.UserValidator;

@RestController
@RequestMapping(value = "/api/user")
public class UserControllerRest {

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;

	@PostMapping(value = "/register", produces = "application/json")
	public ResponseEntity<?> create(@RequestBody User user, BindingResult result, HttpServletRequest request) {
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			List<String> errorList = new LinkedList<>();
			for (ObjectError e : result.getAllErrors()) {
				errorList.add(e.getCode());
			}
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorList);
		} else {
			userService.create(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
	}
}
