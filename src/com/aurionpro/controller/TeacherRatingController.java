package com.aurionpro.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.aurionpro.model.TeacherRating;
import com.aurionpro.service.TeacherRatingService;

public class TeacherRatingController {
	private TeacherRatingService ratingService = new TeacherRatingService();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n--- Teacher Rating Menu ---");
            System.out.println("1. Add Rating");
            System.out.println("2. View All Ratings");
            System.out.println("3. View Ratings by Teacher ID");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addRating();
                    break;
                case 2:
                    viewAllRatings();
                    break;
                case 3:
                    viewRatingsByTeacherId();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private void addRating() {
        try {
            System.out.print("Enter Teacher ID: ");
            int teacherId = scanner.nextInt();
            System.out.print("Enter Rating (1 to 5): ");
            float ratingValue = scanner.nextFloat();
            scanner.nextLine(); // consume newline
            System.out.print("Enter Your Name: ");
            String givenBy = scanner.nextLine();
            System.out.print("Enter Comment: ");
            String comment = scanner.nextLine();

            TeacherRating rating = new TeacherRating();
            rating.setTeacherId(teacherId);
            rating.setRatingValue(ratingValue);
            rating.setGivenBy(givenBy);
            rating.setComment(comment);
            rating.setDate(LocalDate.now());

            ratingService.addRating(rating);
            System.out.println("Rating added successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewAllRatings() {
        try {
            List<TeacherRating> ratings = ratingService.getAllRatings();
            if (ratings.isEmpty()) {
                System.out.println("No ratings found.");
            } else {
                for (TeacherRating rating : ratings) {
                    System.out.println(rating);
                }
            }
        } catch (Exception e) {
            System.out.println("Error retrieving ratings: " + e.getMessage());
        }
    }

    private void viewRatingsByTeacherId() {
        try {
            System.out.print("Enter Teacher ID: ");
            int teacherId = scanner.nextInt();

            List<TeacherRating> ratings = ratingService.getRatingsByTeacherId(teacherId);
            if (ratings.isEmpty()) {
                System.out.println("No ratings found for Teacher ID: " + teacherId);
            } else {
                for (TeacherRating rating : ratings) {
                    System.out.println(rating);
                }
            }
        } catch (Exception e) {
            System.out.println("Error retrieving ratings: " + e.getMessage());
        }
    }
}
