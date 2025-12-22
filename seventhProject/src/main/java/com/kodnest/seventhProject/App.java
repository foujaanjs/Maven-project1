package com.kodnest.seventhProject;

import java.util.ArrayList;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		Course course1 = new Course("Java");
		Course course2 = new Course("sql");
		Course course3 = new Course("DSA");

		KodnestStudent kodnestStudent = new KodnestStudent("Foujaan", "Foujaan@gmail.com", "9345763320");

		course1.setKodenestStudent(kodnestStudent);
		course2.setKodenestStudent(kodnestStudent);
		course3.setKodenestStudent(kodnestStudent);

		ArrayList<Course> courseList = new ArrayList<Course>();
		courseList.add(course1);
		courseList.add(course2);
		courseList.add(course3);

		kodnestStudent.setCourse(courseList);
	}
}
