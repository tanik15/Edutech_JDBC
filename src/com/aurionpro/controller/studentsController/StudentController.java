package com.aurionpro.controller.studentsController;

import java.util.List;
import java.util.Scanner;

import com.aurionpro.dao.studentsDao.StudentDao;
import com.aurionpro.model.studentsModel.StudentCourseModel;
import com.aurionpro.model.studentsModel.StudentFeesModel;
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
	
	public static void displayAStudent(Scanner scanner) {

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
	}
	
	public static void deleteAStudent(Scanner scanner) {
		System.out.print("🔹 Enter the Student Id: ");
		int studentId = scanner.nextInt();
		scanner.nextLine();
		System.out.println("\n⚠️  Are you sure you want to delete the student with ID: " + studentId + "?");
		System.out.print("👉 Enter 'y' to confirm or 'n' to cancel: ");
		String choice = scanner.nextLine();
		if(choice.equalsIgnoreCase("y")) {
			StudentDao.deleteStudent(studentId);
		}
	}
	
	public static void addNewStudent(Scanner scanner) {

        System.out.println("\n📥 Enter Student Details");

        System.out.print("🔹 Enter Student Name: ");
        String studentName = scanner.nextLine();

        System.out.print("🔹 Enter Student Date of Birth (YYYY-MM-DD): ");
        String studentDOB = scanner.nextLine();
        
        StudentModel student = new StudentModel();
        student.setStudent_name(studentName);
        student.setStudent_DOB(studentDOB);
        StudentDao.addNewStudent(student);

	}
	
	public static void displayStudentCourses() {
	  	List<StudentCourseModel> studentcourses = StudentDao.getStudentCourses();
	  	studentcourses.stream().forEach((s)->{
	  		System.out.println(s.getStudentName());
	  	});
	  	System.out.println("\n 🎓 Students Courses");
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.printf("| %-15s | %-20s | %-43s |\n", "Student RollNo", "Student Name", "Courses");
		System.out.println("----------------------------------------------------------------------------------------");

		studentcourses.stream().forEach(student -> {
		    System.out.printf("| %-15d | %-20s | %-43s |\n",
		    		student.getstudentRollNumber(),
		    		student.getStudentName(),
		    		(student.getStudentCourses()== null)?"Not Selected":student.getStudentCourses()
		    	
		    );
		});
		System.out.println("----------------------------------------------------------------------------------------");
	  	
	}
	public static void displayStudentFees() {
		List<StudentCourseModel> studentcourses = StudentDao.getStudentCourses();
		studentcourses.stream().forEach((s)->{
			System.out.println(s.getStudentName());
		});
		System.out.println("\n 🎓 Students Fees");
		System.out.println("--------------------------------------------------------");
		System.out.printf("| %-15s | %-20s |%-12s |\n", "Student RollNo", "Student Name","Total Fees");
		System.out.println("--------------------------------------------------------");
		studentcourses.stream().forEach(student -> {
			System.out.printf("| %-15d | %-20s |%-12d |\n",
					student.getstudentRollNumber(),
					student.getStudentName(),
					student.getStudentFees()
					);
		});
		System.out.println("--------------------------------------------------------");
		
	}
	
	public static void displayStudentSubjects() {
	  	List<StudentCourseModel> studentcourses = StudentDao.getStudentSubjects();
	  	studentcourses.stream().forEach((s)->{
	  		System.out.println(s.getStudentName());
	  	});
	  	System.out.println("\n 🎓 Students Subjects");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.printf("| %-15s | %-20s | %-42s |\n", "Student RollNo", "Student Name", "Subjects");
		System.out.println("---------------------------------------------------------------------------------------");

		studentcourses.stream().forEach(student -> {
		    System.out.printf("| %-15d | %-20s | %-42s |\n",
		    		student.getstudentRollNumber(),
		    		student.getStudentName(),
		    		(student.getStudentSubjects()== null)?"Not Assigned":student.getStudentSubjects(),
		    		student.getStudentFees()
		    );
		});
		System.out.println("---------------------------------------------------------------------------------------");
	  	
	}
	
	
}
