package com.harsh.app.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ContactVO {

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("phones")
	private PhoneVO[] phones;
	
	@JsonProperty("phone_list")
	private List<PhoneVO> phonelist;
	
	@JsonProperty("description")
	private String description;

	public ContactVO() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PhoneVO[] getPhones() {
		return phones;
	}

	public void setPhones(PhoneVO[] phones) {
		this.phones = phones;
	}

	public List<PhoneVO> getPhonelist() {
		return phonelist;
	}

	public void setPhonelist(List<PhoneVO> phonelist) {
		this.phonelist = phonelist;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
