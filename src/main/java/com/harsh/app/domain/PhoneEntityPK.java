package com.harsh.app.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Embeddable
public class PhoneEntityPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(fetch = FetchType.EAGER)
	private ContactEntity id;
	
	@Column(name = "phone", nullable = false)
	private String phone;

	public PhoneEntityPK() {
		
	}

	public PhoneEntityPK(ContactEntity id, String phone) {
		this.id = id;
		this.phone = phone;
	}

	public ContactEntity getId() {
		return id;
	}

	public void setId(ContactEntity id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhoneEntityPK other = (PhoneEntityPK) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.getId().equals(other.id.getId()))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getId(), this.getPhone());
	}

}
