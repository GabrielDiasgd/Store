package com.store.profile.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.profile.Profile;
import com.store.profile.input.ProfileInput;

@Component
public class ProfileDisassemblerInput {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Profile toDomainObject (ProfileInput profileInput) {
		return modelMapper.map(profileInput, Profile.class);
	}
	
	public void copyToDomainObject (ProfileInput profileInput, Profile profile) {
		modelMapper.map(profileInput, profile);
	}

}
