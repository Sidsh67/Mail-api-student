package com.study.student.service;

import java.util.List;

import com.study.student.dto.StudentDto;

public interface StudentService {

	public StudentDto saveData(StudentDto studentDto);

	public List<StudentDto> getAll();

	public StudentDto getById(String id);
	
	public String deleteById(String id);
	
	public StudentDto updateById(String id, StudentDto dto);
}
