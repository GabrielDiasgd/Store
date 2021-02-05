package com.store.profile.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
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
import com.store.profile.repository.ProfileRepository;
import com.store.profile.service.ProfileService;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	
	@GetMapping
	public List<Profile> listProfiles (){
		return profileRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Profile add (@RequestBody Profile profile) {
		return profileService.save(profile);
	}
	
	@PutMapping("/{profileId}")
	public Profile update (@PathVariable Long profileId, @RequestBody Profile profile) {
		Profile currentProfile = profileService.find(profileId);
		
		BeanUtils.copyProperties(profile, currentProfile, "id", "dateCreation");
		
		return profileService.save(currentProfile);
	}
	
	@DeleteMapping("/{profileId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete (@PathVariable Long profileId) {
		profileService.delete(profileId);
	}

}
