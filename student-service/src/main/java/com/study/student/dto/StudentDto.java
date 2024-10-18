package com.study.student.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.NonNull;

@Data
@Component

public class StudentDto {

	@JsonProperty(access = Access.READ_ONLY)
	private String id;
	
	private String name;
	
	private String emailId;
	
	private String address;
	
	private String contactNumber;
}
