package com.pechonick.registration.service;

import com.pechonick.registration.entity.course.Course;
import com.pechonick.registration.entity.course.Review;
import com.pechonick.registration.entity.student.Student;
import com.pechonick.registration.entity.teacher.Teacher;
import com.pechonick.registration.service.exception.ServiceException;

import java.util.List;

public interface TeacherService {

    List<Course> getAllCoursesList() throws ServiceException;

    List<Course> getCoursesListByTeacherId(int teacherId, int offset, int rowCount) throws ServiceException;

    List<Student> getStudentsByCourseId(int courseId) throws ServiceException;

    void addReview(Review review) throws ServiceException;

    Teacher getTeacher(int teacherId) throws ServiceException;

    Teacher getTeacherByCourseId(int courseId) throws ServiceException;

    void updateTeacher(Teacher teacher) throws ServiceException;

    List<Teacher> getTeachersList() throws ServiceException;

    void appointTeacherForTheCourse(int teacherId, int courseId) throws ServiceException;

    boolean hasReview(int studentId, int courseId) throws ServiceException;
}
