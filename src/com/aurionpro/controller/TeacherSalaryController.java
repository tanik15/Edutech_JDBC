package com.aurionpro.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.aurionpro.model.TeacherSalary;
import com.aurionpro.service.TeacherSalaryService;

public class TeacherSalaryController {
	 private static TeacherSalaryService salaryService = new TeacherSalaryService();
	    private static Scanner sc = new Scanner(System.in);

	    public static void start() {
	        boolean running = true;
	        while (running) {
	            System.out.println("\n------ Teacher Salary Management ------");
	            System.out.println("1. Add Salary");
	            System.out.println("2. View All Salaries");
	            System.out.println("3. View Salary by ID");
	            System.out.println("4. Exit");
	            System.out.print("Enter your choice: ");

	            int choice = sc.nextInt();
	            sc.nextLine(); // consume newline

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
	                    System.out.println("Exiting Teacher Salary Module.");
	                    break;
	                default:
	                    System.out.println("Invalid choice! Please try again.");
	            }
	        }
	    }

	    private static void addSalary() {
	        try {
	            System.out.print("Enter Teacher ID: ");
	            int teacherId = sc.nextInt();
	            System.out.print("Enter Salary Amount: ");
	            double amount = sc.nextDouble();
	            sc.nextLine(); // consume newline
	            System.out.print("Enter Pay Date (yyyy-mm-dd): ");
	            String payDate = sc.nextLine();
	            System.out.print("Enter Bonus: ");
	            double bonus = sc.nextDouble();
	            System.out.print("Enter Deductions: ");
	            double deductions = sc.nextDouble();

	            TeacherSalary salary = new TeacherSalary(0, teacherId, amount, LocalDate.parse(payDate), bonus, deductions);
	            salaryService.addSalary(salary);
	        } catch (Exception e) {
	            System.out.println("Error: " + e.getMessage());
	        }
	    }

	    private static void viewAllSalaries() {
	        try {
	            List<TeacherSalary> list = salaryService.getAllSalaries();
	            for (TeacherSalary salary : list) {
	                System.out.println(salary);
	            }
	        } catch (Exception e) {
	            System.out.println("Error fetching salaries: " + e.getMessage());
	        }
	    }

	    private static void viewSalaryById() {
	        try {
	            System.out.print("Enter Salary ID: ");
	            int salaryId = sc.nextInt();
	            TeacherSalary salary = salaryService.getSalariesByTeacherId(salaryId);
	            if (salary != null) {
	                System.out.println(salary);
	            } else {
	                System.out.println("Salary not found with ID: " + salaryId);
	            }
	        } catch (Exception e) {
	            System.out.println("Error fetching salary: " + e.getMessage());
	        }
	    }
	}

