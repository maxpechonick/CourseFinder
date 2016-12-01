package com.pechonick.registration.command.impl.admin;

import com.pechonick.registration.command.Command;
import com.pechonick.registration.command.ParameterUtil;
import com.pechonick.registration.command.exception.CommandException;
import com.pechonick.registration.controller.PageUtil;
import com.pechonick.registration.entity.course.Course;
import com.pechonick.registration.entity.student.Student;
import com.pechonick.registration.service.IService;
import com.pechonick.registration.service.ServiceFactory;
import com.pechonick.registration.service.ServiceType;
import com.pechonick.registration.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetAdminCourseInfoCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ServiceFactory factory = ServiceFactory.getInstance();
        IService courseService = factory.getService(ServiceType.COURSE);
        IService teacherService = factory.getService(ServiceType.TEACHER);

        HttpSession session = request.getSession(false);

        if (session == null) {
            throw new CommandException("Session expired");
        }

        int courseId = Integer.parseInt(request.getParameter(ParameterUtil.COURSE_ID));

        try {
            Course courseInfo = courseService.getCourse(courseId);
            List<Student> students = teacherService.getStudentsByCourseId(courseId);

            request.getSession().setAttribute(ParameterUtil.COURSE, courseInfo);
            request.getSession().setAttribute(ParameterUtil.STUDENT_LIST, students);
            request.getSession().setAttribute(ParameterUtil.PATH, PageUtil.ADMIN_COURSE_INFO_PAGE);

            return PageUtil.ADMIN_COURSE_INFO_PAGE;
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}
