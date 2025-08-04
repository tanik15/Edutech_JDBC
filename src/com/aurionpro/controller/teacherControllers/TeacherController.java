package com.aurionpro.controller.teacherControllers;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.aurionpro.model.teachermodels.Teacher;
import com.aurionpro.service.teacherservices.TeacherService;

public class TeacherController {
    private TeacherService teacherService;
    private Scanner scanner;

    public TeacherController() {
        this.teacherService = new TeacherService();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\n--- Teacher Management ---");
            System.out.println("1. Add Teacher");
            System.out.println("2. View All Active Teachers");
            System.out.println("3. View Teacher by ID");
            System.out.println("4. Delete (Soft) Teacher");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addTeacher();
                    break;
                case 2:
                    viewAllTeachers();
                    break;
                case 3:
                    viewTeacherById();
                    break;
                case 4:
                    softDeleteTeacher();
                    break;
                case 5:
                    System.out.println("Exiting Teacher Management.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addTeacher() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setEmail(email);
        teacher.setPhoneNumber(phone);
        teacher.setActive(true); // default active teacher

        try {
            boolean result = teacherService.addTeacher(teacher);
            if (result) {
                System.out.println("✅ Teacher added successfully.");
            } else {
                System.out.println("❌ Failed to add teacher.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error occurred while adding teacher.");
            e.printStackTrace();
        }
    }

    private void viewAllTeachers() {
        try {
            List<Teacher> teachers = teacherService.getAllTeachers();

            if (teachers.isEmpty()) {
                System.out.println("⚠️ No active teachers found.");
                return;
            }

            System.out.println("\n+------------+----------------------+----------------------------+-------------------+");
            System.out.println("| Teacher ID | Name                 | Email                      | Phone Number      |");
            System.out.println("+------------+----------------------+----------------------------+-------------------+");

            for (Teacher t : teachers) {
                System.out.printf("| %-10d | %-20s | %-26s | %-17s |\n",
                        t.getTeacherId(), t.getName(), t.getEmail(), t.getPhoneNumber());
            }

            System.out.println("+------------+----------------------+----------------------------+-------------------+");
        } catch (SQLException e) {
            System.out.println("❌ Error fetching teacher list.");
            e.printStackTrace();
        }
    }

    private void viewTeacherById() {
        System.out.print("Enter teacher ID: ");
        int id = scanner.nextInt();

        try {
            Teacher t = teacherService.getTeacherById(id);
            if (t == null) {
                System.out.println("⚠️ Teacher not found or is inactive.");
                return;
            }

            System.out.println("\n+------------+----------------------+----------------------------+-------------------+");
            System.out.println("| Teacher ID | Name                 | Email                      | Phone Number      |");
            System.out.println("+------------+----------------------+----------------------------+-------------------+");
            System.out.printf("| %-10d | %-20s | %-26s | %-17s |\n",
                    t.getTeacherId(), t.getName(), t.getEmail(), t.getPhoneNumber());
            System.out.println("+------------+----------------------+----------------------------+-------------------+");
        } catch (SQLException e) {
            System.out.println("❌ Error retrieving teacher.");
            e.printStackTrace();
        }
    }

    private void softDeleteTeacher() {
        System.out.print("Enter teacher ID to delete (soft): ");
        int id = scanner.nextInt();

        boolean result = teacherService.deleteTeacher(id);
        if (result) {
            System.out.println("🗑️ Teacher soft-deleted successfully.");
        } else {
            System.out.println("⚠️ Teacher not found or already inactive.");
        }
    }
}
