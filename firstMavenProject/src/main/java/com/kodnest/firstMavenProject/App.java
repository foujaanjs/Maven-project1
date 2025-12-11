package com.kodnest.firstMavenProject;

import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		EmployeeManagement emp = new EmployeeManagement();
		while (true) {
			System.out.println("CHOOSE THE OPERATION");
			System.out.println("1->getEmployeebyId");
			System.out.println("2->getAllEmployee");
			System.out.println("3->AddEmployee");
			System.out.println("4->updateEmployee");
			System.out.println("5->deleteemployee");
			System.out.println("Other -> exit");
			int ch = scan.nextInt();
			switch (ch) {
			case 1: {
				System.out.println("Enter the employeeId");
				int eid = scan.nextInt();
				emp.getEmployeeById(eid);
				break;
			}
			case 2: {
				emp.getAllEmployees();
				break;
			}
			case 3: {
				System.out.println("Enter the id,name,salary,phone,email,type");
				int id = scan.nextInt();
				String name = scan.next();
				int salary = scan.nextInt();
				int phone = scan.nextInt();
				String email = scan.next();
				String type = scan.next();
				emp.AddEmployee(id, name, salary, phone, email, type);
				break;
			}
			case 4: {
				System.out.println("Enter the id of the employee to update");
				int id = scan.nextInt();
				emp.updateEmployee(id);
				break;
			}
			case 5: {
				System.out.println("Enter the id of the employee to delete");
				int id = scan.nextInt();
				emp.deleteEmployee(id);
				break;
			}
			default:
				System.out.println("THANKS FOR USING EMS");
				return;
			}
		}
	}
}
