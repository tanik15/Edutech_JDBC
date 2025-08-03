package com.aurionpro.service;

import java.util.Scanner;
import com.aurionpro.controller.StudentController;

public class StudentService {
	public static void displayStudentMenu(Scanner scanner) {
		int choice = 0;
		while (choice != 5) {
			System.out.println("\n📚 === Student Menu ===");
			System.out.println("Please choose from the options below:");
			System.out.println("1️  Display All Students");
			System.out.println("2️  Search a Student");
			System.out.println("3️  Add a New Student");
			System.out.println("4️  Delete a Student");
			System.out.println("5. Exit");
			System.out.print("👉 Enter your choice: ");
			choice = scanner.nextInt();
			switch (choice) {
			case 1: {
				StudentController.displayAllStudents();
				continue;
			}
			case 2: {
				StudentController.displayAStudent(scanner);
				continue;
			}
			case 3: {
				StudentController.addNewStudent(scanner);
				continue;
			}
			case 4: {
				StudentController.deleteAStudent(scanner);
				continue;
			}
			case 5: {
				break;
			}
			default: {
				break;
			}
			}
		}
	}
}
