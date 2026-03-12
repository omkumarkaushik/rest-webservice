package com.omkaushik.restservice.restful_web_services.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionSbiController {

	/*
	 * Commenting this and use-path-sagement variable in application.properties file
	 * to use it again uncomment both
	 */
	
//	@GetMapping(value = "/{version}/sbiname", version = "1.0.0")
//	public PersonV1 getv1PersonName() {
//		return new PersonV1("Bob Marley");
//	}
//	
//	@GetMapping(value = "/{version}/sbiname", version = "2.0.0")
//	public PersonV2 getv2PersonName() {
//		return new PersonV2(new Name("Bob","Marley"));
//	}
	
	@GetMapping(path = "/namesbi", version="1")
	public PersonV1 getv1PersonNameinparams() {
		return new PersonV1("Bob Marley");
	}
	
	@GetMapping(path = "/namesbi", version="2")
	public PersonV2 getv2PersonNameinparams() {
		return new PersonV2(new Name("Bob","Marley"));
	}
}
