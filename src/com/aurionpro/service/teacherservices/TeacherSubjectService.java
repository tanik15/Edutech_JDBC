package com.aurionpro.service.teacherservices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.aurionpro.dao.teacherdaos.TeacherSubjectDao;
import com.aurionpro.database.DBUtil;
import com.aurionpro.model.teachermodels.TeacherSubject;

public class TeacherSubjectService {
	 private TeacherSubjectDao dao = new TeacherSubjectDao();

	    public boolean assignSubject(int teacherId, String subjectId) {
	        subjectId = subjectId.trim().toUpperCase();

	      
	        if (teacherId <= 0 || subjectId.isEmpty()) {
	            System.out.println("❌ Invalid Teacher ID or Subject ID.");
	            return false;
	        }

	        
	        if (!isTeacherExists(teacherId)) {
	            System.out.println("❌ Teacher with ID " + teacherId + " does not exist.");
	            return false;
	        }

	    
	        if (!isSubjectExists(subjectId)) {
	            System.out.println("❌ Subject with ID " + subjectId + " does not exist.");
	            return false;
	        }

	   
	        if (isAlreadyAssigned(teacherId, subjectId)) {
	            System.out.println("❌ This subject is already assigned to this teacher.");
	            return false;
	        }


	        boolean success = dao.assignSubjectToTeacher(teacherId, subjectId);
	        if (success) {
	            System.out.println("✅ Subject assigned successfully.");
	        } else {
	            System.out.println("❌ Failed to assign subject.");
	        }

	        return success;
	    }

	    public List<TeacherSubject> getAllAssignments() {
	        return dao.getAllAssignments();
	    }

	  
	    private boolean isTeacherExists(int teacherId) {
	        String query = "SELECT COUNT(*) FROM teacher WHERE teacher_id = ?";
	        try (Connection conn = DBUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setInt(1, teacherId);
	            ResultSet rs = stmt.executeQuery();
	            return rs.next() && rs.getInt(1) > 0;

	        } catch (Exception e) {
	            System.out.println("❌ Error checking teacher existence: " + e.getMessage());
	            return false;
	        }
	    }

	 
	    private boolean isSubjectExists(String subjectId) {
	        String query = "SELECT COUNT(*) FROM subject_table WHERE subject_id = ?";
	        try (Connection conn = DBUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setString(1, subjectId);
	            ResultSet rs = stmt.executeQuery();
	            return rs.next() && rs.getInt(1) > 0;

	        } catch (Exception e) {
	            System.out.println("❌ Error checking subject existence: " + e.getMessage());
	            return false;
	        }
	    }

	   
	    private boolean isAlreadyAssigned(int teacherId, String subjectId) {
	        String query = "SELECT COUNT(*) FROM teacher_subject WHERE teacher_id = ? AND subject_id = ?";
	        try (Connection conn = DBUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setInt(1, teacherId);
	            stmt.setString(2, subjectId);
	            ResultSet rs = stmt.executeQuery();
	            return rs.next() && rs.getInt(1) > 0;

	        } catch (Exception e) {
	            System.out.println("❌ Error checking assignment existence: " + e.getMessage());
	            return false;
	        }
	    }
	}
