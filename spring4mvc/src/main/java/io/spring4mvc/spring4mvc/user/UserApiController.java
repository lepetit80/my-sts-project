package io.spring4mvc.spring4mvc.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserApiController {

	private UserRepository userRepository;
	
	@Autowired
	public UserApiController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping("/users")
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
//	//Using postman body(json)
//	{
//		"email" : "lepetit80@gmail.com",
//		"birthDate" : "1980-11-27",
//		"tastes": ["spring", "scala", "kotlin"]
//	}
//	http post http://localhost:8888/api/users email=ltks3@naver.com birthDate=2017-11-03 tastes:='["httpie","pies"]' not working
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@PutMapping("/user/{email}")
	public User updateUser(@PathVariable String email, @RequestBody User user) {
		return userRepository.save(email, user);
	}
	
	@DeleteMapping("/user/{email}")
	public void deleteUser(@PathVariable String email) {
		userRepository.delete(email);
	}
	
	
}
