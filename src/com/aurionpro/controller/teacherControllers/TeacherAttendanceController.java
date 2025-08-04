package com.aurionpro.controller.teacherControllers;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.aurionpro.model.teachermodels.TeacherAttendance;
import com.aurionpro.service.teacherservices.TeacherAttendanceService;

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
            scanner.nextLine();

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
            scanner.nextLine(); 

            System.out.print("Enter Status (Present/Absent/On Leave): ");
            String status = scanner.nextLine();

            System.out.print("Enter Remarks: ");
            String remarks = scanner.nextLine();

            LocalDate date = LocalDate.now(); 

            TeacherAttendance attendance = new TeacherAttendance();
            attendance.setTeacherId(teacherId);
            attendance.setStatus(status);
            attendance.setRemarks(remarks);
            attendance.setDate(date);

            attendanceService.markAttendance(attendance);
            System.out.println("✅ Attendance marked successfully.");

        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ Database Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Unexpected Error: " + e.getMessage());
        }
    }

    private void viewAttendanceById() {
        try {
            System.out.print("Enter Attendance ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            TeacherAttendance attendance = attendanceService.getAttendanceByTeacherId(id);

            if (attendance != null) {
                printAttendanceHeader();
                printAttendanceRow(attendance);
            } else {
                System.out.println("⚠️ No attendance found with ID: " + id);
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void viewAllAttendance() {
        try {
            List<TeacherAttendance> list = attendanceService.getAllAttendance();

            if (list.isEmpty()) {
                System.out.println("⚠️ No attendance records available.");
            } else {
                printAttendanceHeader();
                for (TeacherAttendance attendance : list) {
                    printAttendanceRow(attendance);
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void printAttendanceHeader() {
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.printf("| %-12s | %-10s | %-12s | %-30s |\n", "Attendance ID", "Teacher ID", "Date", "Status & Remarks");
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    private void printAttendanceRow(TeacherAttendance a) {
        System.out.printf("| %-12d | %-10d | %-12s | %-30s |\n",
                a.getTeacherId(),
                a.getTeacherId(),
                a.getDate(),
                a.getStatus() + " - " + a.getRemarks());
    }
}
