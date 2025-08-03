package com.aurionpro.facade;

import java.util.Scanner;

import com.aurionpro.service.studentsService.StudentEnrollmentService;
import com.aurionpro.service.studentsService.StudentExamService;
import com.aurionpro.service.studentsService.StudentProfileService;
import com.aurionpro.service.studentsService.StudentService;

public class StudentFacade {
	public static void StudentFacadeMenu(Scanner scanner) {
		int choice = 0;
		while (choice != 5) {
			System.out.println("1. Student Menu");
			System.out.println("2. Profile Menu");
			System.out.println("3. Enrollment Menu");
			System.out.println("4. Exam Menu");
			System.out.println("5. Exit");
			choice = scanner.nextInt();
			switch (choice) {
			case 1: {
				StudentService.displayStudentMenu(scanner);
				continue;
			}
			case 2: {
				StudentProfileService.displayProfielMenu(scanner);
				continue;
			}
			case 3: {
				StudentEnrollmentService.displayEnrollmentMenu(scanner);
				continue;
			}
			case 4: {
				StudentExamService.displayExamMenu(scanner);
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
