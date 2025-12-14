package com.kodnest.ems.ui;

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

import com.kodnest.ems.Employee;

public class EmployeeManagementFrame extends JFrame {

	private SessionFactory factory;

	public EmployeeManagementFrame() {
		factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); // same as App.java

		setTitle("Employee Management System");
		setSize(650, 420);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		initComponents();
	}

	private void initComponents() {
		JPanel root = new JPanel(new BorderLayout());
		root.setBorder(new EmptyBorder(20, 40, 20, 40));
		root.setBackground(new Color(245, 248, 250));

		JLabel lblTitle = new JLabel("Employee Management System", JLabel.CENTER);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblTitle.setForeground(new Color(30, 60, 90));
		lblTitle.setBorder(new EmptyBorder(10, 10, 20, 10));
		root.add(lblTitle, BorderLayout.NORTH);

		JButton btnAdd = createPrimaryButton("Add Employee");
		JButton btnGet = createPrimaryButton("Get Employee");
		JButton btnDelete = createPrimaryButton("Delete Employee");
		JButton btnUpdate = createPrimaryButton("Update Employee");
		JButton btnViewAll = createSecondaryButton("View All Employees");
		JButton btnExit = createSecondaryButton("Exit");

		JPanel btnPanel = new JPanel(new GridLayout(6, 1, 12, 12));
		btnPanel.setOpaque(false);
		btnPanel.add(btnAdd);
		btnPanel.add(btnGet);
		btnPanel.add(btnDelete);
		btnPanel.add(btnUpdate);
		btnPanel.add(btnViewAll);
		btnPanel.add(btnExit);

		root.add(btnPanel, BorderLayout.CENTER);

		add(root);

		// Same operations as App.java, but using dialogs
		btnAdd.addActionListener(e -> addEmployee());
		btnGet.addActionListener(e -> getEmployee());
		btnDelete.addActionListener(e -> deleteEmployee());
		btnUpdate.addActionListener(e -> updateEmployee());
		btnViewAll.addActionListener(e -> viewAllEmployees());
		btnExit.addActionListener(e -> {
			factory.close();
			dispose();
		});
	}

	private JButton createPrimaryButton(String text) {
		JButton btn = new JButton(text);
		btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btn.setForeground(Color.WHITE);
		btn.setBackground(new Color(52, 152, 219));
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

	// ---------- logic equivalent to App.java ----------

	private void addEmployee() {
		String name = JOptionPane.showInputDialog(this, "Enter employee name:");
		if (name == null)
			return;

		String salaryStr = JOptionPane.showInputDialog(this, "Enter salary:");
		if (salaryStr == null)
			return;

		String email = JOptionPane.showInputDialog(this, "Enter email:");
		if (email == null)
			return;

		try {
			int salary = Integer.parseInt(salaryStr);

			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			Employee employee = new Employee(name, salary, email);
			session.persist(employee);
			tx.commit();
			session.close();

			JOptionPane.showMessageDialog(this, "Employee added. ID = " + employee.getId());
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Salary must be a number.");
		}
	}

	private void getEmployee() {
		String idStr = JOptionPane.showInputDialog(this, "Enter employee ID:");
		if (idStr == null)
			return;

		try {
			int id = Integer.parseInt(idStr);

			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			Employee emp = session.get(Employee.class, id);
			tx.commit();
			session.close();

			if (emp != null) {
				JOptionPane.showMessageDialog(this, "ID: " + emp.getId() + "\nName: " + emp.getName() + "\nSalary: "
						+ emp.getSalary() + "\nEmail: " + emp.getEmail());
			} else {
				JOptionPane.showMessageDialog(this, "No employee with this ID.");
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "ID must be a number.");
		}
	}

	private void deleteEmployee() {
		String idStr = JOptionPane.showInputDialog(this, "Enter employee ID to delete:");
		if (idStr == null)
			return;

		try {
			int id = Integer.parseInt(idStr);

			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			Employee emp = session.get(Employee.class, id);

			if (emp != null) {
				session.remove(emp);
				JOptionPane.showMessageDialog(this, "Employee deleted successfully.");
			} else {
				JOptionPane.showMessageDialog(this, "Employee not found.");
			}

			tx.commit();
			session.close();
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "ID must be a number.");
		}
	}

	private void updateEmployee() {
		String idStr = JOptionPane.showInputDialog(this, "Enter employee ID to update:");
		if (idStr == null)
			return;

		try {
			int id = Integer.parseInt(idStr);

			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			Employee emp = session.get(Employee.class, id);

			if (emp != null) {
				String name = JOptionPane.showInputDialog(this, "Enter new name:", emp.getName());
				if (name == null) {
					tx.commit();
					session.close();
					return;
				}

				String salaryStr = JOptionPane.showInputDialog(this, "Enter new salary:", emp.getSalary());
				if (salaryStr == null) {
					tx.commit();
					session.close();
					return;
				}

				String email = JOptionPane.showInputDialog(this, "Enter new email:", emp.getEmail());
				if (email == null) {
					tx.commit();
					session.close();
					return;
				}

				int salary = Integer.parseInt(salaryStr);

				emp.setName(name);
				emp.setSalary(salary);
				emp.setEmail(email);
				session.persist(emp);

				JOptionPane.showMessageDialog(this, "Employee updated successfully.");
			} else {
				JOptionPane.showMessageDialog(this, "Employee not found.");
			}

			tx.commit();
			session.close();
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "ID/salary must be a number.");
		}
	}

	private void viewAllEmployees() {
		JOptionPane.showMessageDialog(this, "View All Employees not implemented yet.");
		// Later you can open a table window here.
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new EmployeeManagementFrame().setVisible(true));
	}
}
