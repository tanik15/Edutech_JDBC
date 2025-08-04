package com.aurionpro.controller.teacherControllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.aurionpro.model.teachermodels.TeacherRating;
import com.aurionpro.service.teacherservices.TeacherRatingService;

public class TeacherRatingController {
    private TeacherRatingService ratingService = new TeacherRatingService();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n====== Teacher Rating Management ======");
            System.out.println("1. Add Rating");
            System.out.println("2. View All Ratings");
            System.out.println("3. View Ratings by Teacher ID");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    addRating();
                    break;
                case "2":
                    viewAllRatings();
                    break;
                case "3":
                    viewRatingsByTeacherId();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("❌ Invalid choice! Please enter 1-4.");
            }
        }
    }

    private void addRating() {
        try {
            System.out.print("Enter Teacher ID: ");
            int teacherId = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter Rating (1 to 5): ");
            float ratingValue = Float.parseFloat(scanner.nextLine());

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

            boolean success = ratingService.addRating(rating);
            if (success) {
                System.out.println("✅ Rating added successfully!");
            } else {
                System.out.println("❌ Failed to add rating. Please check your input or try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid number input. Please enter valid numeric values.");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void viewAllRatings() {
        try {
            List<TeacherRating> ratings = ratingService.getAllRatings();
            if (ratings.isEmpty()) {
                System.out.println("ℹ️ No ratings found.");
                return;
            }

            printHeader();
            for (TeacherRating rating : ratings) {
                printRatingRow(rating);
            }
            printFooter();

        } catch (Exception e) {
            System.out.println("❌ Error retrieving ratings: " + e.getMessage());
        }
    }

    private void viewRatingsByTeacherId() {
        try {
            System.out.print("Enter Teacher ID: ");
            int teacherId = Integer.parseInt(scanner.nextLine());

            List<TeacherRating> ratings = ratingService.getRatingsByTeacherId(teacherId);
            if (ratings.isEmpty()) {
                System.out.println("ℹ️ No ratings found for Teacher ID: " + teacherId);
                return;
            }

            printHeader();
            for (TeacherRating rating : ratings) {
                printRatingRow(rating);
            }
            printFooter();

        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid ID format. Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("❌ Error retrieving ratings: " + e.getMessage());
        }
    }

    private void printHeader() {
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.printf("| %-6s | %-10s | %-6s | %-12s | %-15s | %-20s |\n", 
                          "ID", "TeacherID", "Rating", "Date", "Given By", "Comment");
        System.out.println("--------------------------------------------------------------------------------------------");
    }

    private void printRatingRow(TeacherRating rating) {
        System.out.printf("| %-6d | %-10d | %-6.1f | %-12s | %-15s | %-20s |\n",
                          rating.getRatingId(),
                          rating.getTeacherId(),
                          rating.getRatingValue(),
                          rating.getDate(),
                          rating.getGivenBy(),
                          rating.getComment());
    }

    private void printFooter() {
        System.out.println("--------------------------------------------------------------------------------------------");
    }
}
