package com.aurionpro.facade;

import java.util.Scanner;

import com.aurionpro.controller.teacherControllers.TeacherActivityController;
import com.aurionpro.controller.teacherControllers.TeacherAttendanceController;
import com.aurionpro.controller.teacherControllers.TeacherController;
import com.aurionpro.controller.teacherControllers.TeacherProfileController;
import com.aurionpro.controller.teacherControllers.TeacherRatingController;
import com.aurionpro.controller.teacherControllers.TeacherSalaryController;
import com.aurionpro.controller.teacherControllers.TeacherSubjectController;

public class TeacherFacade {

	public static void displayTeacherMenu(Scanner scanner) {
		TeacherController teacherController = new TeacherController();
		TeacherProfileController profileController = new TeacherProfileController();
		TeacherAttendanceController attendanceController = new TeacherAttendanceController();
		TeacherSalaryController salaryController = new TeacherSalaryController();
		TeacherRatingController ratingController = new TeacherRatingController();
		TeacherActivityController activityController = new TeacherActivityController();
		TeacherSubjectController subjectController = new TeacherSubjectController();

		int choice = 0;

		while (choice != 8) {
			System.out.println("\n=== TEACHER MANAGEMENT MENU ===");
			System.out.println("1. Manage Teachers");
			System.out.println("2. Manage Teacher Profiles");
			System.out.println("3. Manage Attendance");
			System.out.println("4. Manage Salaries");
			System.out.println("5. Manage Ratings");
			System.out.println("6. Manage Activities");
			System.out.println("7. Manage Subjects Assigned to Teachers");
			System.out.println("8. Back to Main Menu");
			System.out.print("Enter your choice: ");

			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				teacherController.start();
				break;
			case 2:
				profileController.start();
				break;
			case 3:
				attendanceController.start();
				break;
			case 4:
				salaryController.start();
				break;
			case 5:
				ratingController.start();
				break;
			case 6:
				activityController.start();
				break;
			case 7:
				subjectController.start();
				break;
			case 8:
				System.out.println("Returning to main menu...");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}
}
