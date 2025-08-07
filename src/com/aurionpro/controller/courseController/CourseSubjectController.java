package com.aurionpro.controller.courseController;

import java.util.List;
import java.util.Scanner;

import com.aurionpro.dao.courseDao.CourseSubjectDao;
import com.aurionpro.dao.courseDao.CoursesDao;
import com.aurionpro.model.courseModels.CourseModel;
import com.aurionpro.model.courseModels.CourseSubjectModel;
import com.aurionpro.model.studentsModel.StudentModel;

public class CourseSubjectController {

	private static void printSubjects(List<CourseSubjectModel> subjects) {
		System.out.println("------------------------------------------------------------------------");
		System.out.printf("| %-10s | %-25s | %-15s| %-10s |\n", "Subject Id", "Subject Name", "Semester", "Credits",
				"Description");
		System.out.println("------------------------------------------------------------------------");

		subjects.stream().forEach(subject -> {
			System.out.printf("| %-10s | %-25s | %-15s | %-10d |\n", subject.getSubjectId(), subject.getSubjectName(),
					subject.getSemester(), subject.getCredits());
		});

		System.out.println("------------------------------------------------------------------------");

	}

	public static void displayAllCoursesSubject() { // CourseSubjectDao
		List<CourseSubjectModel> subjects = CourseSubjectDao.getAllSubjects(true);
		System.out.println("\n\t  🎓 List of Registered Subjects ");
		printSubjects(subjects);

	}

	public static void displayASubject(Scanner scanner) {
		scanner.nextLine();
		System.out.print("🔹 Enter the Subject Id: ");
		String subjectId = scanner.nextLine();
		CourseSubjectModel subject = CourseSubjectDao.getASubject(subjectId);
		System.out.println("\n\t  🎓 Student With ID: " + subjectId);
		List<CourseSubjectModel> subjects = List.of(subject);
		printSubjects(subjects);
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

	public static void displayAllInActiveSubjects() {

		List<CourseSubjectModel> subjects = CourseSubjectDao.getAllSubjects(false);
		if (subjects.isEmpty()) {
			System.out.println("\n  === NO Subjects ===\n");
			return;
		}
		System.out.println("\n\t  🎓 List of Registered Subjects");
		printSubjects(subjects);
	}

	private static CourseSubjectModel getCommonSubjectDetails(Scanner scanner, String functionality) {
		System.out.println("\n📥 Enter Subject Details");
		scanner.nextLine();
		CourseSubjectModel subject = new CourseSubjectModel();
		if (functionality != "Name") {
			System.out.print("🔹 Enter Subject ID (e.g., MATH101): ");
			subject.setSubjectId(scanner.nextLine());
		}

		if (functionality != "ID") {
			System.out.print("🔹 Enter Subject Name: ");
			subject.setSubjectName(scanner.nextLine());
		}

		System.out.print("🔹 Enter Semester (Semester 1): ");
		subject.setSemester(scanner.nextLine());

		System.out.print("🔹 Enter Credits: ");
		subject.setCredits(scanner.nextInt());
		scanner.nextLine();
		return subject;
	}

	public static void updateASubjectById(Scanner scanner) {
		CourseSubjectModel course = getCommonSubjectDetails(scanner, "ID");
		System.out.print("🔹 Enter Subject Status (Active/Inactive): ");
		String status = scanner.nextLine();
		boolean isActive = true;
		if (status.equalsIgnoreCase("Inactive")) {
			isActive = false;
		}
		CourseSubjectDao.updateSubjectByID(course, isActive);
	}

	public static void updateASubjectByName(Scanner scanner) {
		CourseSubjectModel course = getCommonSubjectDetails(scanner, "Name");
		System.out.print("🔹 Enter Course Status (Active/Inactive): ");
		String status = scanner.nextLine();
		boolean isActive = true;
		if (status.equalsIgnoreCase("Inactive")) {
			isActive = false;
		}
		CourseSubjectDao.updateSubjectByName(course, isActive);
	}

	public static void addnewSubject(Scanner scanner) {
		CourseSubjectModel course = getCommonSubjectDetails(scanner, "Add");
		CourseSubjectDao.addNewSubject(course);

	}

	public static void assignSubjectToCourse(Scanner scanner) {
		displayAllCoursesSubject();
		CourseController.displayAllCourses();
		scanner.nextLine();
		System.out.print("🔹 Enter Subject ID (e.g., MATH101): ");
		String subjectId = scanner.nextLine();
		System.out.print("🔹 Enter Course ID (e.g., CSE101): ");
		String courseId = scanner.nextLine();
		CourseSubjectDao.assignSubjectCourse(subjectId, courseId);;
	}

}
