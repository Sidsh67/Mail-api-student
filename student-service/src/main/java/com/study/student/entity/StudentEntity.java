package com.study.student.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Document(collection = "student-service")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentEntity {
	

	@Id
	@GeneratedValue(strategy = GenerationType.UUID.AUTO)
	private String id;
	
	private String name;
	
	private String emailId;
	
	private String address;
	
	private String contactNumber;
	

}
