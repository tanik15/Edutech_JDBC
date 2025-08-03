package com.aurionpro.controller.courseController;

import java.util.List;
import java.util.Scanner;

import com.aurionpro.dao.courseDao.CoursesDao;
import com.aurionpro.dao.studentsDao.StudentDao;
import com.aurionpro.model.courseModels.CourseModel;

public class CourseController {
	public static void displayAllCourses() {
		List<CourseModel> courses = CoursesDao.getAllCourses();
		System.out.println("\n\t  🎓 List of Registered Courses");
		System.out.println("-----------------------------------------------------------------------------------------------------");
		System.out.printf("| %-10s | %-25s | %-10s | %-10s | %-30s |\n", "Course ID", "Course Name", "Duration", "Fees",
				"Description");
		System.out.println("-----------------------------------------------------------------------------------------------------");

		courses.stream().forEach(course -> {
			System.out.printf("| %-10s | %-25s | %-10d | %-10s | %-30s |\n", course.getCourseId(),
					course.getCourseName(), course.getCourseDuration(), course.getCourseFees(),
					course.getDescription());
		});

		System.out.println("-----------------------------------------------------------------------------------------------------");

	}

	public static void displayAStudent(Scanner scanner) {
		scanner.nextLine();
		System.out.print("🔹 Enter the Course Id: ");
		String courseId = scanner.nextLine();
		CourseModel course = CoursesDao.getACourse(courseId);
		if(course==null) {
			return;
		}
		System.out.println("\n\t  🎓 Student With ID: " + courseId);
		System.out.println("-----------------------------------------------------------------------------------------------------");
		System.out.printf("| %-10s | %-20s | %-10s | %-10s | %-25s |\n", "Course ID", "Course Name", "Duration", "Fees",
				"Description");
		System.out.println("-----------------------------------------------------------------------------------------------------");
		System.out.printf("| %-10s | %-25s | %-10d | %-10s | %-25s |\n", course.getCourseId(), course.getCourseName(),
				course.getCourseDuration(), course.getCourseFees(), course.getDescription());
		System.out.println("-----------------------------------------------------------------------------------------------------");
	}

	public static void deleteACourse(Scanner scanner) {
		System.out.print("🔹 Enter the Course Id: ");
		String courseId = scanner.nextLine();
		scanner.nextLine();
		System.out.println("\n⚠️  Are you sure you want to delete the Course with ID: " + courseId + "?");
		System.out.print("👉 Enter 'y' to confirm or 'n' to cancel: ");
		String choice = scanner.nextLine();
		if (choice.equalsIgnoreCase("y")) {
			CoursesDao.checkCourse(courseId);
		}
	}

	public static void addnewCourse(Scanner scanner) {

		System.out.println("\n📥 Enter Course Details");
		
		CourseModel course = new CourseModel();

        System.out.print("🔹 Enter Course ID (e.g., CSE101): ");
        course.setCourseId(scanner.nextLine());

        System.out.print("🔹 Enter Course Name: ");
        course.setCourseName(scanner.nextLine());

        System.out.print("🔹 Enter Course Duration (in years): ");
        course.setCourseDuration(scanner.nextInt());
        scanner.nextLine(); // consume newline

        System.out.print("🔹 Enter Description: ");
        course.setDescription(scanner.nextLine());

        System.out.print("🔹 Enter Course Fees: ");
        course.setCourseFees(scanner.nextInt());
		CoursesDao.addNewCourse(course);

	}
	
	public static void assignCourse(Scanner scanner) {
		System.out.print("🔹 Enter Student Id: ");
		int studentId = scanner.nextInt();
		scanner.nextLine();
		if(StudentDao.checkStudent(studentId)) {
			System.out.print("🔹 Enter Course ID (e.g., CSE101): ");			
			String courseId = scanner.nextLine();
			CoursesDao.assignCourseStudent(courseId, studentId);
			return;
		}
		
	}
}
