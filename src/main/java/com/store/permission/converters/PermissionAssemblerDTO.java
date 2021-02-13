package com.store.permission.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.permission.Permission;
import com.store.permission.dto.PermissionDTO;

@Component
public class PermissionAssemblerDTO {
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public PermissionDTO toDTO (Permission permission) {
		return modelMapper.map(permission, PermissionDTO.class);
	}
	
	
	public List<PermissionDTO> toCollectionDTO (List<Permission> permissions) {
		return permissions.stream()
				.map(permission -> toDTO(permission))
				.collect(Collectors.toList());
	}

}
