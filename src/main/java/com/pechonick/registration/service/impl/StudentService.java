package com.pechonick.registration.service.impl;

import com.pechonick.registration.dao.DaoFactory;
import com.pechonick.registration.dao.DaoType;
import com.pechonick.registration.dao.IDao;
import com.pechonick.registration.dao.exception.DaoException;
import com.pechonick.registration.entity.course.Course;
import com.pechonick.registration.entity.course.CourseStatus;
import com.pechonick.registration.entity.student.Student;
import com.pechonick.registration.entity.teacher.Teacher;
import com.pechonick.registration.service.exception.ServiceException;

import java.sql.Date;
import java.util.List;

public class StudentService extends DefaultService {

    @Override
    public List<Course> getAllCoursesList(int offset, int rowCount) throws ServiceException{
        DaoFactory factory = DaoFactory.getInstance();
        IDao courseDao = factory.getDao(DaoType.COURSE);
        List<Course> courses;

        try {
            courses = courseDao.getAllCourses(offset, rowCount);

            for (Course course : courses) {
                if (course.getEndDate().before(new Date(System.currentTimeMillis()))
                        && course.getStatus() != CourseStatus.NOT_STARTED) {
                    course.setStatus(CourseStatus.COMPLETED);
                    courseDao.updateCourse(course);
                }
            }

        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return courses;
    }

    @Override
    public List<Course> getSignedCoursesList(int studentId, int offset, int rowCount) throws ServiceException{
        DaoFactory factory = DaoFactory.getInstance();
        IDao courseDao = factory.getDao(DaoType.COURSE);
        List<Course> courses;

        try {
            courses = courseDao.getCoursesByStudentId(studentId, offset, rowCount);

            for (Course course : courses) {
                if (course.getEndDate().before(new Date(System.currentTimeMillis()))
                        && course.getStatus() != CourseStatus.NOT_STARTED) {
                    course.setStatus(CourseStatus.COMPLETED);
                    courseDao.updateCourse(course);
                }
            }

        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return courses;
    }

    @Override
    public Teacher getTeacherByCourseId(int courseId) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        IDao teacherDao = factory.getDao(DaoType.TEACHER);
        Teacher teacher;

        try {
            teacher = teacherDao.getTeacherByCourseId(courseId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return teacher;
    }

    @Override
    public void applyStudentForTheCourse(int studentId, int courseId) throws ServiceException{
        DaoFactory factory = DaoFactory.getInstance();
        IDao courseDao = factory.getDao(DaoType.COURSE);

        try {
            courseDao.applyStudentForTheCourse(studentId, courseId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }

    }

    @Override
    public boolean checkStudentIfApplied(int studentId, int courseId) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        IDao courseDao = factory.getDao(DaoType.COURSE);

        try {
            return courseDao.checkStudentIfApplied(studentId, courseId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Student getStudent(int studentId) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        IDao studentDao = factory.getDao(DaoType.STUDENT);

        try {
            return studentDao.getStudent(studentId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void updateStudent(Student student) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        IDao studentDao = factory.getDao(DaoType.STUDENT);
        IDao userDao = factory.getDao(DaoType.USER);

        try {
            studentDao.updateStudent(student);
            userDao.updateUser(student);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
