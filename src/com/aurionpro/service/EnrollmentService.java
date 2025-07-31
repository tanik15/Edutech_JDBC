package com.aurionpro.service;

import java.util.Scanner;

import com.aurionpro.controller.EnrollmentController;

public class EnrollmentService {
	
	public static void displayEnrollmentMenu(Scanner scanner) {
		System.out.println("\n📚 === Enrollment Menu ===");
		System.out.println("Please choose from the options below:");
		System.out.println("1️  Enroll new Student");
		System.out.println("2️  Update Enrolled Student");
		System.out.println("3️  Display All Enrolled Students Details");
		System.out.println("4  Update Enrollment Status");
		System.out.print("👉 Enter your choice: ");
		int choice = scanner.nextInt();
		switch (choice) {
		case 1: {
			EnrollmentController.addNewEnrollment(scanner);
			break;
		}
		case 2: {
			EnrollmentController.updateEnrollmentDetail(scanner);
			break;
		}
		case 3: {
			EnrollmentController.displayAllEnrollmentStudents();
			break;
		}
		case 4: {
			EnrollmentController.updateStatus(scanner);
			break;
		}
		}
	}

}
