package young.tyler.rest.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

	@GetMapping("v1/person")
	public Person person() {
		return new Person("Tyler Young");		
	}
	
	@GetMapping("v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Tyler", "Young"));		
	}
	
	@GetMapping(value="/person/param", params="version=1")
	public Person paramPerson() {
		return new Person("Tyler Young");		
	}
	
	@GetMapping(value="/person/param", params="version=2")
	public PersonV2 paramPersonV2() {
		return new PersonV2(new Name("Tyler", "Young"));		
	}
	
	@GetMapping(value="/person/header", headers="X-API-VERSION=1")
	public Person headerPerson() {
		return new Person("Tyler Young");		
	}
	
	@GetMapping(value="/person/header", headers="X-API-VERSION=2")
	public PersonV2 headerPersonV2() {
		return new PersonV2(new Name("Tyler", "Young"));		
	}
	
	@GetMapping(value="/person/produces", produces="application/vnd.company-app-v1+json")
	public Person producesPerson() {
		return new Person("Tyler Young");		
	}
	
	@GetMapping(value="/person/produces", produces="application/vnd.company-app-v2+json")
	public PersonV2 producesPersonV2() {
		return new PersonV2(new Name("Tyler", "Young"));		
	}
}
