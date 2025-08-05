package com.aurionpro.controller.teacherControllers;

import java.util.List;
import java.util.Scanner;

import com.aurionpro.model.teachermodels.TeacherSubject;
import com.aurionpro.service.teacherservices.TeacherSubjectService;

public class TeacherSubjectController {
	 private TeacherSubjectService service = new TeacherSubjectService();
	    private Scanner scanner = new Scanner(System.in);

	    public void start() {
	        while (true) {
	            System.out.println("\n--- TEACHER SUBJECT MANAGEMENT ---");
	            System.out.println("1. Assign Subject to Teacher");
	            System.out.println("2. View All Subjects assigned to teacher");
	            System.out.println("3. Back to Main Menu");
	            System.out.print("Enter your choice: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine(); 

	            switch (choice) {
	                case 1:
	                    assignSubject();
	                    break;
	                case 2:
	                    viewAssignments();
	                    break;
	                case 3:
	                    return;
	                default:
	                    System.out.println("Invalid choice. Try again.");
	            }
	        }
	    }

	    private void assignSubject() {
	        System.out.print("Enter Teacher ID: ");
	        int teacherId = scanner.nextInt();
	        scanner.nextLine(); 
	        System.out.print("Enter Subject ID (e.g., CS101): ");
	        String subjectId = scanner.nextLine();

	        boolean success = service.assignSubject(teacherId, subjectId);
	        if (success) {
	            System.out.println("✅ Subject assigned successfully!");
	        } else {
	            System.out.println("❌ Failed to assign subject.");
	        }
	    }

	    private void viewAssignments() {
	        List<TeacherSubject> assignments = service.getAllAssignments();
	        if (assignments.isEmpty()) {
	            System.out.println("No assignments found.");
	            return;
	        }

	        System.out.println("\n+------------+-----------------+");
	        System.out.println("| Teacher ID | Subject ID      |");
	        System.out.println("+------------+-----------------+");
	        for (TeacherSubject ts : assignments) {
	            System.out.println(ts);
	        }
	        System.out.println("+------------+-----------------+");
	    }
	}

