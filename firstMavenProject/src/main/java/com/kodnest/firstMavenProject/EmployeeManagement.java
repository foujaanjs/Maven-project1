package com.kodnest.firstMavenProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class EmployeeManagement {
	Scanner scan = new Scanner(System.in);
	Connection con = null;
	String dpath = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/kodnest";
	String user = "root";
	String password = "2004";

	public EmployeeManagement() {
		try {
			Class.forName(dpath);
			con = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void getEmployeeById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "select * from employee where id=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3) + " " + rs.getInt(4) + " "
						+ rs.getString(5) + " " + rs.getString(6));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	void getAllEmployees() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "select * from employee";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3) + " " + rs.getInt(4) + " "
						+ rs.getString(5) + " " + rs.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	void AddEmployee(int id, String name, int salary, int phone, String email, String type) {
		PreparedStatement ps = null;
		try {
			String sql = "insert into employee values(?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setInt(3, salary);
			ps.setInt(4, phone);
			ps.setString(5, email);
			ps.setString(6, type);
			int result = ps.executeUpdate();
			System.out.println(result + " row's got affected");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	void updateEmployee(int id) {
		try {
			System.out.println("Enter A-> update name, B-> phone, C->email");
			char ch = scan.next().charAt(0);
			switch (ch) {
			case 'A': {
				String sql = "update employee set name=? where id=?";
				System.out.println("Enter the name");
				String name = scan.next();
				PreparedStatement ps1 = con.prepareStatement(sql);
				ps1.setString(1, name);
				ps1.setInt(2, id);
				int result = ps1.executeUpdate();
				System.out.println(result + " employee got updated");
				ps1.close();
				break;
			}
			case 'B': {
				String sql = "update employee set phone=? where id=?";
				PreparedStatement ps2 = con.prepareStatement(sql);
				System.out.println("Enter the phone number");
				int phne = scan.nextInt();
				ps2.setInt(1, phne);
				ps2.setInt(2, id);
				int result = ps2.executeUpdate();
				System.out.println(result + "employee got updated");
				ps2.close();
				break;
			}
			case 'C': {
				String sql = "update employee set email=? where id=?";
				PreparedStatement ps3 = con.prepareStatement(sql);
				System.out.println("Enter the email");
				String email = scan.next();
				ps3.setString(1, email);
				ps3.setInt(2, id);
				int result = ps3.executeUpdate();
				System.out.println(result + ":employee got updated");
				ps3.close();
				break;
			}
			default: {
				System.out.println("Invalid enter enter the correct case");
				updateEmployee(id);
			}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void deleteEmployee(int id) {
		PreparedStatement ps = null;
		try {
			String sql = "delete from employee where id=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			int result = ps.executeUpdate();
			System.out.println(result + " row's got deleted");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
