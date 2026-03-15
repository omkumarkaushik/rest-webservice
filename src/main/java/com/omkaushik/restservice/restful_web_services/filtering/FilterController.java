package com.omkaushik.restservice.restful_web_services.filtering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class FilterController {

	@GetMapping("/allFields")
	public List<Fields> getAllFields(){
//		List<Fields> list = new ArrayList<Fields>();
//		Fields field = new Fields("field1","field2","field3");
//		list.add(field);
		List<Fields> field = Arrays.asList(new Fields("value1","value2","value3"),
				new Fields("value4","value5","value6"));
		return field;
	}

	
	@GetMapping("/allFields-withV-view")
	//@JsonView(View.view1.class)
	@JsonView(View.view2.class)
	public List<Fields> getAllFieldsWithView(){
//		List<Fields> list = new ArrayList<Fields>();
//		Fields field = new Fields("field1","field2","field3");
//		list.add(field);
		List<Fields> field = Arrays.asList(new Fields("value1","value2","value3"),
				new Fields("value4","value5","value6"));
		return field;
	}

}
