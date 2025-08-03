package com.aurionpro.service;

import java.util.Scanner;
import com.aurionpro.controller.StudentProfileController;

public class StudentProfileService {

	public static void displayProfielMenu(Scanner scanner) {
		int choice = 0;
		while (choice !=4) {
			System.out.println("\n📚 === Profile Menu ===");
			System.out.println("Please choose from the options below:");
			System.out.println("1️  Add Student Profile");
			System.out.println("2️  Update Student Profile");
			System.out.println("3️  Display All Students Profile Details");
			System.out.println("4. Exit");
			System.out.print("👉 Enter your choice: ");
			choice = scanner.nextInt();
			switch (choice) {
			case 1: {
				StudentProfileController.addNewProfile();
				continue;
			}
			case 2: {
				StudentProfileController.updateProfileDetail();
				continue;
			}
			case 3: {
				StudentProfileController.displayStudentsProfile();
				continue;
			}
			case 4: {
				break;
			}
			default: {
				break;
			}
			}
		}
	}

}
