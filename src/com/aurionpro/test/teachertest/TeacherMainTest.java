package com.aurionpro.test.teachertest;

import java.util.Scanner;

import com.aurionpro.controller.teacherControllers.TeacherActivityController;
import com.aurionpro.controller.teacherControllers.TeacherAttendanceController;
import com.aurionpro.controller.teacherControllers.TeacherController;
import com.aurionpro.controller.teacherControllers.TeacherProfileController;
import com.aurionpro.controller.teacherControllers.TeacherRatingController;
import com.aurionpro.controller.teacherControllers.TeacherSalaryController;
import com.aurionpro.facade.teacherfacade.TeacherFacade;

public class TeacherMainTest {
	 public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        TeacherFacade.displayTeacherMenu(scanner);
	        scanner.close();
	    }}
