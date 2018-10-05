package com.qa.vetPractice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.vetPractice.model.VetPracticeOwnerModel;

@Repository
public interface VetPracticeOwnerRepository extends JpaRepository<VetPracticeOwnerModel, Long> {
	
	Optional<VetPracticeOwnerModel> findByfirstName(String firstname);

}
