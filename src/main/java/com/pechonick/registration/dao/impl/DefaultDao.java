package com.pechonick.registration.dao.impl;

import com.pechonick.registration.dao.IDao;
import com.pechonick.registration.dao.exception.DaoException;
import com.pechonick.registration.entity.common.User;
import com.pechonick.registration.entity.course.Course;
import com.pechonick.registration.entity.course.CourseStatus;
import com.pechonick.registration.entity.course.Review;
import com.pechonick.registration.entity.student.Student;
import com.pechonick.registration.entity.student.StudentStatus;
import com.pechonick.registration.entity.teacher.Teacher;

import java.util.List;

public class DefaultDao implements IDao {
    @Override
    public List<Course> getAllCourses(int offset, int rowCount) throws DaoException {
        throw new DaoException(IDao.DAO_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public List<Course> getCoursesByStudentId(int studentId, int offset, int rowCount) throws DaoException {
        throw new DaoException(IDao.DAO_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public List<Course> getCoursesByTeacherId(int teacherId, int offset, int rowCount) throws DaoException {
        throw new DaoException(IDao.DAO_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public void updateCourse(Course course) throws DaoException {
        throw new DaoException(IDao.DAO_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public Course getCourse(int courseId) throws DaoException {
        throw new DaoException(IDao.DAO_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public void deleteCourse(int courseId) throws DaoException {
        throw new DaoException(IDao.DAO_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public Course createCourse(Course course) throws DaoException {
        throw new DaoException(IDao.DAO_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public void applyStudentForTheCourse(int studentId, int courseId) throws DaoException {
        throw new DaoException(IDao.DAO_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public boolean checkStudentIfApplied(int studentId, int courseId) throws DaoException {
        throw new DaoException(IDao.DAO_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public void appointTeacherForTheCourse(int teacherId, int courseId) throws DaoException {
        throw new DaoException(IDao.DAO_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public void setCourseStatus(int courseId, CourseStatus status) throws DaoException {
        throw new DaoException(IDao.DAO_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public void addReview(Review review) throws DaoException {
        throw new DaoException(IDao.DAO_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public Review getReview(int studentId, int courseId) throws DaoException {
        throw new DaoException(IDao.DAO_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public List<Student> getStudentsByCourseId(int courseId) throws DaoException {
        throw new DaoException(IDao.DAO_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public Student getStudent(int studentId) throws DaoException {
        throw new DaoException(IDao.DAO_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public void deleteStudent(int studentId) throws DaoException {
        throw new DaoException(IDao.DAO_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public void updateStudent(Student student) throws DaoException {
        throw new DaoException(IDao.DAO_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public void createStudent(Student student) throws DaoException {
        throw new DaoException(IDao.DAO_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public void setStudentStatus(StudentStatus status, int studentId, int courseId) throws DaoException {
        throw new DaoException(IDao.DAO_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public Teacher getTeacher(int teacherId) throws DaoException {
        throw new DaoException(IDao.DAO_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public void updateTeacher(Teacher teacher) throws DaoException {
        throw new DaoException(IDao.DAO_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public void deleteTeacher(int teacherId) throws DaoException {
        throw new DaoException(IDao.DAO_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public void createTeacher(Teacher teacher) throws DaoException {
        throw new DaoException(IDao.DAO_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public Teacher getTeacherByCourseId(int courseId) throws DaoException {
        throw new DaoException(IDao.DAO_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public List<Teacher> getTeachersList() throws DaoException {
        throw new DaoException(IDao.DAO_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public void addUser(User user) throws DaoException {
        throw new DaoException(IDao.DAO_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public User getUser(String login) throws DaoException {
        throw new DaoException(IDao.DAO_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public void updateUser(User user) throws DaoException {
        throw new DaoException(IDao.DAO_NOT_IMPLEMENTED_MESSAGE);
    }
}
