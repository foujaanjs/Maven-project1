package com.kodnest.sixthhibernate;

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
		System.out.println("Enter name, price and noofpages");
		String name = scan.next();
		int price = scan.nextInt();
		int noOfPages = scan.nextInt();
		Book book = new Book(name, price, noOfPages);
		System.out.println("Enter name,gender,age,address");
		Author author = new Author(scan.next(), scan.next(), scan.nextInt(), scan.next());
		book.setAuthor(author);
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		Session session = factory.openSession();

		Transaction transaction = session.beginTransaction();

		session.persist(book);

		transaction.commit();

		session.close();

		factory.close();

		scan.close();

	}
}
