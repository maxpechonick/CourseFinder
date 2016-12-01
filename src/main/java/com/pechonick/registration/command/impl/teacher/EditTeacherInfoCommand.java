package com.pechonick.registration.command.impl.teacher;

import com.pechonick.registration.command.Command;
import com.pechonick.registration.command.ParameterUtil;
import com.pechonick.registration.command.exception.CommandException;
import com.pechonick.registration.controller.PageUtil;
import com.pechonick.registration.entity.common.Role;
import com.pechonick.registration.entity.teacher.Teacher;
import com.pechonick.registration.service.IService;
import com.pechonick.registration.service.ServiceFactory;
import com.pechonick.registration.service.ServiceType;
import com.pechonick.registration.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public class EditTeacherInfoCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ServiceFactory factory = ServiceFactory.getInstance();
        IService teacherService = factory.getService(ServiceType.TEACHER);

        try {
            Teacher teacher = new Teacher();
            ParameterUtil.formUser(teacher, request);
            teacher.setRole(Role.TEACHER);
            teacherService.updateTeacher(teacher);
            request.getSession().setAttribute(ParameterUtil.USER, teacher);

            return PageUtil.USER_INFO_PAGE;
        } catch (ServiceException | UnsupportedEncodingException e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}
