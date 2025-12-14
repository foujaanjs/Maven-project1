package com.kodnest.fifthhibernate;

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
		System.out.println("Enter name, email, phone number of the student");
		KodnestStudent kodneststudent = new KodnestStudent(scan.next(), scan.next(), scan.next());
		System.out.println("Enter the city,street,state,zipcode");
		Address address = new Address(scan.next(), scan.next(), scan.next(), scan.next());

		kodneststudent.setAddress(address);

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		Session session = factory.openSession();

		Transaction transaction = session.beginTransaction();

		session.persist(kodneststudent);

		transaction.commit();

		session.close();

		factory.close();

		scan.close();
	}
}
