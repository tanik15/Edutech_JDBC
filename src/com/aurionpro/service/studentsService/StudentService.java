package com.aurionpro.service.studentsService;

import java.util.Scanner;

import com.aurionpro.controller.studentsController.StudentController;

public class StudentService {
	public static void displayStudentMenu(Scanner scanner) {
		int choice = 0;
		while (choice != 8) {
			System.out.println("\n📚 === Student Menu ===");
			System.out.println("Please choose from the options below:");
			System.out.println("1️  Display All Students");
			System.out.println("2️  Search a Student");
			System.out.println("3️  Add a New Student");
			System.out.println("4️  Delete a Student");
			System.out.println("5  Display Students Courses");
			System.out.println("6. Student Subjects");
			System.out.println("7. Student Total Fees");
			System.out.println("8. Exit");
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
				StudentController.displayStudentCourses();
				continue;
			}
			case 6: {
				StudentController.displayStudentSubjects();
				continue;
			}
			case 7: {
				StudentController.displayStudentFees();
				continue;
			}
			case 8: {
				break;
			}
			default: {
				break;
			}
			}
		}
	}
}
