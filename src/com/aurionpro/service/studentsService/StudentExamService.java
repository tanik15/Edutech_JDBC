package com.aurionpro.service.studentsService;

import java.util.Scanner;

import com.aurionpro.controller.studentsController.StudentExamsController;

public class StudentExamService {

	public static void displayExamMenu(Scanner scanner) {
		int choice = 0;
		while (choice !=3) {
			System.out.println("\n📚 === Exam Menu ===");
			System.out.println("Please choose from the options below:");
			System.out.println("1️  Add New Exam");
			System.out.println("2️  View All Exams");
			System.out.println("3. Exit");
			System.out.print("👉 Enter your choice: ");
			choice = scanner.nextInt();
			switch (choice) {
			case 1: {
				StudentExamsController.addNewExam(scanner);
				continue;
			}
			case 2: {
				StudentExamsController.viewAllExams();
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
