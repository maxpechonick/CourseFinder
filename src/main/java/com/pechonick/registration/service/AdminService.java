package com.pechonick.registration.service;

import com.pechonick.registration.entity.student.Student;
import com.pechonick.registration.service.exception.ServiceException;

import java.util.List;


public interface AdminService {

    void appointTeacherForTheCourse(int teacherId, int courseId) throws ServiceException;

    void startCourse(int courseId, List<Student> studentList) throws ServiceException;

    void cancelCourse(int courseId, List<Student> studentList) throws ServiceException;
}
