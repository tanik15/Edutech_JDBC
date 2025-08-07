package com.aurionpro.service.courseService;

import java.util.Scanner;

import com.aurionpro.controller.courseController.CourseSubjectController;


public class CourseSubjectService {
	public static void displayCourseMenu(Scanner scanner) {
		int choice = 0;
		while (choice != 9) {
			System.out.println("\n📚 === Subject Menu ===");
			System.out.println("Please choose from the options below:");
			System.out.println("1️  Add new Subject");
			System.out.println("2️  Display All Subject");
			System.out.println("3️  Display Subject by ID");
			System.out.println("4  Delete Subject");
			System.out.println("5  Display All InActive Subjects");
			System.out.println("6  Update Subjects Details By Id");
			System.out.println("7  Update Subjects Details By Name");
			System.out.println("8  Assign Subject to Course");
			System.out.println("9. Exit");
			System.out.print("👉 Enter your choice: ");
			choice = scanner.nextInt();
			switch (choice) {
			case 1: {
				CourseSubjectController.addnewSubject(scanner);
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
				CourseSubjectController.displayAllInActiveSubjects();
				continue;
			}
			case 6: {
				CourseSubjectController.updateASubjectById(scanner);
				continue;
			}
			case 7: {
				CourseSubjectController.updateASubjectByName(scanner);
				continue;
			}
			case 8: {
				CourseSubjectController.assignSubjectToCourse(scanner);
				continue;
			}
			default: {
				break;
			}
			}
		}
	}
}
