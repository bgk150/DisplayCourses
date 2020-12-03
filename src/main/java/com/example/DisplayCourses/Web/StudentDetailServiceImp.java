package com.example.DisplayCourses.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.DisplayCourses.Domain.Student;
import com.example.DisplayCourses.Domain.StudentRepository;

@Service

public class StudentDetailServiceImp implements UserDetailsService {
	private final StudentRepository repository;
	@Autowired
	public StudentDetailServiceImp(StudentRepository studentRepository) { 
		this.repository= studentRepository;
		}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws
		UsernameNotFoundException{   
		Student student= repository.findByUsername(username);
		UserDetails user= new org.springframework.security.core.userdetails.User(username,
				student.getPassword(),
				AuthorityUtils.createAuthorityList(student.getRole()));
		return user;
		   
	}
}
