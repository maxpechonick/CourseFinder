package com.pechonick.registration.command.impl.common;

import com.pechonick.registration.command.Command;
import com.pechonick.registration.command.ParameterUtil;
import com.pechonick.registration.command.exception.CommandException;
import com.pechonick.registration.controller.PageUtil;
import com.pechonick.registration.entity.common.User;
import com.pechonick.registration.entity.student.Student;
import com.pechonick.registration.entity.teacher.Teacher;
import com.pechonick.registration.service.IService;
import com.pechonick.registration.service.ServiceFactory;
import com.pechonick.registration.service.ServiceType;
import com.pechonick.registration.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class GetUserInfoCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        User user = (User) request.getSession().getAttribute(ParameterUtil.USER);

        try {
            User userInfo = getUserInfo(user, request);
            request.getSession().setAttribute(ParameterUtil.USER_INFO, userInfo);
            request.getSession().setAttribute(ParameterUtil.PATH, PageUtil.USER_INFO_PAGE);
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage(), e);
        }

        return PageUtil.USER_INFO_PAGE;
    }

    private User getUserInfo(User user, HttpServletRequest request) throws ServiceException {

        int userId = Integer.parseInt(request.getParameter(ParameterUtil.USER_ID));
        ServiceFactory factory = ServiceFactory.getInstance();

        switch (user.getRole()) {
            case STUDENT:
                IService studentService = factory.getService(ServiceType.STUDENT);
                Student student = studentService.getStudent(userId);
                student.setRole(user.getRole());
                return student;
            case TEACHER:
                IService teacherService = factory.getService(ServiceType.TEACHER);
                Teacher teacher = teacherService.getTeacher(userId);
                teacher.setRole(user.getRole());
                return teacher;
            default:
                return null;
        }
    }
}
