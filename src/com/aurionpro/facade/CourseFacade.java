package com.aurionpro.facade;

import java.util.Scanner;

import com.aurionpro.service.courseService.CourseService;
import com.aurionpro.service.courseService.CourseSubjectService;

public class CourseFacade {
	public static void displayCourseMenu(Scanner scanner) {
		int choice = 0;
		while (choice != 3) {
			System.out.println("\n📚 === Courses Operations ===");
			System.out.println("Please choose from the options below:");
			System.out.println("1. Course Menu");
			System.out.println("2. Subject Menu");
			System.out.println("3. Exit");
			System.out.print("👉 Enter your choice: ");
			choice = scanner.nextInt();
			switch (choice) {
			case 1: {
				CourseService.displayCourseMenu(scanner);
				continue;
			}
			case 2: {
				CourseSubjectService.displayCourseMenu(scanner);
				continue;
			}
			case 3: {
				break;
			}
			default: {
				break;
			}
			}
		}
	}
}
