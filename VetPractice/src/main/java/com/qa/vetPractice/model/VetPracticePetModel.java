package com.qa.vetPractice.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pet")

public class VetPracticePetModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long petId;

	
	private Long speciesID;
	private String name;
	private String colour;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "OwnerId", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private VetPracticeOwnerModel owner;
	
	public VetPracticePetModel() {
		
	}

	public VetPracticePetModel(Long speciesID, String name, String colour) {
		this.speciesID = speciesID;
		this.name = name;
		this.colour = colour;
	}

	public Long getPetId() {
		return petId;
	}

	public void setPetId(Long petId) {
		this.petId = petId;
	}

	public Long getSpeciesID() {
		return speciesID;
	}

	public void setSpeciesID(Long speciesID) {
		this.speciesID = speciesID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}
	
	public VetPracticeOwnerModel getOwner() {
		return owner;
	}

	public void setOwner(VetPracticeOwnerModel owner) {
		this.owner= owner;
		
	}



	
	
}
