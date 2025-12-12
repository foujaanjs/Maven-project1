package com.kodnest.FourthHibernate;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		System.out.println("Enter the user id");
		int id = scan.nextInt();
		Student student = session.get(Student.class, id);
		if (student != null) {
			System.out.println("Enter new name and new email");
			String name = scan.next();
			String email = scan.next();
			student.setName(name);
			student.setEmail(email);
			session.persist(student);
			System.out.println("Updated successfully");
		} else {
			System.out.println("Student with id not found to update");
		}
		transaction.commit();
		session.close();
		factory.close();
		scan.close();
	}
}
