package com.kodnest.ninthProject;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {

		Student s1 = new Student("Foujaan", "foujaan@email.com");
		Student s2 = new Student("Rama", "raman@email.com");
		Student s3 = new Student("sitha", "sitha@email.com");

		Club c1 = new Club("CollegeRadio");
		Club c2 = new Club("CollegeSports");
		Club c3 = new Club("CollegeNSS");

		Set<Club> clubset1 = new HashSet<Club>();
		clubset1.add(c1);
		clubset1.add(c2);

		Set<Club> clubset2 = new HashSet<Club>();
		clubset2.add(c2);
		clubset2.add(c3);

		Set<Club> clubset3 = new HashSet<Club>();
		clubset3.add(c1);
		clubset3.add(c3);

		s1.setClubs(clubset1);
		s2.setClubs(clubset2);
		s3.setClubs(clubset3);

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		Session session = factory.openSession();

		Transaction transaction = session.beginTransaction();

		session.persist(s1);
		session.persist(s2);
		session.persist(s3);
		session.persist(c1);
		session.persist(c2);
		session.persist(c3);

		transaction.commit();

		session.close();

		factory.close();

	}
}
