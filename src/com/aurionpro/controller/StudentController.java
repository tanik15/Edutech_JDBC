package com.aurionpro.controller;

import java.util.List;
import java.util.Scanner;

import com.aurionpro.dao.StudentDao;
import com.aurionpro.model.studentsModel.StudentModel;

public class StudentController {

	public static void displayAllStudents() {
		List<StudentModel> students = StudentDao.getAllStudents();
		System.out.println("\n\t  🎓 List of Registered Students");
		System.out.println("--------------------------------------------------------------");
		System.out.printf("| %-10s | %-20s | %-10s | %-10s |\n", "Student ID", "Name", "Roll No.", "DOB");
		System.out.println("--------------------------------------------------------------");

		students.stream().forEach(student -> {
		    System.out.printf("| %-10d | %-20s | %-10s | %-10s |\n",
		        student.getStudent_id(),
		        student.getStudent_name(),
		        student.getStudent_rollnumber(),
		        student.getStudent_DOB().toString()
		    );
		});

		System.out.println("--------------------------------------------------------------");

	}
	
	public static void displayAStudent() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("🔹 Enter the Student Id: ");
		int studentId = scanner.nextInt();
		StudentModel student = StudentDao.getAStudent(studentId);
		System.out.println("\n\t  🎓 Student With ID: "+studentId);
		System.out.println("--------------------------------------------------------------");
		System.out.printf("| %-10s | %-20s | %-10s | %-10s |\n", "Student ID", "Name", "Roll No.", "DOB");
		System.out.println("--------------------------------------------------------------");
		System.out.printf("| %-10d | %-20s | %-10s | %-10s |\n",
		        student.getStudent_id(),
		        student.getStudent_name(),
		        student.getStudent_rollnumber(),
		        student.getStudent_DOB().toString()
		    );
		System.out.println("--------------------------------------------------------------");
		scanner.close();

	}
	
	public static void deleteAStudent() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("🔹 Enter the Student Id: ");
		int studentId = scanner.nextInt();
		System.out.println("\n⚠️  Are you sure you want to delete the student with ID: " + studentId + "?");
		System.out.print("👉 Enter 'y' to confirm or 'n' to cancel: ");
		String choice = scanner.nextLine();
		if(choice.equalsIgnoreCase("y")) {
			StudentDao.deleteStudent(studentId);
		}
		scanner.close();
	}
	
	public static void addNewStudent() {
		Scanner scanner = new Scanner(System.in);

        System.out.println("\n📥 Enter Student Details");

        System.out.print("🔹 Enter Student Name: ");
        String studentName = scanner.nextLine();

        System.out.print("🔹 Enter Student Date of Birth (YYYY-MM-DD): ");
        String studentDOB = scanner.nextLine();
        
        StudentModel student = new StudentModel();
        student.setStudent_name(studentName);
        student.setStudent_DOB(studentDOB);
        StudentDao.addNewStudent(student);
        scanner.close();
	}
}
