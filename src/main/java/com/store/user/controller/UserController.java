package com.store.user.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.store.exception.BusinessException;
import com.store.exception.ProfileNotFoundException;
import com.store.user.converters.UserAssemblerDTO;
import com.store.user.converters.UserDisassemblerInput;
import com.store.user.dto.UserDTO;
import com.store.user.input.UserInput;
import com.store.user.model.User;
import com.store.user.repository.UserRepositoty;
import com.store.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepositoty userRepository;
	
	@Autowired
	private UserAssemblerDTO userAssembler;
	
	@Autowired
	private UserDisassemblerInput userDisassembler;
	
	
	@GetMapping
	public List<UserDTO> listUsers (){
		return userAssembler.toCollectionDTO(userRepository.findAll());
	}
	
	
	@GetMapping("/{userId}")
	public UserDTO find (@PathVariable Long userId) {
		return userAssembler.toDTO(userService.find(userId));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserDTO add (@RequestBody @Valid UserInput userInput) {
		try {
			User user = userDisassembler.toDomainObject(userInput);
			return userAssembler.toDTO(userService.save(user));
		} catch (ProfileNotFoundException e) {
			throw new BusinessException(e.getMessage());
		}
		
	}
	
	@PutMapping("/{userId}")
	public UserDTO update (@PathVariable Long userId, @Valid @RequestBody UserInput userInput) {
		try {
			User currentUser = userService.find(userId);
			User user = userDisassembler.toDomainObject(userInput);
			BeanUtils.copyProperties(user, currentUser, "id", "dateCreation");
			
			return userAssembler.toDTO(userService.save(currentUser));
		} catch (ProfileNotFoundException e) {
			throw new BusinessException(e.getMessage());
		}
		
		
	}
	
	
	@DeleteMapping("/{userId}")
	public void delete (@PathVariable Long userId) {
		userService.delete(userId);
	}

}
