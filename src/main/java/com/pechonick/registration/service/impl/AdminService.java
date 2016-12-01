package com.pechonick.registration.service.impl;

import com.pechonick.registration.dao.DaoFactory;
import com.pechonick.registration.dao.DaoType;
import com.pechonick.registration.dao.IDao;
import com.pechonick.registration.dao.exception.DaoException;
import com.pechonick.registration.entity.course.CourseStatus;
import com.pechonick.registration.entity.student.Student;
import com.pechonick.registration.entity.student.StudentStatus;
import com.pechonick.registration.service.exception.ServiceException;

import java.util.List;


public class AdminService extends DefaultService {

    @Override
    public void appointTeacherForTheCourse(int teacherId, int courseId) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        IDao courseDao = factory.getDao(DaoType.COURSE);

        try {
            courseDao.appointTeacherForTheCourse(teacherId, courseId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void startCourse(int courseId, List<Student> studentList) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        IDao courseDao = factory.getDao(DaoType.COURSE);
        IDao studentDao = factory.getDao(DaoType.STUDENT);

        try {
            int i = 0;

            while (i < studentList.size() && i < Student.MAX_APPLIED_FOR_COURSE_COUNT) {
                Student student = studentList.get(i++);
                studentDao.setStudentStatus(StudentStatus.TRAINING, student.getId(), courseId);
            }

            while (i < studentList.size()) {
                Student student = studentList.get(i++);
                studentDao.setStudentStatus(StudentStatus.NOT_ENROLLED, student.getId(), courseId);
            }

            courseDao.setCourseStatus(courseId, CourseStatus.STARTED);

        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void cancelCourse(int courseId, List<Student> studentList) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        IDao courseDao = factory.getDao(DaoType.COURSE);
        IDao studentDao = factory.getDao(DaoType.STUDENT);

        try {
            int i = 0;

            while (i < studentList.size()) {
                Student student = studentList.get(i++);
                studentDao.setStudentStatus(StudentStatus.NOT_STARTED, student.getId(), courseId);
            }

            courseDao.setCourseStatus(courseId, CourseStatus.NOT_STARTED);

        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
