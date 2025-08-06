package com.aurionpro.test;

import java.util.Scanner;

import com.aurionpro.controller.studentsController.EnrollmentController;
import com.aurionpro.facade.CourseFacade;
import com.aurionpro.facade.StudentFacade;
import com.aurionpro.facade.TeacherFacade;
import com.aurionpro.service.dashboardService.DashboardService;
import com.aurionpro.service.studentsService.StudentEnrollmentService;

public class StudentDaoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int choice = 0;
		while (choice!=5) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("\n📚 === Wellcome to Edutech! ===");
			System.out.println("Please choose from the options below:");
			System.out.println("1. Student Management Menu");
			System.out.println("2. Teacher Management Menu");
			System.out.println("3. Course Management Menu");
			System.out.println("4. View Dashboard");
			System.out.println("5. Exit");
			System.out.print("👉 Enter your choice: ");
			choice = scanner.nextInt();
			switch (choice) {
			case 1: {
				StudentFacade.StudentFacadeMenu(scanner);
				continue;
			}
			case 2: {
				TeacherFacade.displayTeacherMenu(scanner);
				continue;
			}
			case 3: {
				CourseFacade.displayCourseMenu(scanner);
				continue;
			}
			case 4: {
				new DashboardService().showDashboard(); 
				break;
			}
			default: {
				System.out.println("=== Thankyou for Using EduTech === ");
				break;
			}
			}
		}

	}
}
