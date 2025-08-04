package com.aurionpro.test.teachertest;

import com.aurionpro.facade.teacherfacade.TeacherFacade;
import java.util.Scanner;

public class TeacherMainTest {
	 public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        TeacherFacade.displayTeacherMenu(scanner);
	        scanner.close();
	    }}
