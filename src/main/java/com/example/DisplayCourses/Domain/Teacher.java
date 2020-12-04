package com.example.DisplayCourses.Domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long teacherid;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
	private List<Course> courses;
	
	public Teacher() {}

	public Teacher(String name) {
		super();
		this.name = name;
	}

	public Long getTeacherid() {
		return teacherid;
	}

	public void setId(Long teacherid) {
		this.teacherid = teacherid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Teacher [teacherid=" + teacherid + ", name=" + name + "]" ;
	}

	
}