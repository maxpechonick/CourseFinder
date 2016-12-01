package com.pechonick.registration.command.impl.student;

import com.pechonick.registration.command.Command;
import com.pechonick.registration.command.ParameterUtil;
import com.pechonick.registration.command.exception.CommandException;
import com.pechonick.registration.controller.PageUtil;
import com.pechonick.registration.entity.course.Course;
import com.pechonick.registration.entity.course.Review;
import com.pechonick.registration.entity.teacher.Teacher;
import com.pechonick.registration.entity.common.User;
import com.pechonick.registration.service.IService;
import com.pechonick.registration.service.ServiceFactory;
import com.pechonick.registration.service.ServiceType;
import com.pechonick.registration.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GetStudentCourseInfoCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ServiceFactory factory = ServiceFactory.getInstance();
        IService courseService = factory.getService(ServiceType.COURSE);
        IService teacherService = factory.getService(ServiceType.TEACHER);
        IService studentService = factory.getService(ServiceType.STUDENT);

        HttpSession session = request.getSession(false);

        if (session == null) {
            throw new CommandException("Session expired");
        }

        User user = (User) request.getSession().getAttribute(ParameterUtil.USER);
        int courseId = Integer.parseInt(request.getParameter(ParameterUtil.COURSE_ID));

        try {
            Course courseInfo = courseService.getCourse(courseId);
            Review review = courseService.getReview(user.getId(), courseId);
            Teacher teacher = teacherService.getTeacherByCourseId(courseId);

            request.getSession().setAttribute(ParameterUtil.COURSE, courseInfo);
            request.getSession().setAttribute(ParameterUtil.TEACHER, teacher);
            request.getSession().setAttribute(ParameterUtil.REVIEW, review);
            request.getSession().setAttribute(ParameterUtil.IS_SIGNED,
                    studentService.checkStudentIfApplied(user.getId(),courseId));
            request.getSession().setAttribute(ParameterUtil.PATH, PageUtil.STUDENT_COURSE_INFO_PAGE);

            return PageUtil.STUDENT_COURSE_INFO_PAGE;
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}
