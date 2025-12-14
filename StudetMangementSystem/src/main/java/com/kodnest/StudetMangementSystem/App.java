package com.kodnest.StudetMangementSystem;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 */
public class App {
	static SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		while (true) {
			System.out.println("Welcome to Student Management app Enter the input");
			System.out.println("Press 1 -> addStudent");
			System.out.println("Press 2 -> getStudent");
			System.out.println("Press 3 -> UpdateStudent");
			System.out.println("Press 4 -> DeleteStudent");
			System.out.println("Press other -> toExit");
			int ch = scan.nextInt();
			switch (ch) {
			case 1:
				addStudents();
				break;
			case 2:
				getStudents();
				break;
			case 3:
				updateStudent();
				break;
			case 4:
				deleteStudent();
				break;
			default:
				System.out.println("Thanks for using student management system");
				factory.close();
				scan.close();
				return;
			}

		}
	}

	static void getStudents() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		System.out.println("Enter the student id");
		int id = scan.nextInt();
		Student student = session.get(Student.class, id);
		if (student != null) {
			System.out.println(student);
		} else {
			System.out.println("No student found with this id");
		}
		transaction.commit();
		session.close();
	}

	static void addStudents() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		System.out.println("Enter the name,marks email");
		Student student = new Student(scan.next(), scan.nextInt(), scan.next());
		session.persist(student);
		transaction.commit();
		session.close();
	}

	static void updateStudent() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		System.out.println("Enter the student id");
		int id = scan.nextInt();
		Student student = session.get(Student.class, id);
		if (student != null) {
			System.out.println("Enter the marks and email to update");
			int umarks = scan.nextInt();
			String uemail = scan.next();
			student.setMarks(umarks);
			student.setEmail(uemail);
			session.persist(student);
			System.out.println("Student updated succesfully");
		} else {
			System.out.println("No student found with this id");
		}
		transaction.commit();
		session.close();
	}

	static void deleteStudent() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		System.out.println("Enter the student id");
		int id = scan.nextInt();
		Student student = session.get(Student.class, id);
		if (student != null) {
			session.remove(student);
			System.out.println("Student removed successfully");
		} else {
			System.out.println("No student found with this id");
		}
		transaction.commit();
		session.close();
	}
}
