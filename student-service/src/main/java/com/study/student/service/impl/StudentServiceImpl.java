package com.study.student.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.naming.InvalidNameException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.student.dto.StudentDto;
import com.study.student.entity.StudentEntity;
import com.study.student.exceptions.IdInvalidException;
import com.study.student.repo.StudentRepo;
import com.study.student.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepo repo;

	@Autowired
	private ModelMapper modelMapper;

	public StudentDto saveData(StudentDto studentDto) {
		
		StudentEntity student = modelMapper.map(studentDto, StudentEntity.class);
		System.out.println(student);
		StudentEntity saveStudent = repo.save(student);
		StudentDto dto = modelMapper.map(saveStudent, StudentDto.class);
		System.out.println(dto);
		if(dto.getId().equals(null)) {
			throw new IdInvalidException("id doesn't exist");
		}
		return dto;
	}

	public List<StudentDto> getAll() {
		List<StudentEntity> studentList = repo.findAll();
		List<StudentDto> st = new ArrayList<>();
		for (StudentEntity student : studentList) {
			System.out.println(student + "*****");
			StudentDto dto = modelMapper.map(student, StudentDto.class);
			st.add(dto);
			System.out.println(st);
		}
		return st;
	}

	public StudentDto getById(String id) {
		StudentEntity student = repo.findById(id).orElseThrow(() -> new IdInvalidException("Student id doesn't exist"));
		StudentDto studentdto = modelMapper.map(student, StudentDto.class);
		return studentdto;
	}

	public String deleteById(String id) {
		StudentEntity student = repo.findById(id).orElseThrow(() -> new IdInvalidException("Student id doesn't exist"));
		if (id != null) {
			repo.deleteById(id);
			return "Deleted Succesfull";
		} else {
			return "deleted unsuccessfull";
		}
	}
	
	public StudentDto updateById(String id, StudentDto dto) {
		StudentEntity student=repo.findById(id).orElseThrow(() -> new IdInvalidException("Student id doesn't exist"));
		
		student.setName(dto.getName());
		student.setEmailId(dto.getEmailId());
		student.setContactNumber(dto.getContactNumber());
		student.setAddress(dto.getAddress());
		StudentEntity st=repo.save(student);
		StudentDto studentDto=modelMapper.map(st, StudentDto.class);
		
		return studentDto;
	}

}
