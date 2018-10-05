package com.qa.vetPractice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.vetPractice.exception.ResourceNotFoundException;
import com.qa.vetPractice.model.VetPracticePetModel;
import com.qa.vetPractice.repository.VetPracticeOwnerRepository;
import com.qa.vetPractice.repository.VetPracticePetRepository;

@RestController
@RequestMapping("/api")
public class VetPracticePetController {

	@Autowired
	private VetPracticePetRepository petRepository;
	
	@Autowired
	private VetPracticeOwnerRepository ownerRepository;
	
	@GetMapping("/owner/{ownerId}/pet")
	public Page<VetPracticePetModel> getAllPetsByOwnerId(@PathVariable (value = "ownerId") Long ownerId, Pageable pageable){
		return petRepository.findByOwnerOwnerId(ownerId, pageable);
	}

	@PostMapping("/owner/{ownerId}/pet")
	public VetPracticePetModel createPet(@PathVariable (value = "ownerId") Long ownerId, @Valid @RequestBody VetPracticePetModel pet) {
		return ownerRepository.findById(ownerId).map(VetPracticeOwnerModel -> {
			pet.setOwner(VetPracticeOwnerModel);
			return petRepository.save(pet);
		}).orElseThrow(() -> new ResourceNotFoundException("petId", "id", pet));
		}
	
	@PutMapping("/owner/{ownerId}/pet/{petId}")
	public VetPracticePetModel updatePet(@PathVariable (value = "ownerId") Long ownerId,
			@PathVariable (value = "petId") Long petId, @Valid @RequestBody VetPracticePetModel petRequest) {
		if(!ownerRepository.existsById(ownerId)) {
			throw new ResourceNotFoundException("Owner ", "id", petRequest);
		}
		return petRepository.findById(petId).map(pet -> {
			pet.setName(petRequest.getName());
			pet.setColour(petRequest.getColour());
			pet.setSpeciesID(petRequest.getSpeciesID());
			return petRepository.save(pet);
		}).orElseThrow(() -> new ResourceNotFoundException("petId", "id", petRequest));
	}
	
	@DeleteMapping("/owner/{ownerId}/pet/{petId}")
	public ResponseEntity<?> deletePet(@PathVariable (value = "personId") Long ownerId,
			@PathVariable (value = "petId") Long petId) {
		if(!petRepository.existsById(ownerId)) {
			throw new ResourceNotFoundException("Owner", "id", ownerId);
		}
		return petRepository.findById(petId).map(pet -> {
			petRepository.delete(pet);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Pet Id", petId.toString(), null));
	}
	
}

	
