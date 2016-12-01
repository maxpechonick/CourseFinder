package com.pechonick.registration.service;

import com.pechonick.registration.entity.common.User;
import com.pechonick.registration.entity.course.Course;
import com.pechonick.registration.entity.course.Review;
import com.pechonick.registration.entity.student.Student;
import com.pechonick.registration.entity.teacher.Teacher;
import com.pechonick.registration.service.exception.ServiceException;

import java.util.List;

public interface IService {

    String SERVICE_NOT_IMPLEMENTED_MESSAGE = "Service method not implemented";

    void appointTeacherForTheCourse(int teacherId, int courseId) throws ServiceException;

    void startCourse(int courseId, List<Student> studentList) throws ServiceException;

    void cancelCourse(int courseId, List<Student> studentList) throws ServiceException;

    Course getCourse(int courseId) throws ServiceException;

    Course createCourse(Course course) throws ServiceException;

    void updateCourse(Course course) throws ServiceException;

    void deleteCourse(int courseId) throws ServiceException;

    Review getReview(int studentId, int courseId) throws ServiceException;

    List<Course> getAllCoursesList(int offset, int rowCount) throws ServiceException;

    List<Course> getSignedCoursesList(int studentId, int offset, int rowCount) throws ServiceException;


    void applyStudentForTheCourse(int studentId, int courseId) throws ServiceException;

    boolean checkStudentIfApplied(int studentId, int courseId) throws ServiceException;

    Student getStudent(int studentId) throws ServiceException;

    void updateStudent(Student student) throws ServiceException;

    List<Course> getAllCoursesList() throws ServiceException;

    List<Course> getCoursesListByTeacherId(int teacherId, int offset, int rowCount) throws ServiceException;

    List<Student> getStudentsByCourseId(int courseId) throws ServiceException;

    void addReview(Review review) throws ServiceException;

    Teacher getTeacher(int teacherId) throws ServiceException;

    Teacher getTeacherByCourseId(int courseId) throws ServiceException;

    void updateTeacher(Teacher teacher) throws ServiceException;

    List<Teacher> getTeachersList() throws ServiceException;

    boolean hasReview(int studentId, int courseId) throws ServiceException;

    void registerUser(User user) throws ServiceException;

    User loginUser(String login, String password) throws ServiceException;

    boolean isUserExist(String login) throws ServiceException;
}
