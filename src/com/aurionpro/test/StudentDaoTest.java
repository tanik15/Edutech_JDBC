package com.aurionpro.test;

import java.util.List;

import com.aurionpro.dao.studentDao;
import com.aurionpro.model.studentModel;

public class StudentDaoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		studentDao sd = new studentDao();
//		List<studentModel>list = sd.getAllStudents();
//		System.out.println("Id\tName\t\tRollno\tDOB"); 
//		for(studentModel s : list) {
//			System.out.println(s.getStudent_id()+"\t"+s.getStudent_name()+"\t"+s.getStudent_rollnumber()+"\t"+s.getStudent_DOB());
//		}
		studentModel student = sd.getAStudent(56);
		System.out.println(student.getStudent_id()+"\t"+student.getStudent_name()+"\t"+student.getStudent_rollnumber()+"\t"+student.getStudent_DOB());
		
	}

}
