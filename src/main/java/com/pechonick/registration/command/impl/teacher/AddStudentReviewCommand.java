package com.pechonick.registration.command.impl.teacher;

import com.pechonick.registration.command.Command;
import com.pechonick.registration.command.ParameterUtil;
import com.pechonick.registration.command.exception.CommandException;
import com.pechonick.registration.controller.PageUtil;
import com.pechonick.registration.entity.course.Review;
import com.pechonick.registration.entity.student.Student;
import com.pechonick.registration.service.IService;
import com.pechonick.registration.service.ServiceFactory;
import com.pechonick.registration.service.ServiceType;
import com.pechonick.registration.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class AddStudentReviewCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ServiceFactory factory = ServiceFactory.getInstance();
        IService teacherService = factory.getService(ServiceType.TEACHER);

        Review review = new Review();

        int courseId = Integer.parseInt(request.getParameter(ParameterUtil.COURSE_ID));

        try {
            ParameterUtil.formReview(review, request);
            teacherService.addReview(review);

            List<Student> students = teacherService.getStudentsByCourseId(courseId);
            request.getSession().setAttribute(ParameterUtil.STUDENT_LIST, students);
            request.getSession().setAttribute(ParameterUtil.PATH, PageUtil.TEACHER_COURSE_INFO_PAGE);

            return PageUtil.TEACHER_COURSE_INFO_PAGE;
        } catch (UnsupportedEncodingException | ServiceException e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}
