package com.pechonick.registration.service.impl;

import com.pechonick.registration.dao.DaoFactory;
import com.pechonick.registration.dao.DaoType;
import com.pechonick.registration.dao.IDao;
import com.pechonick.registration.dao.exception.DaoException;
import com.pechonick.registration.entity.course.Course;
import com.pechonick.registration.entity.course.CourseStatus;
import com.pechonick.registration.entity.course.Review;
import com.pechonick.registration.service.exception.ServiceException;

import java.sql.Date;

public class CourseService extends DefaultService {

    @Override
    public Course getCourse(int courseId) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        IDao courseDao = factory.getDao(DaoType.COURSE);

        try {
            Course course = courseDao.getCourse(courseId);

            if (course.getEndDate().before(new Date(System.currentTimeMillis()))) {
                course.setStatus(CourseStatus.COMPLETED);
                courseDao.updateCourse(course);
            }

            return course;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Course createCourse(Course course) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        IDao courseDao = factory.getDao(DaoType.COURSE);

        try {
            return courseDao.createCourse(course);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void updateCourse(Course course) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        IDao courseDao = factory.getDao(DaoType.COURSE);

        try {
            courseDao.updateCourse(course);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteCourse(int courseId) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        IDao courseDao = factory.getDao(DaoType.COURSE);

        try {
            courseDao.deleteCourse(courseId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Review getReview(int studentId, int courseId) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        IDao courseDao = factory.getDao(DaoType.COURSE);

        try {
            return courseDao.getReview(studentId, courseId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
