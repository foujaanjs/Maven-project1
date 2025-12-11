package com.kodnest.firstHibernatePeoject;

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
		System.out.println("enter the student name,marks,email");
		Student s1 = new Student(scan.next(), scan.nextInt(), scan.next());

		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");

		SessionFactory factory = configuration.buildSessionFactory();

		Session session = factory.openSession();

		Transaction transcation = session.beginTransaction();

		session.persist(s1);

		transcation.commit();

		session.close();

		factory.close();
	}
}
