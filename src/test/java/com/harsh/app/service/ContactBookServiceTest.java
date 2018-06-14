package com.harsh.app.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.harsh.app.ContactBookAppApplicationTests;
import com.harsh.app.domain.ContactEntity;
import com.harsh.app.domain.PhoneEntity;
import com.harsh.app.repository.ContactRepository;
import com.harsh.app.repository.PhoneRepository;
import com.harsh.app.util.ContactBookUtil;
import com.harsh.app.vo.ContactVO;
import com.harsh.app.vo.PhoneVO;

public class ContactBookServiceTest extends ContactBookAppApplicationTests {

	@InjectMocks
	private ContactBookService contactBookService;
	
	@Mock
	private ContactRepository contactRepository;

	@Mock
	private PhoneRepository phoneRepository;
	
	private ContactVO contact;
	private PhoneVO phone;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		contact = new ContactVO();
		contact.setName("harsh");
		contact.setEmail("harsh@mathur.com");
		contact.setDescription("test contact");
		
		phone = new PhoneVO();
		phone.setPhone("12345");
		phone.setType("test phone");
		
		contact.setPhones(new PhoneVO[] {phone});
		contact.setPhonelist(Arrays.asList(phone));
	}
	
	@Test
	public void addNewContactTest() {
		Mockito.when(contactRepository.save(any(ContactEntity.class))).thenReturn(null);
		Mockito.when(phoneRepository.save(any(PhoneEntity.class))).thenReturn(null);
		contactBookService.addNewContact(contact);
	}
	
	@Test
	public void getAllContacts() {
		Mockito.when(contactRepository.findAll()).thenReturn(new ArrayList<>());
		List<ContactVO> contacts = contactBookService.getAllContacts();
		assertEquals(0, contacts.size());
	}
	
	@Test
	public void getContactsByNameTest() {
		Mockito.when(contactRepository.findByName(any(String.class))).thenReturn(Arrays.asList(ContactBookUtil.getEntityFromVO(contact)));
		List<ContactVO> contacts = contactBookService.getContactsByName("harsh");
		assertEquals(1, contacts.size());
	}
	
	@Test
	public void getContactsByEmailTest() {
		Mockito.when(contactRepository.findByEmail(any(String.class))).thenReturn(Arrays.asList(ContactBookUtil.getEntityFromVO(contact)));
		List<ContactVO> contacts = contactBookService.getContactsByEmail("harsh");
		assertEquals(1, contacts.size());
	}
	
	@Test
	public void updateContactTest() throws Exception {
		Mockito.when(contactRepository.findByEmail(any(String.class))).thenReturn(Arrays.asList(ContactBookUtil.getEntityFromVO(contact)));
		Mockito.when(contactRepository.save(any(ContactEntity.class))).thenReturn(null);
		Mockito.when(phoneRepository.save(any(PhoneEntity.class))).thenReturn(null);
		contactBookService.updateContact(contact);
	}
	
	@Test(expected = Exception.class)
	public void updateContactTestException() throws Exception {
		Mockito.when(contactRepository.findByEmail(any(String.class))).thenReturn(null);
		Mockito.when(contactRepository.save(any(ContactEntity.class))).thenReturn(null);
		Mockito.when(phoneRepository.save(any(PhoneEntity.class))).thenReturn(null);
		contactBookService.updateContact(contact);
	}
}
