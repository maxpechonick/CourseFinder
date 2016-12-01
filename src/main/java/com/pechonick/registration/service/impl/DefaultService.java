package com.pechonick.registration.service.impl;

import com.pechonick.registration.entity.common.User;
import com.pechonick.registration.entity.course.Course;
import com.pechonick.registration.entity.course.Review;
import com.pechonick.registration.entity.student.Student;
import com.pechonick.registration.entity.teacher.Teacher;
import com.pechonick.registration.service.IService;
import com.pechonick.registration.service.exception.ServiceException;

import java.util.List;

public class DefaultService implements IService {
    @Override
    public void appointTeacherForTheCourse(int teacherId, int courseId) throws ServiceException {
        throw new ServiceException(IService.SERVICE_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public void startCourse(int courseId, List<Student> studentList) throws ServiceException {
        throw new ServiceException(IService.SERVICE_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public void cancelCourse(int courseId, List<Student> studentList) throws ServiceException {
        throw new ServiceException(IService.SERVICE_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public Course getCourse(int courseId) throws ServiceException {
        throw new ServiceException(IService.SERVICE_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public Course createCourse(Course course) throws ServiceException {
        throw new ServiceException(IService.SERVICE_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public void updateCourse(Course course) throws ServiceException {
        throw new ServiceException(IService.SERVICE_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public void deleteCourse(int courseId) throws ServiceException {
        throw new ServiceException(IService.SERVICE_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public Review getReview(int studentId, int courseId) throws ServiceException {
        throw new ServiceException(IService.SERVICE_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public List<Course> getAllCoursesList(int offset, int rowCount) throws ServiceException {
        throw new ServiceException(IService.SERVICE_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public List<Course> getSignedCoursesList(int studentId, int offset, int rowCount) throws ServiceException {
        throw new ServiceException(IService.SERVICE_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public void applyStudentForTheCourse(int studentId, int courseId) throws ServiceException {
        throw new ServiceException(IService.SERVICE_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public boolean checkStudentIfApplied(int studentId, int courseId) throws ServiceException {
        throw new ServiceException(IService.SERVICE_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public Student getStudent(int studentId) throws ServiceException {
        throw new ServiceException(IService.SERVICE_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public void updateStudent(Student student) throws ServiceException {
        throw new ServiceException(IService.SERVICE_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public List<Course> getAllCoursesList() throws ServiceException {
        throw new ServiceException(IService.SERVICE_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public List<Course> getCoursesListByTeacherId(int teacherId, int offset, int rowCount) throws ServiceException {
        throw new ServiceException(IService.SERVICE_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public List<Student> getStudentsByCourseId(int courseId) throws ServiceException {
        throw new ServiceException(IService.SERVICE_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public void addReview(Review review) throws ServiceException {
        throw new ServiceException(IService.SERVICE_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public Teacher getTeacher(int teacherId) throws ServiceException {
        throw new ServiceException(IService.SERVICE_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public Teacher getTeacherByCourseId(int courseId) throws ServiceException {
        throw new ServiceException(IService.SERVICE_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public void updateTeacher(Teacher teacher) throws ServiceException {
        throw new ServiceException(IService.SERVICE_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public List<Teacher> getTeachersList() throws ServiceException {
        throw new ServiceException(IService.SERVICE_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public boolean hasReview(int studentId, int courseId) throws ServiceException {
        throw new ServiceException(IService.SERVICE_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public void registerUser(User user) throws ServiceException {
        throw new ServiceException(IService.SERVICE_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public User loginUser(String login, String password) throws ServiceException {
        throw new ServiceException(IService.SERVICE_NOT_IMPLEMENTED_MESSAGE);
    }

    @Override
    public boolean isUserExist(String login) throws ServiceException {
        throw new ServiceException(IService.SERVICE_NOT_IMPLEMENTED_MESSAGE);
    }
}
