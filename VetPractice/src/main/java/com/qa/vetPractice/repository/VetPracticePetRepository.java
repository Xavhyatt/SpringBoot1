package com.qa.vetPractice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.vetPractice.model.VetPracticePetModel;

@Repository
public interface VetPracticePetRepository extends JpaRepository<VetPracticePetModel, Long> {

	Page<VetPracticePetModel> findByOwnerOwnerId(Long ownerId, Pageable pageable);


}
