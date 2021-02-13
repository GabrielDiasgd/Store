package com.store.user.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.profile.Profile;
import com.store.user.input.UserInput;
import com.store.user.model.User;

@Component
public class UserDisassemblerInput {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public User toDomainObject (UserInput userInput) {
		return modelMapper.map(userInput, User.class);
	}
	
	public void copyToDomainObject (UserInput userInput, User user) {
		user.setProfile(new Profile());
		modelMapper.map(userInput, user);
	}

}
