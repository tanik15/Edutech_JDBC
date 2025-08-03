package com.aurionpro.service.courseService;

import java.util.Scanner;

import com.aurionpro.controller.courseController.CourseSubjectController;

public class CourseSubjectService {
	public static void displayCourseMenu(Scanner scanner) {
		int choice = 0;
		while (choice != 5) {
			System.out.println("\n📚 === Subject Menu ===");
			System.out.println("Please choose from the options below:");
			System.out.println("1️  Add new Subject");
			System.out.println("2️  Display All Subject");
			System.out.println("3️  Display Subject by ID");
			System.out.println("4  Delete Subject");
			System.out.println("5. Exit");
			System.out.print("👉 Enter your choice: ");
			choice = scanner.nextInt();
			switch (choice) {
			case 1: {
				CourseSubjectController.addNewSubject(scanner);
				continue;
			}
			case 2: {
				CourseSubjectController.displayAllCoursesSubject();
				continue;
			}
			case 3: {
				CourseSubjectController.displayASubject(scanner);
				continue;
			}
			case 4: {
				CourseSubjectController.deleteASubject(scanner);
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
