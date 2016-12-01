package com.pechonick.registration.dao;

import com.pechonick.registration.dao.exception.DaoException;
import com.pechonick.registration.entity.teacher.Teacher;

import java.util.List;

public interface TeacherDao {

    Teacher getTeacher(int teacherId) throws DaoException;

    void updateTeacher(Teacher teacher) throws DaoException;

    void deleteTeacher(int teacherId) throws DaoException;

    void createTeacher(Teacher teacher) throws DaoException;

    Teacher getTeacherByCourseId(int courseId) throws DaoException;

    List<Teacher> getTeachersList() throws DaoException;

}
