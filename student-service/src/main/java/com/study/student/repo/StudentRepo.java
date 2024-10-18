package com.study.student.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.student.entity.StudentEntity;

@Repository
public interface StudentRepo extends JpaRepository<StudentEntity, String>{

}
