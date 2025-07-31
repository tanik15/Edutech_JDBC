package com.aurionpro.service;

import java.util.Scanner;

import com.aurionpro.controller.StudentExamsController;

public class StudentExamService {
	
	
	public static void displayExamMenu(Scanner scanner) {
		System.out.println("\n📚 === Exam Menu ===");
		System.out.println("Please choose from the options below:");
		System.out.println("1️  Add New Exam");
		System.out.println("2️  View All Exams");
		System.out.print("👉 Enter your choice: ");
		int choice = scanner.nextInt();
		switch (choice) {
		case 1: {
			StudentExamsController.addNewExam();
			break;
		}
		case 2: {
			StudentExamsController.viewAllExams();
			break;
		}
		}
	}
}
