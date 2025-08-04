package com.aurionpro.controller.teacherControllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.aurionpro.model.teachermodels.TeacherActivity;
import com.aurionpro.service.teacherservices.TeacherActivityService;

public class TeacherActivityController {
    private TeacherActivityService activityService;
    private Scanner scanner;

    public TeacherActivityController() {
        activityService = new TeacherActivityService();
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\n--- Teacher Activity Menu ---");
            System.out.println("1. Add Activity");
            System.out.println("2. View All Activities");
            System.out.println("3. View Activities by Teacher ID");
            System.out.println("4. Exit Activity Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addActivity();
                    break;
                case 2:
                    displayAllActivities();
                    break;
                case 3:
                    displayActivitiesByTeacherId();
                    break;
                case 4:
                    System.out.println("Exiting Teacher Activity Menu...");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private void addActivity() {
        try {
            System.out.print("Enter Teacher ID: ");
            int teacherId = scanner.nextInt();
            scanner.nextLine(); // consume newline

            System.out.print("Enter Activity Name: ");
            String activityName = scanner.nextLine();

            System.out.print("Enter Description: ");
            String description = scanner.nextLine();

            System.out.print("Enter Activity Date (yyyy-mm-dd): ");
            String dateInput = scanner.nextLine();
            LocalDate activityDate = LocalDate.parse(dateInput);

            TeacherActivity activity = new TeacherActivity(0, teacherId, activityName, description, activityDate);
            activityService.addActivity(activity);

            System.out.println("Activity added successfully.");
        } catch (Exception e) {
            System.out.println("Error while adding activity: " + e.getMessage());
        }
    }

    private void displayAllActivities() {
        try {
            List<TeacherActivity> activities = activityService.getAllActivities();
            if (activities.isEmpty()) {
                System.out.println("No activities found.");
                return;
            }
            System.out.println("\nAll Activities:");
            printActivityTable(activities);
        } catch (Exception e) {
            System.out.println("Error retrieving activities: " + e.getMessage());
        }
    }

    private void displayActivitiesByTeacherId() {
        try {
            System.out.print("Enter Teacher ID: ");
            int teacherId = scanner.nextInt();
            scanner.nextLine();

            List<TeacherActivity> activities = activityService.getActivitiesByTeacherId(teacherId);
            if (activities.isEmpty()) {
                System.out.println("No activities found for teacher ID: " + teacherId);
                return;
            }

            System.out.println("\nActivities for Teacher ID " + teacherId + ":");
            printActivityTable(activities);
        } catch (Exception e) {
            System.out.println("Error retrieving activities: " + e.getMessage());
        }
    }

 
    private void printActivityTable(List<TeacherActivity> activities) {
        String format = "| %-11s | %-9s | %-20s | %-45s | %-13s |\n";
        System.out.println("+-------------+-----------+----------------------+-----------------------------------------------+--------------+");
        System.out.printf("| Activity ID | TeacherID | Activity Name         | Description                                   | Activity Date |\n");
        System.out.println("+-------------+-----------+----------------------+-----------------------------------------------+--------------+");
        for (TeacherActivity a : activities) {
            System.out.printf(format, a.getActivityId(), a.getTeacherId(), a.getActivityName(), a.getDescription(), a.getActivityDate());
        }
        System.out.println("+-------------+-----------+----------------------+-----------------------------------------------+--------------+");
    }
}
