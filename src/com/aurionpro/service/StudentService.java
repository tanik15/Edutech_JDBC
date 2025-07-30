package com.aurionpro.service;

import java.util.Scanner;

import com.aurionpro.controller.StudentController;

public class StudentService {
	public static void displayStudentMenu() {
		System.out.println("\n📚 === Student Menu ===");
		System.out.println("Please choose from the options below:");
		System.out.println("1️  Display All Students");
		System.out.println("2️  Search a Student");
		System.out.println("3️  Add a New Student");
		System.out.println("4️  Delete a Student");
		System.out.print("👉 Enter your choice: ");
		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();
		switch (choice) {
		case 1: {
			StudentController.displayAllStudents();
			break;
		}
		case 2:{
			StudentController.displayAStudent();
			break;
		}
		case 3:{
			StudentController.addNewStudent();
			break;
		}
		case 4:{
			StudentController.deleteAStudent();
			break;
		}
		}
		scanner.close();
	}
}
