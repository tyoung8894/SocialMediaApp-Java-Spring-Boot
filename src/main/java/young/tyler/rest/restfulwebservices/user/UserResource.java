package young.tyler.rest.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
//import org.springframework.hateoas.server.mvc.ControllerLinkBuilder.*;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;
		
	@RequestMapping(method = RequestMethod.GET,path="/users")
	public List<User> retrieveAllUsers(){
		List<User> allUsers = service.findAll();
		if(allUsers == null) {
			throw new UserNotFoundException("No users found");
		}
		return allUsers;
	}
	
	//"all-users", SERVER_PATH + "/users"
	@RequestMapping(method = RequestMethod.GET,path="/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);	
		if(user==null) {
			throw new UserNotFoundException("id-" + id);
		}
			
		return user;
	}
	
	//@Valid enable content validation on user bean passed in, in invalid return proper response back
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		if(user.getName()==null) {
			throw new UserNotFoundException("No user name input");
		} else if(user.getBirthDate()==null) {
			throw new UserNotFoundException("No user birthdate");
		}
		User savedUser = service.save(user);
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId())
			.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
    	User user = service.delete(id);
    	
    	if(user==null) {
    		throw new UserNotFoundException("id-"+id);
    	}
    }
	
    
	@GetMapping("/users/{id}/posts/{post_id}")
	public String retrievePostDetails() {
		return "";
	}
	
	
}
