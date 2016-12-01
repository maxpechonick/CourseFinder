package com.pechonick.registration.command.impl.teacher;

import com.pechonick.registration.command.Command;
import com.pechonick.registration.command.ParameterUtil;
import com.pechonick.registration.command.exception.CommandException;
import com.pechonick.registration.controller.PageUtil;
import com.pechonick.registration.entity.student.Student;
import com.pechonick.registration.service.IService;
import com.pechonick.registration.service.ServiceFactory;
import com.pechonick.registration.service.ServiceType;
import com.pechonick.registration.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class GetAddReviewFormCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ServiceFactory factory = ServiceFactory.getInstance();
        IService studentService = factory.getService(ServiceType.STUDENT);

        int studentId = Integer.parseInt(request.getParameter(ParameterUtil.STUDENT_ID));

        try {
            Student student = studentService.getStudent(studentId);
            request.getSession().setAttribute(ParameterUtil.STUDENT, student);
            request.getSession().setAttribute(ParameterUtil.PATH, PageUtil.ADD_REVIEW_PAGE);

            return PageUtil.ADD_REVIEW_PAGE;
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}
