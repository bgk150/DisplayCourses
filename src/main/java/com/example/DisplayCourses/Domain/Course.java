package com.example.DisplayCourses.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String code;
	

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "teacherid")
	private Teacher teacher;

	public Course() {
	}

	public Course(String name, String code, Teacher teacher) {
		super();
		this.name = name;
		this.code = code;
		this.teacher = teacher;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@Override
	public String toString() {
		if (this.teacher != null) {
			return "Course [id=" + id + ", teacher=" + this.teacher + ", name=" + name + ", code=" + code + "]";
		} else
			return "Course [id=" + id + ", name=" + name + ", code=" + code + "]";
	}
}
