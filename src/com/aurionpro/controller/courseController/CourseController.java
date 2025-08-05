package com.aurionpro.controller.courseController;

import java.util.List;
import java.util.Scanner;

import com.aurionpro.dao.courseDao.CoursesDao;
import com.aurionpro.dao.studentsDao.StudentDao;
import com.aurionpro.model.courseModels.CourseModel;
import com.aurionpro.model.courseModels.CourseSubjectModel;
import com.aurionpro.model.studentsModel.StudentModel;

public class CourseController {

	private static void printCourses(List<CourseModel> courses) {
		
		System.out.println(
				"-----------------------------------------------------------------------------------------------------");
		System.out.printf("| %-10s | %-25s | %-10s | %-10s | %-30s |\n", "Course ID", "Course Name", "Duration", "Fees",
				"Description");
		System.out.println(
				"-----------------------------------------------------------------------------------------------------");

		courses.stream().forEach(course -> {
			System.out.printf("| %-10s | %-25s | %-10d | %-10s | %-30s |\n", course.getCourseId(),
					course.getCourseName(), course.getCourseDuration(), course.getCourseFees(),
					course.getDescription());
		});

		System.out.println(
				"-----------------------------------------------------------------------------------------------------");
	}

	public static void displayAllCourses() {
		List<CourseModel> courses = CoursesDao.getAllCourses(true);
		if(courses.isEmpty()) {
			System.out.println("\n  === NO Courses ===\n");
			return;
		}
		System.out.println("\n\t  🎓 List of Registered Courses");
		printCourses(courses);

	}

	public static void displayAllInActiveCourses() {
		
		List<CourseModel> courses = CoursesDao.getAllCourses(false);
		if(courses.isEmpty()) {
			System.out.println("\n  === NO Courses ===\n");
			return;
		}
		System.out.println("\n\t  🎓 List of Registered Courses");
		printCourses(courses);
	}

	public static void displayACourse(Scanner scanner) {
		scanner.nextLine();
		System.out.print("🔹 Enter the Course Id: ");
		String courseId = scanner.nextLine();
		CourseModel course = CoursesDao.getACourse(courseId);
		if (course == null) {
			return;
		}
		System.out.println("\n\t  🎓 Student With ID: " + courseId);
		List<CourseModel> courses = List.of(course);
		printCourses(courses);
		}

	public static void deleteACourse(Scanner scanner) {
		scanner.nextLine();
		System.out.print("🔹 Enter the Course Id: ");
		String courseId = scanner.nextLine();
		System.out.println("\n⚠️  Are you sure you want to delete the Course with ID: " + courseId + "?");
		System.out.print("👉 Enter 'y' to confirm or 'n' to cancel: ");
		String choice = scanner.nextLine();
		if (choice.equalsIgnoreCase("y")) {
			CoursesDao.deleteCourse(courseId);
		}
	}

	private static CourseModel getCommonCourseDetails(Scanner scanner,String functionality) {
		System.out.println("\n📥 Enter Course Details");
		scanner.nextLine();
		CourseModel course = new CourseModel();
		if(functionality!="Name") {
		System.out.print("🔹 Enter Course ID (e.g., CSE101): ");
		course.setCourseId(scanner.nextLine());
		}
		
		if(functionality!= "ID") {
		System.out.print("🔹 Enter Course Name: ");
		course.setCourseName(scanner.nextLine());
		}

		System.out.print("🔹 Enter Course Duration (in years): ");
		course.setCourseDuration(scanner.nextInt());
		scanner.nextLine(); // consume newline

		System.out.print("🔹 Enter Description: ");
		course.setDescription(scanner.nextLine());

		System.out.print("🔹 Enter Course Fees: ");
		course.setCourseFees(scanner.nextInt());
		scanner.nextLine();
		return course;
	}

	public static void updateACourseById(Scanner scanner) {
		CourseModel course = getCommonCourseDetails(scanner,"ID");
		
		System.out.print("🔹 Enter Course Status (Active/Inactive): ");
		String status = scanner.nextLine();
		boolean isActive = true;
		if(status.equalsIgnoreCase("Inactive")) {
			isActive = false;
		}
		CoursesDao.updateCourseByID(course, isActive);
	}
	public static void updateACourseByName(Scanner scanner) {
		CourseModel course = getCommonCourseDetails(scanner,"Name");
		System.out.print("🔹 Enter Course Status (Active/Inactive): ");
		String status = scanner.nextLine();
		boolean isActive = true;
		if(status.equalsIgnoreCase("Inactive")) {
			isActive = false;
		}
		CoursesDao.updateCourseByName(course, isActive);
	}

	public static void addnewCourse(Scanner scanner) {
		CourseModel course = getCommonCourseDetails(scanner,"Add");
		CoursesDao.addNewCourse(course);

	}

	public static void assignCourse(Scanner scanner) {
		System.out.print("🔹 Enter Student Id: ");
		int studentId = scanner.nextInt();
		scanner.nextLine();
		if (StudentDao.checkStudent(studentId)) {
			System.out.print("🔹 Enter Course ID (e.g., CSE101): ");
			String courseId = scanner.nextLine();
			CoursesDao.assignCourseStudent(courseId, studentId);
			return;
		}

	}
	
	public static void displaySubjectsInCourse(Scanner scanner) {
		scanner.nextLine();
		System.out.print("🔹 Enter Course Id: ");
		String courseId = scanner.nextLine();
		List<CourseSubjectModel> subjects =CoursesDao.getSubjectsInCourse(courseId);
		if(subjects.isEmpty()) {
			return ;
		}
		System.out.println("\n\t  🎓 List of Subjects in a Course\n");
		System.out.println("🔹 Course Id: "+courseId );
		System.out.println("🔹 Total Subjects: "+subjects.size() );
		System.out.println(
				"--------------------------------------------------------------------");
		System.out.printf("| %-10s | %-20s | %-15s | %-10s |\n", "Subject ID", "Subject Name", "Semester", "Credits");
		System.out.println(
				"--------------------------------------------------------------------");

		subjects.stream().forEach(subject -> {
			System.out.printf("| %-10s | %-20s | %-15s | %-10d |\n", subject.getSubjectId(),
					subject.getSubjectName(), subject.getSemester(),subject.getCredits());
		});

		System.out.println(
				"--------------------------------------------------------------------");
	}
	
	public static void displayStudentsInCourse(Scanner scanner) {
		scanner.nextLine();
		System.out.print("🔹 Enter Course Id: ");
		String courseId = scanner.nextLine();
		List<StudentModel> students =CoursesDao.getStudentInCourse(courseId);
		if(students.isEmpty()) {
			return ;
		}
		System.out.println("\n\t  🎓 List of Students in Courses\n");
		System.out.println("🔹 Course Id: "+courseId );
		System.out.println("🔹 Total Students: "+students.size() );
		System.out.println(
				"------------------------------------------------------------");
		System.out.printf("| %-10s | %-25s | %-14s |\n", "Student ID", "Student Name", "Student RollNo.");
		System.out.println(
				"------------------------------------------------------------");

		students.stream().forEach(student -> {
			System.out.printf("| %-10s | %-25s | %-15s |\n", student.getStudent_id(),
					student.getStudent_name(), student.getStudent_rollnumber());
		});

		System.out.println(
				"------------------------------------------------------------");
	
	}
}
