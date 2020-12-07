package com.example.DisplayCourses.Web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.DisplayCourses.Domain.Course;
import com.example.DisplayCourses.Domain.CourseRepository;
import com.example.DisplayCourses.Domain.Teacher;
import com.example.DisplayCourses.Domain.TeacherRepository;

@Controller 

public class CoursesController {
	
	@Autowired
	private CourseRepository crepository;
	
	@Autowired
	private TeacherRepository trepository;
	
	//for the page to display the courses
	@RequestMapping(value = { "/courses", "/" })
	public String courseList(Model model) {
		model.addAttribute("courses", crepository.findAll());
		return "courselist";
	}
	
	//to add the course and teacher to model
	@RequestMapping(value = "/add")
	public String addCourse(Model model) {
		model.addAttribute("course", new Course());
		model.addAttribute("teachers", trepository.findAll());
		return "addcourse";
	}
	
	
	//saves the course and teacher in server
	@RequestMapping(value = "/saveCourse", method = RequestMethod.POST)
	public String save(Course course, Teacher teacher) {
		trepository.save(teacher);	
		crepository.save(course);
		return "redirect:/courses";
	}
	
	//for deleting the course from server where id comes from the course
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteCourse(@PathVariable("id") Long id, Model model) {
		crepository.deleteById(id);
		return "redirect:../courses";
	}
	
	//for editing a course
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editCourse(@PathVariable("id") Long id, Model model) {
		Optional<Course> course = crepository.findById(id);
		model.addAttribute("course", course);
		model.addAttribute("teachers", trepository.findAll());
		return "editcourse";
	}
	
	//for displaying teachers list
	@RequestMapping(value = {"/teachers"})
	public String teacherList(Model model) {
		model.addAttribute("teachers", trepository.findAll());
		return "teacherlist";
	}
	
	//to add the teacher
	@RequestMapping(value = "/addteacher")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addTeacher(Model model) {
		model.addAttribute("teacher", new Teacher());
		return "addteacher";
	}
	
	//saves the teacher in server
	@RequestMapping(value = "/saveTeacher", method = RequestMethod.POST)
	public String saveTeacher(Teacher teacher) {
			trepository.save(teacher);
			return "redirect:teachers";
		}
	
	//for displaying a teacher
	@RequestMapping(value = "/teacher/{teacherid}", method = RequestMethod.GET)
	public String teacher(@PathVariable("teacherid") Long teacherid, Model model) {
		Optional<Teacher> teacher = trepository.findById(teacherid);
		model.addAttribute("teacher", teacher);
		return "teacher";
	}
	
	//rest-ful data for courses
	@RequestMapping(value="courselist", method=RequestMethod.GET) 
	@ResponseBody List<Course> courseListRest() {
		return (List<Course>) crepository.findAll();
	}
	
	//rest-ful data for teachers
	@RequestMapping(value="teacherlist", method=RequestMethod.GET)
	@ResponseBody List<Teacher> teacherListRest() {
		return (List<Teacher>) trepository.findAll();
	}
	
}
