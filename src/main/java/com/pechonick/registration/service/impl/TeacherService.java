package com.pechonick.registration.service.impl;

import com.pechonick.registration.command.ParameterUtil;
import com.pechonick.registration.dao.DaoFactory;
import com.pechonick.registration.dao.DaoType;
import com.pechonick.registration.dao.IDao;
import com.pechonick.registration.dao.exception.DaoException;
import com.pechonick.registration.entity.course.Course;
import com.pechonick.registration.entity.course.CourseStatus;
import com.pechonick.registration.entity.course.Review;
import com.pechonick.registration.entity.student.Student;
import com.pechonick.registration.entity.student.StudentStatus;
import com.pechonick.registration.entity.teacher.Teacher;
import com.pechonick.registration.service.exception.ServiceException;

import java.sql.Date;
import java.util.List;

public class TeacherService extends DefaultService {

    @Override
    public List<Course> getAllCoursesList() throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        IDao courseDao = factory.getDao(DaoType.COURSE);

        List<Course> courses;

        try {
            courses = courseDao.getAllCourses(0, ParameterUtil.ROW_COUNT_PER_QUERY);

            for (Course course : courses) {
                if (course.getEndDate().before(new Date(System.currentTimeMillis()))
                        && course.getStatus() != CourseStatus.COMPLETED) {
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
    public List<Course> getCoursesListByTeacherId(int teacherId, int offset, int rowCount) throws ServiceException{
        DaoFactory factory = DaoFactory.getInstance();
        IDao courseDao = factory.getDao(DaoType.COURSE);

        List<Course> courses;
        try {
            courses = courseDao.getCoursesByTeacherId(teacherId, offset, rowCount);

            for (Course course : courses) {
                if (course.getEndDate().before(new Date(System.currentTimeMillis()))
                        && course.getStatus() != CourseStatus.COMPLETED) {
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
    public List<Student> getStudentsByCourseId(int courseId) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        IDao courseDao = factory.getDao(DaoType.COURSE);
        IDao studentDao = factory.getDao(DaoType.STUDENT);

        List<Student> students;
        Course course;
        try {
            students = studentDao.getStudentsByCourseId(courseId);
            course = courseDao.getCourse(courseId);

            if (course.getStatus() == CourseStatus.COMPLETED) {
                for (Student student : students) {
                    studentDao.setStudentStatus(StudentStatus.COMPLETED, student.getId(), courseId);
                    student.setReviewed(hasReview(student.getId(), courseId));
                }
            }

        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return students;
    }

    @Override
    public void addReview(Review review) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        IDao courseDao = factory.getDao(DaoType.COURSE);

        try {
            courseDao.addReview(review);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Teacher getTeacher(int teacherId) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        IDao teacherDao = factory.getDao(DaoType.TEACHER);

        try {
            return teacherDao.getTeacher(teacherId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void updateTeacher(Teacher teacher) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        IDao teacherDao = factory.getDao(DaoType.TEACHER);
        IDao userDao = factory.getDao(DaoType.USER);

        try {
            teacherDao.updateTeacher(teacher);
            userDao.updateUser(teacher);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Teacher getTeacherByCourseId(int courseId) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        IDao teacherDao = factory.getDao(DaoType.TEACHER);

        try {
            return teacherDao.getTeacherByCourseId(courseId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Teacher> getTeachersList() throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        IDao teacherDao = factory.getDao(DaoType.TEACHER);

        try {
            return teacherDao.getTeachersList();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

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
    public boolean hasReview(int studentId, int courseId) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        IDao courseDao = factory.getDao(DaoType.COURSE);

        try {
            Review review = courseDao.getReview(studentId, courseId);

            if (review != null) {
                return true;
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return false;
    }
}
