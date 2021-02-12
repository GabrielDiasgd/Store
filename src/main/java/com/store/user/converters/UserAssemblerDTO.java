package com.store.user.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.user.dto.UserDTO;
import com.store.user.model.User;

@Component
public class UserAssemblerDTO {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public UserDTO toDTO (User user) {
		return modelMapper.map(user, UserDTO.class);
	}
	
	public List<UserDTO> toCollectionDTO (List<User> users) {
		return users.stream()
				.map(user -> toDTO(user))
				.collect(Collectors.toList());
	}

}
