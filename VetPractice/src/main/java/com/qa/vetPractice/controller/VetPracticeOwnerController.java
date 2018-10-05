package com.qa.vetPractice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.qa.vetPractice.model.VetPracticeOwnerModel;
import com.qa.vetPractice.repository.VetPracticeOwnerRepository;

@RestController
@RequestMapping("/api")
public class VetPracticeOwnerController {

	@Autowired
	VetPracticeOwnerRepository myRepository;
	
	// Method to create a owner
	@PostMapping("/owner")
	public VetPracticeOwnerModel createOwner(@Valid @RequestBody VetPracticeOwnerModel mSDM) {
		return myRepository.save(mSDM);
	}
	
	//Method to get a owner
	@GetMapping("owner/{Id}")
	public VetPracticeOwnerModel getOwnerbyID(@PathVariable(value ="Id") Long ownerID) {
		return myRepository.findById(ownerID).orElseThrow(()-> new ResourceNotFoundException("VetPracticeOwnerModel", "id", ownerID));
	}
	
	//Method to get all owners
	@GetMapping("/owner")
	public List<VetPracticeOwnerModel> getAllOwners(){
		return myRepository.findAll();		
	}
	
	//Method to update a owner
	@PutMapping("/person/{Id}")
	public VetPracticeOwnerModel updateOwner(@PathVariable(value = "Id") Long ownerID,
			@Valid @RequestBody VetPracticeOwnerModel ownerDetails) {
		
		VetPracticeOwnerModel mSDM = myRepository.findById(ownerID).orElseThrow(()-> new ResourceNotFoundException("Person", "id", ownerID));
		
		mSDM.setFirstName(ownerDetails.getFirstName());
		mSDM.setSecondName(ownerDetails.getSecondName());
		mSDM.setAddress(ownerDetails.getAddress());
		mSDM.setCity(ownerDetails.getCity());
		mSDM.setNumber(ownerDetails.getNumber());
			
		VetPracticeOwnerModel updateData = myRepository.save(mSDM);
		return updateData;
	}
	
	//Method to remove a owner
	@DeleteMapping("/person/{Id}")
	public ResponseEntity<?> deleteOwner(@PathVariable(value = "Id")Long ownerID){
		VetPracticeOwnerModel mSDM = myRepository.findById(ownerID).orElseThrow(()-> new ResourceNotFoundException("Person", "id", ownerID));
			
		myRepository.delete(mSDM);
		return ResponseEntity.ok().build();
		}
		
	
		
		
	
}
