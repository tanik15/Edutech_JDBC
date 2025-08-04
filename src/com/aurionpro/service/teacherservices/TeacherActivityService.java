package com.aurionpro.service.teacherservices;

import java.sql.SQLException;
import java.util.List;

import com.aurionpro.dao.teacherdaos.TeacherActivityDao;
import com.aurionpro.model.teachermodels.TeacherActivity;

public class TeacherActivityService {
	private TeacherActivityDao activityDao = new TeacherActivityDao();

    public void addActivity(TeacherActivity activity) throws SQLException {
        if (activity.getActivityName().isEmpty()) {
            throw new IllegalArgumentException("Activity cannot be empty");
        }
        activityDao.addActivity(activity);
    }

    public List<TeacherActivity> getAllActivities() throws SQLException {
        return activityDao.getAllActivities();
    }

    public List<TeacherActivity> getActivitiesByTeacherId(int teacherId) throws SQLException {
        return activityDao.getActivitiesByTeacherId(teacherId);
    }
}

