package com.pechonick.registration.dao;

import com.pechonick.registration.dao.exception.DaoException;
import com.pechonick.registration.entity.course.Course;
import com.pechonick.registration.entity.course.CourseStatus;
import com.pechonick.registration.entity.course.Review;

import java.util.List;

public interface CourseDao {

    List<Course> getAllCourses(int offset, int rowCount) throws DaoException;

    List<Course> getCoursesByStudentId(int studentId, int offset, int rowCount) throws DaoException;

    List<Course> getCoursesByTeacherId(int teacherId, int offset, int rowCount) throws DaoException;

    void updateCourse(Course course) throws DaoException;

    Course getCourse(int courseId) throws DaoException;

    void deleteCourse(int courseId) throws DaoException;

    Course createCourse(Course course) throws DaoException;

    void applyStudentForTheCourse(int studentId, int courseId) throws DaoException;

    boolean checkStudentIfApplied(int studentId, int courseId) throws DaoException;

    void appointTeacherForTheCourse(int teacherId, int courseId) throws DaoException;

    void setCourseStatus(int courseId, CourseStatus status) throws DaoException;

    void addReview(Review review) throws DaoException;

    Review getReview(int studentId, int courseId) throws DaoException;
}
