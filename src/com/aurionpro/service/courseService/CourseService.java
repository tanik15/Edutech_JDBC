package com.aurionpro.service.courseService;

import java.util.Scanner;

import com.aurionpro.controller.courseController.CourseController;
import com.aurionpro.controller.studentsController.EnrollmentController;

public class CourseService {
	public static void displayCourseMenu(Scanner scanner) {
		int choice = 0;
		while (choice != 6) {
			System.out.println("\n📚 === Course Menu ===");
			System.out.println("Please choose from the options below:");
			System.out.println("1️  Add new Course");
			System.out.println("2️  Display All Courses");
			System.out.println("3️  Display Course by ID");
			System.out.println("4  Delete Course");
			System.out.println("5  Assign Course to Student");
			System.out.println("6. Exit");
			System.out.print("👉 Enter your choice: ");
			choice = scanner.nextInt();
			switch (choice) {
			case 1: {
				CourseController.addnewCourse(scanner);
				continue;
			}
			case 2: {
				CourseController.displayAllCourses();
				continue;
			}
			case 3: {
				CourseController.displayAStudent(scanner);
				continue;
			}
			case 4: {
				CourseController.deleteACourse(scanner);
				continue;
			}
			case 5: {
				CourseController.assignCourse(scanner);
				continue;
			}
			case 6: {
				break;
			}
			default: {
				break;
			}
			}
		}
	}
}
