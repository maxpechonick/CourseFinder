package com.pechonick.registration.command.impl.admin;

import com.pechonick.registration.command.Command;
import com.pechonick.registration.command.ParameterUtil;
import com.pechonick.registration.command.exception.CommandException;
import com.pechonick.registration.controller.PageUtil;
import com.pechonick.registration.entity.course.Course;
import com.pechonick.registration.entity.teacher.Teacher;
import com.pechonick.registration.service.IService;
import com.pechonick.registration.service.ServiceFactory;
import com.pechonick.registration.service.ServiceType;
import com.pechonick.registration.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public class AppointTeacherForTheCourseCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ServiceFactory factory = ServiceFactory.getInstance();
        IService teacherService = factory.getService(ServiceType.TEACHER);

        int teacherId = Integer.parseInt(request.getParameter(ParameterUtil.TEACHER_ID));

        try {
            Course course = new Course();
            ParameterUtil.formCourse(course, request);
            Teacher selectedTeacher = teacherService.getTeacher(teacherId);
            
            request.getSession().setAttribute(ParameterUtil.COURSE, course);
            request.getSession().setAttribute(ParameterUtil.SELECTED_TEACHER, selectedTeacher);

            return PageUtil.COURSE_FORM_PAGE;
        } catch (UnsupportedEncodingException | ServiceException e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}
