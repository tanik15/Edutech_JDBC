package com.aurionpro.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.aurionpro.model.TeacherProfile;
import com.aurionpro.service.TeacherProfileService;

public class TeacherProfileController {
	private TeacherProfileService profileService = new TeacherProfileService();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n--- Teacher Profile Management ---");
            System.out.println("1. Add Teacher Profile");
            System.out.println("2. View All Profiles");
            System.out.println("3. Get Profile by Teacher ID");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addProfile();
                    break;
                case 2:
                    viewAllProfiles();
                    break;
                case 3:
                    viewProfileById();
                    break;
                case 4:
                    System.out.println("Exiting Teacher Profile Module.");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private void addProfile() {
        try {
            TeacherProfile profile = new TeacherProfile();

            System.out.print("Enter Teacher ID (must already exist in teacher table): ");
            profile.setTeacherId(scanner.nextInt());

            System.out.print("Enter Gender: ");
            profile.setGender(scanner.next());

            System.out.print("Enter Date of Birth (yyyy-mm-dd): ");
            profile.setDob(LocalDate.parse(scanner.next()));

            System.out.print("Enter Qualification: ");
            scanner.nextLine(); // Consume leftover newline
            profile.setQualification(scanner.nextLine());

            System.out.print("Enter Experience (years): ");
            profile.setExperience(scanner.nextDouble());

            System.out.print("Enter Joining Date (yyyy-mm-dd): ");
            profile.setJoiningDate(LocalDate.parse(scanner.next()));

            profileService.addProfile(profile);
            System.out.println("Profile added successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewAllProfiles() {
        try {
            List<TeacherProfile> profiles = profileService.getAllProfiles();
            if (profiles.isEmpty()) {
                System.out.println("No profiles found.");
            } else {
                for (TeacherProfile profile : profiles) {
                    printProfile(profile);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving profiles: " + e.getMessage());
        }
    }

    private void viewProfileById() {
        System.out.print("Enter Teacher ID: ");
        int id = scanner.nextInt();

        try {
            TeacherProfile profile = profileService.getProfileByTeacherId(id);
            if (profile == null) {
                System.out.println("No profile found for this ID.");
            } else {
                printProfile(profile);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching profile: " + e.getMessage());
        }
    }

    private void printProfile(TeacherProfile profile) {
        System.out.println("\n--- Profile Details ---");
        System.out.println("Teacher ID: " + profile.getTeacherId());
        System.out.println("Gender: " + profile.getGender());
        System.out.println("DOB: " + profile.getDob());
        System.out.println("Qualification: " + profile.getQualification());
        System.out.println("Experience: " + profile.getExperience() + " years");
        System.out.println("Joining Date: " + profile.getJoiningDate());
    }
}
