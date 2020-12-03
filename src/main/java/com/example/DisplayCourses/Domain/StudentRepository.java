package com.example.DisplayCourses.Domain;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
	
	Student findByUsername(String username);

}
