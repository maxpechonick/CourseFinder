package com.pechonick.registration.service;

import com.pechonick.registration.entity.course.Course;
import com.pechonick.registration.entity.course.Review;
import com.pechonick.registration.service.exception.ServiceException;

public interface CourseService {

    Course getCourse(int courseId) throws ServiceException;

    Course createCourse(Course course) throws ServiceException;

    void updateCourse(Course course) throws ServiceException;

    void deleteCourse(int courseId) throws ServiceException;

    Review getReview(int studentId, int courseId) throws ServiceException;
}
