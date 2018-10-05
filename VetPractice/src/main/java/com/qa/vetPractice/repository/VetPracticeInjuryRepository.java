package com.qa.vetPractice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.qa.vetPractice.model.VetPracticeInjuryModel;

@Repository
public interface VetPracticeInjuryRepository {

	Page<VetPracticeInjuryModel> findByInjuryId(Long ownerId, Pageable pageable);

	
}
