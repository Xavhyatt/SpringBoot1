package com.qa.vetPractice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.vetPractice.exception.ResourceNotFoundException;
import com.qa.vetPractice.model.VetPracticeInjuryModel;
import com.qa.vetPractice.model.VetPracticeOwnerModel;
import com.qa.vetPractice.repository.VetPracticeInjuryRepository;

@RestController
@RequestMapping("/api")
public class VetPracticeInjuryController {

	
	
	@Autowired
	private VetPracticeInjuryRepository injuryRepository;
	
	@GetMapping("owner/{Id}")
	public VetPracticeInjuryModel getInjurybyID(@PathVariable(value ="Id") Long injuryID) {
		return injuryRepository.findById(injuryID).orElseThrow(()-> new ResourceNotFoundException("VetPracticeOwnerModel", "id", injuryID));
	}
}
