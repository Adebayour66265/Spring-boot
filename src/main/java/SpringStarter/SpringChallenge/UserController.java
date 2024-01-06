package SpringStarter.SpringChallenge;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@GetMapping
	public String getUsers(@RequestParam(value = "page") int pageNo, @RequestParam(value = "limit") int limitNo) {
	
		return "Get request Sent for page" + pageNo+" and limit "+ limitNo;

	}
	
	@GetMapping(path = "/{userId}")
	public String getUser(@PathVariable String userId) {
	
		return "Get request Sent for user Id:" + userId;

	}
	
	@PostMapping
	public String creatUser() {
	
		return "Post request Sent";

	}
	
	@PutMapping
	public String updateUser() {
	
		return "Update request Sent";

	}
	
	@DeleteMapping
	public String deleteUser() {
	
		return "Delete request Sent";

	}
}
