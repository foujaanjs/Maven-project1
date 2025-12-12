package com.kodnest.ThirdHibernateProject;

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
		System.out.println("Enter the id to be deleted");
		int id = scan.nextInt();
		Student student = session.get(Student.class, id);
		if (student != null) {
			session.remove(student);
			System.out.println("Student with this " + id + " got deleted.");
		} else {
			System.out.println("No student found with the id");
		}
		transaction.commit();
		session.close();
		factory.close();
		scan.close();

	}
}
