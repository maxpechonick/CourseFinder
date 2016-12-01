package com.pechonick.registration.command.impl.admin;

import com.pechonick.registration.command.Command;
import com.pechonick.registration.command.CommandName;
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
import java.util.List;

public class GetEditCourseFormCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ServiceFactory factory = ServiceFactory.getInstance();
        IService teacherService = factory.getService(ServiceType.TEACHER);
        IService courseService = factory.getService(ServiceType.COURSE);

        int courseId = Integer.parseInt(request.getParameter(ParameterUtil.COURSE_ID));

        try {
            List<Teacher> teachersList = teacherService.getTeachersList();
            Course course = courseService.getCourse(courseId);
            Teacher selectedTeacher = teacherService.getTeacherByCourseId(courseId);

            request.getSession().setAttribute(ParameterUtil.COURSE, course);
            request.getSession().setAttribute(ParameterUtil.SELECTED_TEACHER, selectedTeacher);
            request.getSession().setAttribute(ParameterUtil.TEACHERS_LIST, teachersList);
            request.getSession().setAttribute(ParameterUtil.COMMAND_NAME, CommandName.EDIT_COURSE.getCommandName());
            System.out.println(CommandName.EDIT_COURSE.getCommandName());

            request.getSession().setAttribute(ParameterUtil.PATH, PageUtil.COURSE_FORM_PAGE);

            return PageUtil.COURSE_FORM_PAGE;
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}
