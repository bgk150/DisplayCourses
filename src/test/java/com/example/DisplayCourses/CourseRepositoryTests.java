package com.example.DisplayCourses;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.DisplayCourses.Domain.Course;
import com.example.DisplayCourses.Domain.CourseRepository;
import com.example.DisplayCourses.Domain.Teacher;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CourseRepositoryTests {

	@Autowired
	private CourseRepository repository;
	
	@Test
	public void findByNameShouldReturnCourse() {
		List<Course> courses = repository.findByName("OSE");
		
		assertThat(courses).hasSize(1);
		assertThat(courses.get(0).getCode()).isEqualTo("SWD4TF043-3034");
	}
	
	@Test
	public void createNewCourse() {
		Course course = new Course("Beginner's Finnish", "FIN4TF010-3004", new Teacher("Taija Hamalainen"));
		repository.save(course);
		assertThat(course.getId()).isNotNull();
	}
	
	@Test
	public void deleteCourse() {
		List<Course> courses = repository.findByName("OSE");
		courses.remove(courses.get(0));
		assertThat(courses).hasSize(0);
	}
	
	@Test
	public void editCourse() {
		List<Course> courses = repository.findByName("OSE");
		(courses.get(0)).setName("Orientation to Software Engineering");
		assertThat(courses.get(0).getName()).isEqualTo("Orientation to Software Engineering");
	}
	
	
}
