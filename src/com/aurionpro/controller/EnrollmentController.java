package com.aurionpro.controller;

import java.util.List;
import java.util.Scanner;

import com.aurionpro.dao.StudentEnrollmentDao;
import com.aurionpro.model.studentsModel.StudentEnrollmentModel;

public class EnrollmentController {
	
	public static StudentEnrollmentModel getEnrollmentDetails() {
		Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();

        System.out.print("Enter Class ID: ");
        int classId = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter Enrollment Date (YYYY-MM-DD): ");
        String enrollmentDate = scanner.nextLine();

        System.out.print("Enter Academic Year (e.g., 2024-2025): ");
        String academicYear = scanner.nextLine();

        StudentEnrollmentModel enrollment = new StudentEnrollmentModel(studentId, classId, enrollmentDate, academicYear,"");
        scanner.close();
        return enrollment;
	}
	
	public static void addNewEnrollment() {
		StudentEnrollmentModel enrollment  = getEnrollmentDetails();
		StudentEnrollmentDao.addNewStudentEnrollment(enrollment);
        
	}
	
	public static void updateEnrollmentDetail() {
		StudentEnrollmentModel enrollment  = getEnrollmentDetails();
		StudentEnrollmentDao.updateStudentEnrollmentDetails(enrollment);
	}
	
	public static void displayAllEnrollmentStudents() {
		List<StudentEnrollmentModel> enrollments = StudentEnrollmentDao.getAllEnrollmentDetails();
		System.out.println("\n\t  🎓 Students Enrollment Details");
		System.out.println("--------------------------------------------------------------------------");
		System.out.printf("%-12s |%-10s |%-15s |%-15s |%-13s|\n", "Student ID", "Class ID", "Enroll Date", "Academic Year", "Status");
		System.out.println("--------------------------------------------------------------------------");
		enrollments.stream().forEach(enrollment -> {
		    System.out.printf("%-12d | %-10d| %-15s| %-15s| %-12s|\n",
		    		enrollment.getStudentId(),
		    		enrollment.getClassId(),
		    		enrollment.getEnrollmentDate(),
		    		enrollment.getAcademicYear(),
		            enrollment.getEnrollmentStatus());
		});
		System.out.println("--------------------------------------------------------------------------");

	}
}
