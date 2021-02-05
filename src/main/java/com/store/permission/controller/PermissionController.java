package com.store.permission.controller;

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

import com.store.permission.Permission;
import com.store.permission.repository.PermissionRepository;
import com.store.permission.service.PermissionService;

@RestController
@RequestMapping("/permissions")
public class PermissionController {
	
	@Autowired
	private PermissionRepository permissionRepository;
	
	@Autowired
	private PermissionService permissionService;
	
	@GetMapping
	public List<Permission> listPermissions () {
		return permissionRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Permission add (@RequestBody Permission permission) {
		return permissionService.save(permission);
	}
	
	@PutMapping("/{permissionId}")
	public Permission update (@PathVariable Long permissionId, @RequestBody Permission permission) {
		Permission currentPermission = permissionService.find(permissionId);
		
		BeanUtils.copyProperties(permission, currentPermission);
		
		return permissionService.save(currentPermission);
	}
	
	@DeleteMapping("/{permissionId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete (@PathVariable Long permissionId) {
		permissionService.delete(permissionId);
	}

}











