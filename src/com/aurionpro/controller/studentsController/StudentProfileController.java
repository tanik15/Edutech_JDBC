package com.aurionpro.controller.studentsController;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aurionpro.dao.studentsDao.studentProfileDao;
import com.aurionpro.model.studentsModel.StudentProfileModel;

public class StudentProfileController {
	private static String isValidPhoneNumber(String phoneNumber,Scanner scanner) {
		String regex = "^(\\+91|91)?[6-9][0-9]{9}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(phoneNumber);
		while(!matcher.matches()) {
			System.out.println("Enter Valid phone number");
			phoneNumber = scanner.nextLine();
			matcher = pattern.matcher(phoneNumber);
		}
		return phoneNumber;
	}
	private static String isValidEmail(String email, Scanner scanner) {
	    String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(email);

	    while (!matcher.matches()) {
	        System.out.println("Enter a valid email address:");
	        email = scanner.nextLine();
	        matcher = pattern.matcher(email);
	    }
	    return email;
	}

	private static StudentProfileModel getProfileDetails(Scanner scanner) {

        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Student Address: ");
        String address = scanner.nextLine();

        System.out.print("Enter Student Gender (M/F): ");
        String gender = scanner.nextLine();

        System.out.print("Enter Student Phone no.: ");
        String phone = scanner.nextLine();
        phone = isValidPhoneNumber(phone,scanner);
        
        System.out.print("Enter Student Email address: ");
        String email = scanner.nextLine();
        email = isValidEmail(email,scanner);

        StudentProfileModel profile = new StudentProfileModel(studentId,address,gender,phone,email);
        return profile;
	}
	
	public static void addNewProfile(Scanner scanner) {
		StudentProfileModel profile  = getProfileDetails(scanner);
		studentProfileDao.addStudentProfile(profile);
	}
	
	public static void updateProfileDetail(Scanner scanner) {
		StudentProfileModel profile  = getProfileDetails(scanner);
		studentProfileDao.updateStudentProfile(profile);
	}
	
	public static void displayStudentsProfile() {
		List<StudentProfileModel> profiles = studentProfileDao.getAllStudentProfiles();
		System.out.println("\n\t  🎓 Students Profile Details");
		System.out.println("----------------------------------------------------------------------------------------------");
		System.out.printf("%-10s |%-25s |%-10s |%-15s |%-25s|\n", "Student ID", "  Address", " Gender", "  Phone", "  Email");
		System.out.println("----------------------------------------------------------------------------------------------");
		profiles.stream().forEach(enrollment -> {
		    System.out.printf("%-10d | %-25s| %-10s| %-15s| %-24s|\n",
		    		enrollment.getStudentId(),
		    		enrollment.getStudentAddress(),
		    		enrollment.getStudentGender(),
		    		enrollment.getStudentPhone(),
		            enrollment.getStudentEmail());
		});
		System.out.println("----------------------------------------------------------------------------------------------");

	}
}
