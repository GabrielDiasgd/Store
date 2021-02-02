package com.store.user;

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

import com.store.user.repository.UserRepositoty;
import com.store.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepositoty userRepository;
	
	
	@GetMapping
	public List<User> listUsers (){
		return userRepository.findAll();
	}
	
	
	@GetMapping("/{userId}")
	public User find (@PathVariable Long userId) {
		return userService.find(userId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User add (@RequestBody User user) {
		return userService.save(user);
	}
	
	@PutMapping("/{userId}")
	public User update (@PathVariable Long userId, @RequestBody User user) {
		User currentUser = userService.find(userId);
		BeanUtils.copyProperties(user, currentUser, "id", "dataCreation");
		
		return userService.save(currentUser);
	}
	
	
	@DeleteMapping("/{userId}")
	public void delete (@PathVariable Long userId) {
		userService.delete(userId);
	}

}
