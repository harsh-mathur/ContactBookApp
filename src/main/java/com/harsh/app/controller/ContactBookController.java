package com.harsh.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.app.service.ContactBookService;
import com.harsh.app.util.ContactBookUtil;
import com.harsh.app.vo.ContactVO;
import com.harsh.app.vo.Message;

@RestController
@RequestMapping(value = "/")
public class ContactBookController {

	@Autowired
	private ContactBookService contactBookService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Message> addNewContact(
			@RequestBody(required = true) ContactVO request) {
		
		Message message = null;
		try {
			ContactBookUtil.validateParams(request);
			
			contactBookService.addNewContact(request);
			
			message = new Message("CONTACT CREATED");
			return new ResponseEntity<Message>(message, HttpStatus.CREATED);
		} catch (Exception e) {
			message = new Message("ERROR OCCURED" + e.getMessage());
			return new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "getAllContacts")
	public ResponseEntity<Object> getAllContacts() {
		Message message = null;
		try {
			List<ContactVO> contacts = contactBookService.getAllContacts();
			
			if(contacts.isEmpty()) {
				message = new Message("NO CONTACTS");
				return new ResponseEntity<Object>(message, HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<Object>(contacts, HttpStatus.OK);
		} catch (Exception e) {
			message = new Message("ERROR OCCURED" + e.getMessage());
			return new ResponseEntity<Object>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "getContactsByName")
	public ResponseEntity<Object> getContactsByName(
			@RequestParam(required = true) String name) {
		Message message = null;
		try {
			List<ContactVO> contacts = contactBookService.getContactsByName(name);
			
			if(contacts.isEmpty()) {
				message = new Message("NO CONTACTS");
				return new ResponseEntity<Object>(message, HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<Object>(contacts, HttpStatus.OK);
		} catch (Exception e) {
			message = new Message("ERROR OCCURED" + e.getMessage());
			return new ResponseEntity<Object>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "getContactsByEmail")
	public ResponseEntity<Object> getContactsByEmail(
			@RequestParam(required = true) String email) {
		Message message = null;
		try {
			List<ContactVO> contacts = contactBookService.getContactsByEmail(email);
			
			if(contacts.isEmpty()) {
				message = new Message("NO CONTACTS");
				return new ResponseEntity<Object>(message, HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<Object>(contacts, HttpStatus.OK);
		} catch (Exception e) {
			message = new Message("ERROR OCCURED" + e.getMessage());
			return new ResponseEntity<Object>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Message> deleteContact(
			@RequestParam(required = true) String name) {
		Message message = null;
		try {
			contactBookService.deleteContact(name);
			
			message = new Message("CONTACT DELETED");
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		} catch (Exception e) {
			message = new Message("ERROR OCCURED" + e.getMessage());
			return new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Message> updateContact(
			@RequestBody(required = true) ContactVO request) {
		
		Message message = null;
		try {
			ContactBookUtil.validateParams(request);
			
			contactBookService.updateContact(request);
			
			message = new Message("CONTACT UPDATED");
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		} catch (Exception e) {
			message = new Message("ERROR OCCURED" + e.getMessage());
			return new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
