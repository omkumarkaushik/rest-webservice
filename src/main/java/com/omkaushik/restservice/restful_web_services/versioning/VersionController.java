package com.omkaushik.restservice.restful_web_services.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

	@GetMapping("/v1/name")
	public PersonV1 getv1PersonName() {
		return new PersonV1("Bob Marley");
	}
	
	@GetMapping("/v2/name")
	public PersonV2 getv2PersonName() {
		return new PersonV2(new Name("Bob","Marley"));
	}
	
	@GetMapping(path = "/name", params = "version=1")
	public PersonV1 getv1PersonNameinparams() {
		return new PersonV1("Bob Marley");
	}
	
	@GetMapping(path = "/name", params = "version=2")
	public PersonV2 getv2PersonNameinparams() {
		return new PersonV2(new Name("Bob","Marley"));
	}
	
	@GetMapping(path = "/name/header", headers = "X-API-VERSION=1")
	public PersonV1 getv1PersonNameinparamsinHeaders() {
		return new PersonV1("Bob Marley");
	}
	
	@GetMapping(path = "/name/header", headers = "X-API-VERSION=2")
	public PersonV2 getv2PersonNameinparamsinHeaders() {
		return new PersonV2(new Name("Bob","Marley"));
	}
	
	/*
	 * This block of code is not letting server start
	 * not sure why but need to investigate
	 * for the time being let it stay commented
	 */
	
//	@GetMapping(path = "/name/accept", produces = "application/vnd.api-v1+json")
//	public PersonV1 getv1PersonNameinparamsinaccept() {
//		return new PersonV1("Bob Marley");
//	}
//	
//	@GetMapping(path = "/name/accept", produces = "application/vnd.api-v1+json")
//	public PersonV2 getv2PersonNameinparamsinaccept() {
//		return new PersonV2(new Name("Bob","Marley"));
//	}
}
