package com.harsh.app.domain;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "phone", schema = "contactbook")
@AssociationOverrides({
    @AssociationOverride(name = "key.id", joinColumns = @JoinColumn(name = "id"))
})
public class PhoneEntity implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private PhoneEntityPK key;
	
	@Column(name = "type", nullable = true)
	private String type;

	public PhoneEntity() {

	}

	public PhoneEntityPK getKey() {
		return key;
	}

	public void setKey(PhoneEntityPK key) {
		this.key = key;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
