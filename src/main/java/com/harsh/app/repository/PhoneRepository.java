package com.harsh.app.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harsh.app.domain.ContactEntity;
import com.harsh.app.domain.PhoneEntity;
import com.harsh.app.domain.PhoneEntityPK;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneEntity, PhoneEntityPK> {

	@Transactional
	void deleteByKeyId(ContactEntity id);
}
