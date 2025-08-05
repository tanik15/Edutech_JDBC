package com.aurionpro.service.courseService;

import java.util.Scanner;
import com.aurionpro.controller.courseController.CourseController;

public class CourseService {
	public static void displayCourseMenu(Scanner scanner) {
		int choice = 0;
		while (choice != 11) {
			System.out.println("\n📚 === Course Menu ===");
			System.out.println("Please choose from the options below:");
			System.out.println("1️  Add new Course");
			System.out.println("2️  Display All Courses");
			System.out.println("3️  Display Course by ID");
			System.out.println("4  Delete Course");
			System.out.println("5  Assign Course to Student");
			System.out.println("6  Display All InActive Courses");
			System.out.println("7  Update Course Details By Id");
			System.out.println("8  Update Course Details By Name");
			System.out.println("9  Display All Students in the course");
			System.out.println("10 Display All Subjects in the course");
			System.out.println("11. Exit");
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
				CourseController.displayACourse(scanner);
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
				CourseController.displayAllInActiveCourses();
				continue;
			}
			case 7: {
				CourseController.updateACourseById(scanner);
				continue;
			}
			case 8: {
				CourseController.updateACourseByName(scanner);
				continue;
			}
			case 9: {
				CourseController.displayStudentsInCourse(scanner);
				continue;
			}
			case 10: {
				CourseController.displaySubjectsInCourse(scanner);
				continue;
			}
			default: {
				break;
			}
			}
		}
	}
}
