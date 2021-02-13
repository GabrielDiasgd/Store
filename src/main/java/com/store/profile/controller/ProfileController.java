package com.store.profile.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.store.profile.Profile;
import com.store.profile.converters.ProfileAssemblerDTO;
import com.store.profile.converters.ProfileDisassemblerInput;
import com.store.profile.dto.ProfileDTO;
import com.store.profile.input.ProfileInput;
import com.store.profile.repository.ProfileRepository;
import com.store.profile.service.ProfileService;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private ProfileAssemblerDTO profileAssembler;
	
	@Autowired
	private ProfileDisassemblerInput profileDisassembler;
	
	
	@GetMapping
	public List<ProfileDTO> listProfiles (){
		return profileAssembler.toCollectionDTO(profileRepository.findAll());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProfileDTO add (@RequestBody @Valid ProfileInput profileInput) {
		Profile profile = profileDisassembler.toDomainObject(profileInput);
		return profileAssembler.toDTO(profileService.save(profile));
	}
	
	@PutMapping("/{profileId}")
	public ProfileDTO update (@PathVariable Long profileId, @RequestBody @Valid ProfileInput profileInput) {
		Profile profile = profileService.find(profileId);
		
		profileDisassembler.copyToDomainObject(profileInput, profile);
		
		return profileAssembler.toDTO(profileService.save(profile));
	}
	
	@DeleteMapping("/{profileId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete (@PathVariable Long profileId) {
		profileService.delete(profileId);
	}

}
