package in.vasanth.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.vasanth.binding.CreateUsers;
import in.vasanth.entity.AccountDtls;
import in.vasanth.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/createUser", consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<String> createUser(@RequestBody CreateUsers user) {
		String status = userService.createUser(user);

		return new ResponseEntity<String>(status, HttpStatus.CREATED);

	}
	
	@GetMapping(value="/viewUsers",
			produces = { "application/json" }
			)
	public List<AccountDtls> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping(value="/editUser/{userId}",
			produces= {"application/json"}
			)
	public AccountDtls editUser(@PathVariable Integer userId) {
		return userService.editUser(userId);
		
	}
	@GetMapping(value="/deleteUser/{userId}",
			produces= {"application/json"}
			)
	public Boolean deleteUser(@PathVariable Integer userId) {
		return userService.deleteUser(userId);
	}
}
