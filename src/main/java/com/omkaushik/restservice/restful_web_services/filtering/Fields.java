package com.omkaushik.restservice.restful_web_services.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

//@JsonIgnoreProperties("field3")
public class Fields {

	@JsonView(View.view1.class)
	private String field1;
	
//	@JsonIgnore
	@JsonView(View.view2.class)
	private String field2;
	
	@JsonView({View.view1.class, View.view2.class})
	private String field3;

	public Fields(String field1, String field2, String field3) {
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}
	
}
