package com.pechonick.registration.service;

import com.pechonick.registration.entity.course.Course;
import com.pechonick.registration.entity.student.Student;
import com.pechonick.registration.entity.teacher.Teacher;
import com.pechonick.registration.service.exception.ServiceException;

import java.util.List;

public interface StudentService {

    List<Course> getAllCoursesList(int offset, int rowCount) throws ServiceException;

    List<Course> getSignedCoursesList(int studentId, int offset, int rowCount) throws ServiceException;

    Teacher getTeacherByCourseId(int courseId) throws ServiceException;

    void applyStudentForTheCourse(int studentId, int courseId) throws ServiceException;

    boolean checkStudentIfApplied(int studentId, int courseId) throws ServiceException;

    Student getStudent(int studentId) throws ServiceException;

    void updateStudent(Student student) throws ServiceException;

}
