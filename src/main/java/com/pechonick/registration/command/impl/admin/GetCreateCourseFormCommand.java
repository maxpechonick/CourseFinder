package com.pechonick.registration.command.impl.admin;

import com.pechonick.registration.command.Command;
import com.pechonick.registration.command.CommandName;
import com.pechonick.registration.command.ParameterUtil;
import com.pechonick.registration.command.exception.CommandException;
import com.pechonick.registration.controller.PageUtil;
import com.pechonick.registration.entity.course.Course;
import com.pechonick.registration.entity.course.CourseStatus;
import com.pechonick.registration.entity.teacher.Teacher;
import com.pechonick.registration.service.IService;
import com.pechonick.registration.service.ServiceFactory;
import com.pechonick.registration.service.ServiceType;
import com.pechonick.registration.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetCreateCourseFormCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ServiceFactory factory = ServiceFactory.getInstance();
        IService teacherService = factory.getService(ServiceType.TEACHER);

        try {
            List<Teacher> teachersList = teacherService.getTeachersList();
            Course course = new Course();
            course.setStatus(CourseStatus.OPEN_SET);

            request.getSession().setAttribute(ParameterUtil.COURSE, course);
            request.getSession().setAttribute(ParameterUtil.TEACHERS_LIST, teachersList);
            request.getSession().setAttribute(ParameterUtil.COMMAND_NAME, CommandName.CREATE_COURSE.getCommandName());

            request.getSession().setAttribute(ParameterUtil.PATH, PageUtil.COURSE_FORM_PAGE);

            return PageUtil.COURSE_FORM_PAGE;
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}
