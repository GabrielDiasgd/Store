package com.store.permission.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.permission.Permission;
import com.store.permission.input.PermissionInput;

@Component
public class PermissionDisassemblerInput {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Permission toDomainObject (PermissionInput permissionInput) {
		return modelMapper.map(permissionInput, Permission.class);
	}
	
	public void copyToDomainObject (PermissionInput permissionInput, Permission permission) {
		modelMapper.map(permissionInput, permission);
	}

}
