package com.aurionpro.controller.teacherControllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.aurionpro.model.teachermodels.TeacherSalary;
import com.aurionpro.service.teacherservices.TeacherSalaryService;

public class TeacherSalaryController {
    private static TeacherSalaryService salaryService = new TeacherSalaryService();
    private static Scanner sc = new Scanner(System.in);

    public static void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n====== Teacher Salary Management ======");
            System.out.println("1. Add Salary");
            System.out.println("2. View All Salaries");
            System.out.println("3. View Salary by ID");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            String input = sc.nextLine();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Please enter a valid number for choice.");
                continue;
            }

            switch (choice) {
                case 1:
                    addSalary();
                    break;
                case 2:
                    viewAllSalaries();
                    break;
                case 3:
                    viewSalaryById();
                    break;
                case 4:
                    running = false;
                    System.out.println("✅ Exiting Teacher Salary Module.");
                    break;
                default:
                    System.out.println("⚠️ Invalid choice! Please try again.");
            }
        }
    }

    private static void addSalary() {
        try {
            System.out.print("Enter Teacher ID: ");
            int teacherId = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Salary Amount: ");
            double amount = Double.parseDouble(sc.nextLine());

            System.out.print("Enter Pay Date (yyyy-mm-dd): ");
            String payDate = sc.nextLine();

            System.out.print("Enter Bonus: ");
            double bonus = Double.parseDouble(sc.nextLine());

            System.out.print("Enter Deductions: ");
            double deductions = Double.parseDouble(sc.nextLine());

            TeacherSalary salary = new TeacherSalary(0, teacherId, amount, LocalDate.parse(payDate), bonus, deductions);
            salaryService.addSalary(salary);

        } catch (NumberFormatException e) {
            System.out.println("⚠️ Please enter valid numeric values for ID, amount, bonus, and deductions.");
        } catch (Exception e) {
            System.out.println("⚠️ Error: " + e.getMessage());
        }
    }

    private static void viewAllSalaries() {
        try {
            List<TeacherSalary> list = salaryService.getAllSalaries();
            if (list.isEmpty()) {
                System.out.println("No salary records found.");
                return;
            }

            System.out.println("\n--------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-10s | %-10s | %-12s | %-8s | %-11s |\n",
                    "ID", "Teacher ID", "Amount", "Pay Date", "Bonus", "Deductions");
            System.out.println("--------------------------------------------------------------------------");

            for (TeacherSalary salary : list) {
                System.out.printf("| %-5d | %-10d | %-10.2f | %-12s | %-8.2f | %-11.2f |\n",
                        salary.getSalaryId(),
                        salary.getTeacherId(),
                        salary.getSalaryAmount(),
                        salary.getPayDate(),
                        salary.getBonus(),
                        salary.getDeductions());
            }

            System.out.println("--------------------------------------------------------------------------");

        } catch (Exception e) {
            System.out.println("⚠️ Error fetching salaries: " + e.getMessage());
        }
    }

    private static void viewSalaryById() {
        try {
            System.out.print("Enter Salary ID: ");
            int salaryId = Integer.parseInt(sc.nextLine());
            TeacherSalary salary = salaryService.getSalariesByTeacherId(salaryId);

            if (salary == null) {
                System.out.println("⚠️ Salary not found with ID: " + salaryId);
                return;
            }

            System.out.println("\n--------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-10s | %-10s | %-12s | %-8s | %-11s |\n",
                    "ID", "Teacher ID", "Amount", "Pay Date", "Bonus", "Deductions");
            System.out.println("--------------------------------------------------------------------------");

            System.out.printf("| %-5d | %-10d | %-10.2f | %-12s | %-8.2f | %-11.2f |\n",
                    salary.getSalaryId(),
                    salary.getTeacherId(),
                    salary.getSalaryAmount(),
                    salary.getPayDate(),
                    salary.getBonus(),
                    salary.getDeductions());

            System.out.println("--------------------------------------------------------------------------");

        } catch (NumberFormatException e) {
            System.out.println("⚠️ Please enter a valid number for Salary ID.");
        } catch (Exception e) {
            System.out.println("⚠️ Error fetching salary: " + e.getMessage());
        }
    }
}
