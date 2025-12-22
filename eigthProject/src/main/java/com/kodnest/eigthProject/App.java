package com.kodnest.eigthProject;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		Department department = new Department("IT", "5thFloor");

		Employee employee1 = new Employee("Foujaan", "99999", department);
		Employee employee2 = new Employee("parnneth", "89999", department);
		Employee employee3 = new Employee("agan", "79999", department);
		Employee employee4 = new Employee("kumar", "39999", department);

		List<Employee> empList = new ArrayList<Employee>();

		empList.add(employee1);
		empList.add(employee2);
		empList.add(employee3);
		empList.add(employee4);

		department.setEmployee(empList);

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(department);

		transaction.commit();
		session.close();
		factory.close();

	}
}
