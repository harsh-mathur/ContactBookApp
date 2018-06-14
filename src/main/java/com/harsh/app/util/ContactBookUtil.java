package com.harsh.app.util;

import java.util.ArrayList;

import com.harsh.app.domain.ContactEntity;
import com.harsh.app.domain.PhoneEntity;
import com.harsh.app.domain.PhoneEntityPK;
import com.harsh.app.vo.ContactVO;
import com.harsh.app.vo.PhoneVO;

public class ContactBookUtil {

	public static void validateParams(ContactVO contact) throws Exception {
		if(contact == null) {
			throw new Exception("No data provided");
		}
		
		if(contact.getName() == null || contact.getName().isEmpty()) {
			throw new Exception("No name provided");
		}
		
		if(contact.getEmail() == null || contact.getEmail().isEmpty()) {
			throw new Exception("No email provided");
		}
	}
	
	public static ContactVO getVOfromEntity(ContactEntity contactDB) {
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
	
	public static ContactEntity getEntityFromVO(ContactVO contact) {
		ContactEntity entity = new ContactEntity();
		entity.setName(contact.getName());
		entity.setEmail(contact.getEmail());
		entity.setDescription(contact.getDescription());
		entity.setPhones(new ArrayList<>());
		for(PhoneVO phoneRequest : contact.getPhones()) {
			PhoneEntity phone = new PhoneEntity();
			phone.setKey(new PhoneEntityPK(entity, phoneRequest.getPhone()));
			phone.setType(phoneRequest.getType());
			entity.getPhones().add(phone);
		}
		
		return entity;
	}
}
