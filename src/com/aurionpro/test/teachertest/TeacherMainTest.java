package com.aurionpro.test.teachertest;

import java.util.Scanner;

import com.aurionpro.facade.TeacherFacade;

public class TeacherMainTest {
	 public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        TeacherFacade.displayTeacherMenu(scanner);
	        scanner.close();
	    }}
