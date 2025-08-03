package com.aurionpro.controller.studentsController;

import java.util.List;
import java.util.Scanner;

import com.aurionpro.dao.studentsDao.StudentExamDao;
import com.aurionpro.model.studentsModel.StudentExamModel;

public class StudentExamsController {
	public static void addNewExam(Scanner scanner) {

		StudentExamModel examModel = new StudentExamModel();

		System.out.print("Enter Subject ID: ");
		String subjectId = scanner.nextLine();
		examModel.setSubjectId(subjectId);
		scanner.nextLine();

		System.out.print("Enter Exam Date (YYYY-MM-DD): ");
		String examDate = scanner.nextLine();
		examModel.setExamDate(examDate);

		System.out.print("Enter Total Marks: ");
		int totalMarks = scanner.nextInt();
		examModel.setTotalMarks(totalMarks);
		scanner.nextLine();

		System.out.print("Enter Semester: ");
		String semester = scanner.nextLine();
		examModel.setSemester(semester);

		StudentExamDao.addNewExam(examModel);

		System.out.println("\nStudent Exam details added successfully.");
	}

	public static void viewAllExams() {
		List<StudentExamModel> exams = StudentExamDao.getAllExams();
		if (exams.isEmpty()) {
			System.out.println("No exam records found.");
		} else {
			System.out.println("\n\t\t\t🎓 Exam Details");
			System.out.println("--------------------------------------------------------------------");
			System.out.printf("| %-8s | %-10s | %-12s | %-12s | %-10s |\n", "Exam ID", "Subject ID", "Exam Date",
					"Total Marks", "Semester");
			System.out.println("--------------------------------------------------------------------");

			exams.forEach((exam) -> {
				System.out.printf("| %-8d | %-10s | %-12s | %-12d | %-10s |\n", exam.getExamId(), exam.getSubjectId(),
						exam.getExamDate(), exam.getTotalMarks(), exam.getSemester());
			});
			System.out.println("--------------------------------------------------------------------");
		}
	}
}
