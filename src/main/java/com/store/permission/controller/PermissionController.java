package com.store.permission.controller;

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

import com.store.permission.Permission;
import com.store.permission.converters.PermissionAssemblerDTO;
import com.store.permission.converters.PermissionDisassemblerInput;
import com.store.permission.dto.PermissionDTO;
import com.store.permission.input.PermissionInput;
import com.store.permission.repository.PermissionRepository;
import com.store.permission.service.PermissionService;

@RestController
@RequestMapping("/permissions")
public class PermissionController {
	
	@Autowired
	private PermissionRepository permissionRepository;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private PermissionAssemblerDTO permissionAssembler;
	
	@Autowired
	private PermissionDisassemblerInput permissionDisassembler;
	
	@GetMapping
	public List<PermissionDTO> listPermissions () {
		return permissionAssembler.toCollectionDTO(permissionRepository.findAll());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PermissionDTO add (@RequestBody @Valid PermissionInput permissionInput) {
		Permission permission = permissionDisassembler.toDomainObject(permissionInput);
		return permissionAssembler.toDTO(permissionService.save(permission));
	}
	
	@PutMapping("/{permissionId}")
	public PermissionDTO update (@PathVariable Long permissionId, @RequestBody @Valid PermissionInput permissionInput) {
		Permission permission = permissionService.find(permissionId);
		
		permissionDisassembler.copyToDomainObject(permissionInput, permission);
		
		return permissionAssembler.toDTO(permissionService.save(permission));
	}
	
	@DeleteMapping("/{permissionId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete (@PathVariable Long permissionId) {
		permissionService.delete(permissionId);
	}

}











