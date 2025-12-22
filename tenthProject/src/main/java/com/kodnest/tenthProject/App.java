package com.kodnest.tenthProject;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();

		String hql = "from kodneststudent where id > 1";

		Query<kodneststudent> query = session.createQuery(hql, kodneststudent.class);

		List<kodneststudent> studentList = query.getResultList();

		System.out.println(studentList);

		transaction.commit();

		session.close();

		factory.close();
	}
}
