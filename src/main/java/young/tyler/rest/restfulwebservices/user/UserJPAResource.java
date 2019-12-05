package young.tyler.rest.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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


@RestController
public class UserJPAResource {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
		
	@RequestMapping(method = RequestMethod.GET,path="/jpa/users")
	public List<User> retrieveAllUsers(){
		return userRepository.findAll();
	}
	

	@RequestMapping(method = RequestMethod.GET,path="/jpa/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);	
		if(!user.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}	
		return user.get();
	}
	
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId())
			.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
    	userRepository.deleteById(id);
    }
	
    
	@GetMapping("/jpa/users/{id}/posts/{post_id}")
	public String retrievePostDetails() {
		return "";
	}
	
	
	@RequestMapping(method = RequestMethod.GET,path="/jpa/users/{id}/posts")
	public List<Post> retrieveAllPosts(@PathVariable int id){
		 Optional<User> userOptional = userRepository.findById(id);	
		 if(!userOptional.isPresent()) {
			 throw new UserNotFoundException("id-" + id);
		 }
		 
		 return userOptional.get().getPosts();
	}
	
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
		Optional<User> userOptional = userRepository.findById(id);		
		 if(!userOptional.isPresent()) {
			 throw new UserNotFoundException("id-" + id);
		 }
		 
		 User user = userOptional.get();
		 post.setUser(user);
		 
		 postRepository.save(post);

		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(post.getId())
			.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	
}
