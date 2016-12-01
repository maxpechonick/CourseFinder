package com.pechonick.registration.dao;

import com.pechonick.registration.dao.exception.DaoException;
import com.pechonick.registration.entity.student.Student;
import com.pechonick.registration.entity.student.StudentStatus;

import java.util.List;

public interface StudentDao {

    List<Student> getStudentsByCourseId(int courseId) throws DaoException;

    Student getStudent(int studentId) throws DaoException;

    void deleteStudent(int studentId) throws DaoException;

    void updateStudent(Student student) throws DaoException;

    void createStudent(Student student) throws DaoException;

    void setStudentStatus(StudentStatus status, int studentId, int courseId) throws DaoException;

}
