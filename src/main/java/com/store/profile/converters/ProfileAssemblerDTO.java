package com.store.profile.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.profile.Profile;
import com.store.profile.dto.ProfileDTO;

@Component
public class ProfileAssemblerDTO {

	@Autowired
	private ModelMapper modelMapper;
	
	public ProfileDTO toDTO (Profile profile) {
		return modelMapper.map(profile, ProfileDTO.class);
	}
	
	public List<ProfileDTO> toCollectionDTO (List<Profile> profiles) {
		return profiles.stream()
				.map(profile -> toDTO(profile))
				.collect(Collectors.toList());
	}
}
