package com.study.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.student.dto.StudentDto;
import com.study.student.response.Response;
import com.study.student.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/students-service")
public class StudentController {

	@Autowired
	private StudentService service;
	
	private 

	@Operation(summary = "API to add a student details")
	@PostMapping("/save")
	 ResponseEntity<Response<StudentDto>> saveStudent(@RequestBody StudentDto dto) {
		System.out.println(dto);
		StudentDto student = service.saveData(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response<StudentDto>(student,
				"Student successfully Created", HttpStatus.CREATED, HttpStatus.CREATED.value()));
	}
	@Operation(summary = "API to get-all students details")
	@GetMapping
	public ResponseEntity<Object> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
	}
	
	@Operation(summary = "API to get a single student details ")
	@GetMapping("/{id}")
	public ResponseEntity<Object> getById(@PathVariable String id){
		StudentDto student=service.getById(id);
		return ResponseEntity.status(HttpStatus.OK).body(student);
	}
	@Operation(summary = "API to delete a student details")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable String id){
		return ResponseEntity.status(HttpStatus.OK).body(service.deleteById(id));
	}
	
	@Operation(summary = "API to update a student details")
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateById(@RequestBody StudentDto dto,@PathVariable String id ){
		StudentDto studentDto=service.updateById(id, dto);
		
		return ResponseEntity.status(HttpStatus.OK).body(studentDto);
	}
}
