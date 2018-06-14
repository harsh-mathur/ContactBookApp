package com.harsh.app.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harsh.app.domain.ContactEntity;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {

	List<ContactEntity> findByName(String name);
	
	List<ContactEntity> findByEmail(String email);
	
	@Transactional
	void deleteByName(String name);
}
