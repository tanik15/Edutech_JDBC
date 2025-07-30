package com.aurionpro.test;

import java.util.List;

import com.aurionpro.dao.StudentExamDao;
import com.aurionpro.model.studentsModel.StudentExamModel;

public class StudentDaoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		studentDao sd = new studentDao();

//		studentModel student = sd.getAStudent(5);
//		if(student!=null)
//		System.out.println(student.getStudent_id()+"\t"+student.getStudent_name()+"\t"+student.getStudent_rollnumber()+"\t"+student.getStudent_DOB());
//		sd.deleteStudent(5);
//		studentModel s1 = new studentModel();
//		s1.setStudent_name("Tanik");
//		s1.setStudent_DOB("2003-05-15");
//		sd.addNewStudent(s1);

//		studentProfileDao.updateStudentProfile(
//				new StudentProfileModel(19, "8 Green Avenue, Delhi", "M", "9123456780","taniky@example.com"));
//		List<StudentModel> list = sd.getAllStudents();
//		System.out.println("Id\tName\t\tRollno\tDOB");
//		for (StudentModel s : list) {
//			System.out.println(s.getStudent_id() + "\t" + s.getStudent_name() + "\t" + s.getStudent_rollnumber() + "\t"
//					+ s.getStudent_DOB());
//		}
		
		List<StudentExamModel> exams = StudentExamDao.viewAllExams();
		exams.stream().forEach((exam)->{
			System.out.println(exam.getExamId()+"\t"+exam.getSubjectId()+"\t"+exam.getExamDate()+"\t"+exam.getTotalMarks()+"\t"+exam.getSemester());
		});
	}

}
