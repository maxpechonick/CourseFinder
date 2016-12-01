package com.pechonick.registration.command.impl.admin;

import com.pechonick.registration.command.Command;
import com.pechonick.registration.command.ParameterUtil;
import com.pechonick.registration.command.exception.CommandException;
import com.pechonick.registration.controller.PageUtil;
import com.pechonick.registration.entity.course.Course;
import com.pechonick.registration.service.IService;
import com.pechonick.registration.service.ServiceFactory;
import com.pechonick.registration.service.ServiceType;
import com.pechonick.registration.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetAdminIndexInfoCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ServiceFactory factory = ServiceFactory.getInstance();
        IService studentService = factory.getService(ServiceType.STUDENT);

        List<Course> allCourses;

        try {
            allCourses = studentService.getAllCoursesList(0, ParameterUtil.ROW_COUNT_PER_QUERY);

            request.getSession().setAttribute(ParameterUtil.ALL_COURSES, allCourses);
            request.getSession().setAttribute(ParameterUtil.ALL_OFFSET, ParameterUtil.ROW_COUNT_PER_QUERY);
            request.getSession().setAttribute(ParameterUtil.PATH, PageUtil.ADMIN_INDEX_PAGE);

            return PageUtil.ADMIN_INDEX_PAGE;
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}
