package com.harsh.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harsh.app.domain.ContactEntity;
import com.harsh.app.domain.PhoneEntity;
import com.harsh.app.domain.PhoneEntityPK;
import com.harsh.app.repository.ContactRepository;
import com.harsh.app.repository.PhoneRepository;
import com.harsh.app.vo.ContactVO;
import com.harsh.app.vo.PhoneVO;

@Service
public class ContactBookService {

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private PhoneRepository phoneRepository;

	@Transactional
	public void addNewContact(ContactVO request) {
		ContactEntity contact = new ContactEntity();
		contact.setName(request.getName());
		contact.setEmail(request.getEmail());
		contact.setDescription(request.getDescription());
		ContactEntity contactDB = contactRepository.save(contact);

		for(PhoneVO phoneRequest : request.getPhones()) {
			PhoneEntity phone = new PhoneEntity();
			phone.setKey(new PhoneEntityPK(contactDB, phoneRequest.getPhone()));
			phone.setType(phoneRequest.getType());
			phoneRepository.save(phone);
		}
	}

	public List<ContactVO> getAllContacts() {
		List<ContactEntity> contactsDB = contactRepository.findAll();
		List<ContactVO> contacts = new ArrayList<>();

		for(ContactEntity contactDB : contactsDB) {
			contacts.add(getVOfromEntity(contactDB));
		}

		return contacts;
	}

	private ContactVO getVOfromEntity(ContactEntity contactDB) {
		ContactVO contact = new ContactVO();
		contact.setName(contactDB.getName());
		contact.setEmail(contactDB.getEmail());
		contact.setDescription(contactDB.getDescription());

		for(PhoneEntity phoneDB : contactDB.getPhones()) {
			PhoneVO phone = new PhoneVO();
			phone.setPhone(phoneDB.getKey().getPhone());
			phone.setType(phoneDB.getType());
			if(contact.getPhonelist() == null) {
				contact.setPhonelist(new ArrayList<>());
			}
			contact.getPhonelist().add(phone);
		}

		return contact;
	}

	public List<ContactVO> getContactsByName(String name) {
		List<ContactEntity> contactsDB = contactRepository.findByName(name);
		List<ContactVO> contacts = new ArrayList<>();

		for(ContactEntity contactDB : contactsDB) {
			contacts.add(getVOfromEntity(contactDB));
		}

		return contacts;
	}
	
	public List<ContactVO> getContactsByEmail(String email) {
		List<ContactEntity> contactsDB = contactRepository.findByEmail(email);
		List<ContactVO> contacts = new ArrayList<>();

		for(ContactEntity contactDB : contactsDB) {
			contacts.add(getVOfromEntity(contactDB));
		}

		return contacts;
	}
	
	public void deleteContact(String name) {
		contactRepository.deleteByName(name);
	}
	
	public void updateContact(ContactVO request) throws Exception {
		ContactEntity contact = contactRepository.findByEmail(request.getEmail()).get(0);
		
		if(contact == null) {
			throw new Exception("Contact does not exist");
		}
		
		contact.setName(request.getName());
		contact.setEmail(request.getEmail());
		contact.setDescription(request.getDescription());
		contactRepository.save(contact);

		phoneRepository.deleteByKeyId(contact);
		for(PhoneVO phoneRequest : request.getPhones()) {
			PhoneEntity phone = new PhoneEntity();
			phone.setKey(new PhoneEntityPK(contact, phoneRequest.getPhone()));
			phone.setType(phoneRequest.getType());
			phoneRepository.save(phone);
		}
	}
}
