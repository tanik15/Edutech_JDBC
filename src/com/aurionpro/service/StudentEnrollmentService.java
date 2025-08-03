package com.aurionpro.service;

import java.util.Scanner;

import com.aurionpro.controller.EnrollmentController;

public class StudentEnrollmentService {

	public static void displayEnrollmentMenu(Scanner scanner) {
		int choice = 0;
		while (choice != 5) {
			System.out.println("\n📚 === Enrollment Menu ===");
			System.out.println("Please choose from the options below:");
			System.out.println("1️  Enroll new Student");
			System.out.println("2️  Update Enrolled Student");
			System.out.println("3️  Display All Enrolled Students Details");
			System.out.println("4  Update Enrollment Status");
			System.out.println("5. Exit");
			System.out.print("👉 Enter your choice: ");
			choice = scanner.nextInt();
			switch (choice) {
			case 1: {
				EnrollmentController.addNewEnrollment(scanner);
				continue;
			}
			case 2: {
				EnrollmentController.updateEnrollmentDetail(scanner);
				continue;
			}
			case 3: {
				EnrollmentController.displayAllEnrollmentStudents();
				continue;
			}
			case 4: {
				EnrollmentController.updateStatus(scanner);
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
