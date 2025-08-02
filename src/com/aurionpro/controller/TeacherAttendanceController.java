package com.aurionpro.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.aurionpro.model.TeacherAttendance;
import com.aurionpro.service.TeacherAttendanceService;

public class TeacherAttendanceController {
	 private TeacherAttendanceService attendanceService = new TeacherAttendanceService();
	    private Scanner scanner = new Scanner(System.in);

	    public void start() {
	        while (true) {
	            System.out.println("\n🎯 Teacher Attendance Menu:");
	            System.out.println("1. Mark Attendance");
	            System.out.println("2. View Attendance by ID");
	            System.out.println("3. View All Attendance Records");
	            System.out.println("4. Exit");
	            System.out.print("Enter your choice: ");

	            int choice = scanner.nextInt();
	            scanner.nextLine(); // consume newline

	            switch (choice) {
	                case 1:
	                    markAttendance();
	                    break;
	                case 2:
	                    viewAttendanceById();
	                    break;
	                case 3:
	                    viewAllAttendance();
	                    break;
	                case 4:
	                    System.out.println("Exiting Attendance Controller...");
	                    return;
	                default:
	                    System.out.println("❌ Invalid choice. Try again.");
	            }
	        }
	    }

	    private void markAttendance() {
	        try {
	            System.out.print("Enter Teacher ID: ");
	            int teacherId = scanner.nextInt();
	            scanner.nextLine(); // consume newline

	            System.out.print("Enter Status (Present/Absent/On Leave): ");
	            String status = scanner.nextLine();

	            System.out.print("Enter Remarks: ");
	            String remarks = scanner.nextLine();

	            LocalDate date = LocalDate.now(); // Automatically use today's date

	            TeacherAttendance attendance = new TeacherAttendance();
	            attendance.setTeacherId(teacherId);
	            attendance.setStatus(status);
	            attendance.setRemarks(remarks);
	            attendance.setDate(date);

	            attendanceService.markAttendance(attendance);
	            System.out.println("Attendance marked successfully.");

	        } catch (Exception e) {
	            System.out.println("Error: " + e.getMessage());
	        }
	    }

	    private void viewAttendanceById() {
	        try {
	            System.out.print("Enter Attendance ID: ");
	            int id = scanner.nextInt();
	            scanner.nextLine(); // consume newline

	            TeacherAttendance attendance = attendanceService.getAttendanceByTeacherId(id);

	            if (attendance != null) {
	                System.out.println(attendance);
	            } else {
	                System.out.println("No attendance found with ID: " + id);
	            }
	        } catch (Exception e) {
	            System.out.println("Error: " + e.getMessage());
	        }
	    }

	    private void viewAllAttendance() {
	        try {
	            List<TeacherAttendance> list = attendanceService.getAllAttendance();

	            if (list.isEmpty()) {
	                System.out.println("No attendance records available.");
	            } else {
	                for (TeacherAttendance attendance : list) {
	                    System.out.println(attendance);
	                }
	            }
	        } catch (SQLException e) {
	            System.out.println("Error: " + e.getMessage());
	        }
	    }
}
