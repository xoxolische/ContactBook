package com.test.pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.pl.service.ContactService;

@Controller
@RequestMapping(value = "/contact")
public class ContactController {
	@Autowired
	private ContactService contactService;
	
	@RequestMapping(value = "/{id}")
	public String show(@PathVariable long id, Model model) {
		model.addAttribute("c", contactService.get(id));
		return "showContact";
	}	
	
	@RequestMapping(value = "/edit/{id}")
	public String edit(@PathVariable long id, Model model) {
		model.addAttribute("c", contactService.get(id));
		model.addAttribute("edit", true);
		return "showContact";
	}	
}
