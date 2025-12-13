package com.kodnest.ems;

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
			System.out.println("Welcome to ems enter your input");
			System.out.println("Press 1 -> addEmployee");
			System.out.println("Press 2 -> GetEmployee");
			System.out.println("Press 3 -> DeleteEmployee");
			System.out.println("Press 4 -> UpdateEmployee");
			System.out.println("Press other -> exit");
			int ch = scan.nextInt();
			switch (ch) {
			case 1:
				addEmployee();
				break;
			case 2:
				getEmployee();
				break;
			case 3:
				deleteEmployee();
				break;
			case 4:
				updateEmployee();
				break;
			default:
				System.out.println("Thanks for using employee management app");
				factory.close();
				scan.close();
				break;
			}
		}
	}

	public static void addEmployee() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		System.out.println("Enter employee name, salary, email");
		Employee employee = new Employee(scan.next(), scan.nextInt(), scan.next());
		session.persist(employee);
		transaction.commit();
		session.close();
	}

	public static void getEmployee() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		System.out.println("Enter the employee Id");
		int id = scan.nextInt();
		Employee obj = session.get(Employee.class, id);
		if (obj != null) {
			System.out.println(obj);
		} else {
			System.out.println("No Employee foun at the given Id");
		}
		transaction.commit();
		session.close();
	}

	public static void deleteEmployee() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		System.out.println("Enter the employee ID");
		int id = scan.nextInt();
		Employee obj = session.get(Employee.class, id);
		if (obj != null) {
			session.remove(obj);
			System.out.println("Enter employee deleted succesfully");
		} else {
			System.out.println("Enter employee not found");
		}
		transaction.commit();
		session.close();
	}

	public static void updateEmployee() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		System.out.println("Enter the Employee ID");
		int id = scan.nextInt();
		Employee obj = session.get(Employee.class, id);
		if (obj != null) {
			System.out.println("Enter the new name,salary and email");
			obj.setName(scan.next());
			obj.setSalary(scan.nextInt());
			obj.setEmail(scan.next());
			session.persist(obj);
			System.out.println("Updated Succesfully");
		} else {
			System.out.println("Employee not found");
		}
		transaction.commit();
		session.close();
	}
}
