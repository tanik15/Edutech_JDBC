package com.aurionpro.controller.courseController;

import java.util.List;
import java.util.Scanner;

import com.aurionpro.dao.courseDao.CourseSubjectDao;
import com.aurionpro.model.courseModels.CourseSubjectModel;

public class CourseSubjectController {
	public static void displayAllCoursesSubject() { //CourseSubjectDao
		List<CourseSubjectModel> subjects = CourseSubjectDao.getAllSubjects();
		System.out.println("\n\t  🎓 List of Registered Subjects ");
		System.out.println("------------------------------------------------------------------------");
		System.out.printf("| %-10s | %-25s | %-15s| %-10s |\n", "Subject Id", "Subject Name", "Semester", "Credits",
				"Description");
		System.out.println("------------------------------------------------------------------------");

		subjects.stream().forEach(subject -> {
			System.out.printf("| %-10s | %-25s | %-15s | %-10d |\n", subject.getSubjectId(),
					subject.getSubjectName(), subject.getSemester(), subject.getCredits());
		});

		System.out.println("------------------------------------------------------------------------");

	}

	public static void displayASubject(Scanner scanner) {
		scanner.nextLine();
		System.out.print("🔹 Enter the Subject Id: ");
		String subjectId = scanner.nextLine();
		CourseSubjectModel subject = CourseSubjectDao.getASubject(subjectId);
		System.out.println("\n\t  🎓 Student With ID: " + subjectId);
		System.out.println("------------------------------------------------------------------------");
		System.out.printf("| %-10s | %-25s | %-15s| %-10s |\n", "Subject Id", "Subject Name", "Semester", "Credits",
				"Description");
		System.out.println("------------------------------------------------------------------------");
		System.out.printf("| %-10s | %-25s | %-15s | %-10d |\n", subject.getSubjectId(),
				subject.getSubjectName(), subject.getSemester(), subject.getCredits());
		System.out.println("------------------------------------------------------------------------");
	}

	public static void deleteASubject(Scanner scanner) {
		scanner.nextLine();
		System.out.print("🔹 Enter the Subject Id: ");
		String subjectId = scanner.nextLine();
		System.out.println("\n⚠️  Are you sure you want to delete the Subject with ID: " + subjectId + "?");
		System.out.print("👉 Enter 'y' to confirm or 'n' to cancel: ");
		String choice = scanner.nextLine();
		if (choice.equalsIgnoreCase("y")) {
			CourseSubjectDao.deleteSubject(subjectId);
		}
	}

	public static void addNewSubject(Scanner scanner) {

		System.out.println("\n📥 Enter Subjects Details");
		
		CourseSubjectModel subject = new CourseSubjectModel();

        System.out.print("🔹 Enter Subject ID (e.g., MATH101): ");
        subject.setSubjectId(scanner.nextLine());

        System.out.print("🔹 Enter Subject Name: ");
        subject.setSubjectName(scanner.nextLine());

        System.out.print("🔹 Enter Subject Semester: ");
        subject.setSemester(scanner.nextLine());
        scanner.nextLine(); 

        System.out.print("🔹 Enter Subject Credits: ");
        subject.setCredits(scanner.nextInt());
        scanner.nextLine();
		CourseSubjectDao.addNewSubject(subject);

	}
}
