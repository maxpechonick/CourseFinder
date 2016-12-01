package com.pechonick.registration.command.impl.student;

import com.pechonick.registration.command.Command;
import com.pechonick.registration.command.ParameterUtil;
import com.pechonick.registration.command.exception.CommandException;
import com.pechonick.registration.controller.PageUtil;
import com.pechonick.registration.entity.common.Role;
import com.pechonick.registration.entity.student.Student;
import com.pechonick.registration.service.IService;
import com.pechonick.registration.service.ServiceFactory;
import com.pechonick.registration.service.ServiceType;
import com.pechonick.registration.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public class EditStudentInfoCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ServiceFactory factory = ServiceFactory.getInstance();
        IService studentService = factory.getService(ServiceType.STUDENT);

        try {
            Student student = new Student();
            ParameterUtil.formUser(student, request);
            student.setRole(Role.STUDENT);
            studentService.updateStudent(student);
            request.getSession().setAttribute(ParameterUtil.USER, student);

            return PageUtil.USER_INFO_PAGE;
        } catch (ServiceException | UnsupportedEncodingException e) {
            throw new CommandException(e.getMessage(), e);
        }

    }

}
