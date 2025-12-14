package com.kodnest.sms.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.kodnest.StudetMangementSystem.Student;

public class StudentManagementFrame extends JFrame {

	private SessionFactory factory;

	public StudentManagementFrame() {
		factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		setTitle("Student Management System");
		setSize(650, 420);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		initComponents();
	}

	private void initComponents() {
		// Root panel with padding and background
		JPanel root = new JPanel(new BorderLayout());
		root.setBorder(new EmptyBorder(20, 40, 20, 40));
		root.setBackground(new Color(245, 248, 250)); // light gray‑blue background[web:101]

		// Title
		JLabel lblTitle = new JLabel("Student Management System", JLabel.CENTER);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblTitle.setForeground(new Color(30, 60, 90));
		lblTitle.setBorder(new EmptyBorder(10, 10, 20, 10));

		root.add(lblTitle, BorderLayout.NORTH);

		// Buttons panel (center)
		JButton btnAdd = createPrimaryButton("Add Student");
		JButton btnGet = createPrimaryButton("Get Student");
		JButton btnUpdate = createPrimaryButton("Update Student");
		JButton btnDelete = createPrimaryButton("Delete Student");
		JButton btnViewAll = createSecondaryButton("View All Students");
		JButton btnExit = createSecondaryButton("Exit");

		JPanel btnPanel = new JPanel(new GridLayout(6, 1, 12, 12));
		btnPanel.setOpaque(false);
		btnPanel.add(btnAdd);
		btnPanel.add(btnGet);
		btnPanel.add(btnUpdate);
		btnPanel.add(btnDelete);
		btnPanel.add(btnViewAll);
		btnPanel.add(btnExit);

		root.add(btnPanel, BorderLayout.CENTER);

		add(root);

		// Actions (same methods/process as before)
		btnAdd.addActionListener(e -> addStudent());
		btnGet.addActionListener(e -> getStudent());
		btnUpdate.addActionListener(e -> updateStudent());
		btnDelete.addActionListener(e -> deleteStudent());
		btnViewAll.addActionListener(e -> viewAllStudents());
		btnExit.addActionListener(e -> {
			factory.close();
			dispose();
		});
	}

	private JButton createPrimaryButton(String text) {
		JButton btn = new JButton(text);
		btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btn.setForeground(Color.WHITE);
		btn.setBackground(new Color(52, 152, 219)); // blue
		btn.setFocusPainted(false);
		btn.setBorderPainted(false);
		btn.setOpaque(true);
		return btn;
	}

	private JButton createSecondaryButton(String text) {
		JButton btn = new JButton(text);
		btn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btn.setForeground(new Color(44, 62, 80));
		btn.setBackground(new Color(220, 232, 240));
		btn.setFocusPainted(false);
		btn.setBorderPainted(false);
		btn.setOpaque(true);
		return btn;
	}

	// ------- same Hibernate logic as before -------

	private void addStudent() {
		String name = JOptionPane.showInputDialog(this, "Enter name:");
		if (name == null)
			return;

		String marksStr = JOptionPane.showInputDialog(this, "Enter marks:");
		if (marksStr == null)
			return;

		String email = JOptionPane.showInputDialog(this, "Enter email:");
		if (email == null)
			return;

		try {
			int marks = Integer.parseInt(marksStr);

			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			Student s = new Student(name, marks, email);
			session.persist(s);
			tx.commit();
			session.close();

			JOptionPane.showMessageDialog(this, "Student added. ID = " + s.getId());
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Marks must be a number.");
		}
	}

	private void getStudent() {
		String idStr = JOptionPane.showInputDialog(this, "Enter student ID:");
		if (idStr == null)
			return;

		try {
			int id = Integer.parseInt(idStr);

			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			Student s = session.get(Student.class, id);
			tx.commit();
			session.close();

			if (s != null) {
				JOptionPane.showMessageDialog(this, "ID: " + s.getId() + "\nName: " + s.getName() + "\nMarks: "
						+ s.getMarks() + "\nEmail: " + s.getEmail());
			} else {
				JOptionPane.showMessageDialog(this, "No student with this ID.");
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "ID must be a number.");
		}
	}

	private void updateStudent() {
		String idStr = JOptionPane.showInputDialog(this, "Enter student ID to update:");
		if (idStr == null)
			return;

		try {
			int id = Integer.parseInt(idStr);

			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			Student s = session.get(Student.class, id);

			if (s != null) {
				String name = JOptionPane.showInputDialog(this, "Enter new name:", s.getName());
				if (name == null) {
					tx.commit();
					session.close();
					return;
				}

				String marksStr = JOptionPane.showInputDialog(this, "Enter new marks:", s.getMarks());
				if (marksStr == null) {
					tx.commit();
					session.close();
					return;
				}

				String email = JOptionPane.showInputDialog(this, "Enter new email:", s.getEmail());
				if (email == null) {
					tx.commit();
					session.close();
					return;
				}

				int marks = Integer.parseInt(marksStr);

				s.setName(name);
				s.setMarks(marks);
				s.setEmail(email);
				session.persist(s);

				JOptionPane.showMessageDialog(this, "Student updated.");
			} else {
				JOptionPane.showMessageDialog(this, "No student with this ID.");
			}

			tx.commit();
			session.close();
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "ID/marks must be a number.");
		}
	}

	private void deleteStudent() {
		String idStr = JOptionPane.showInputDialog(this, "Enter student ID to delete:");
		if (idStr == null)
			return;

		try {
			int id = Integer.parseInt(idStr);

			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			Student s = session.get(Student.class, id);

			if (s != null) {
				session.remove(s);
				JOptionPane.showMessageDialog(this, "Student deleted.");
			} else {
				JOptionPane.showMessageDialog(this, "No student with this ID.");
			}

			tx.commit();
			session.close();
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "ID must be a number.");
		}
	}

	private void viewAllStudents() {
		JOptionPane.showMessageDialog(this, "View All Students not implemented yet.");
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new StudentManagementFrame().setVisible(true));
	}
}
