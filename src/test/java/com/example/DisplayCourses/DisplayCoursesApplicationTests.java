package com.example.DisplayCourses;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.DisplayCourses.Web.CoursesController;

@RunWith(SpringRunner.class)
@SpringBootTest
class DisplayCoursesApplicationTests {

	@Autowired
	private CoursesController controller;
	
	@Test
	void contextLoads() throws Exception{
		assertThat(controller).isNotNull();
	}

}
