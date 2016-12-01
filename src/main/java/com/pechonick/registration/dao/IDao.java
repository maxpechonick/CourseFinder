package com.pechonick.registration.dao;

import com.pechonick.registration.dao.exception.DaoException;
import com.pechonick.registration.entity.common.User;
import com.pechonick.registration.entity.course.Course;
import com.pechonick.registration.entity.course.CourseStatus;
import com.pechonick.registration.entity.course.Review;
import com.pechonick.registration.entity.student.Student;
import com.pechonick.registration.entity.student.StudentStatus;
import com.pechonick.registration.entity.teacher.Teacher;

import java.util.List;

public interface IDao {

    String DAO_NOT_IMPLEMENTED_MESSAGE = "Dao method not implemented";

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

    List<Student> getStudentsByCourseId(int courseId) throws DaoException;

    Student getStudent(int studentId) throws DaoException;

    void deleteStudent(int studentId) throws DaoException;

    void updateStudent(Student student) throws DaoException;

    void createStudent(Student student) throws DaoException;

    void setStudentStatus(StudentStatus status, int studentId, int courseId) throws DaoException;

    Teacher getTeacher(int teacherId) throws DaoException;

    void updateTeacher(Teacher teacher) throws DaoException;

    void deleteTeacher(int teacherId) throws DaoException;

    void createTeacher(Teacher teacher) throws DaoException;

    Teacher getTeacherByCourseId(int courseId) throws DaoException;

    List<Teacher> getTeachersList() throws DaoException;

    void addUser(User user) throws DaoException;

    User getUser(String login) throws DaoException;

    void updateUser(User user) throws DaoException;
}
