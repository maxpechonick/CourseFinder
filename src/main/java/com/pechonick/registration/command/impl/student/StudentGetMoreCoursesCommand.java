package com.pechonick.registration.command.impl.student;

import com.pechonick.registration.command.Command;
import com.pechonick.registration.command.ParameterUtil;
import com.pechonick.registration.command.exception.CommandException;
import com.pechonick.registration.controller.PageUtil;
import com.pechonick.registration.entity.course.Course;
import com.pechonick.registration.entity.common.User;
import com.pechonick.registration.service.IService;
import com.pechonick.registration.service.ServiceFactory;
import com.pechonick.registration.service.ServiceType;
import com.pechonick.registration.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class StudentGetMoreCoursesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ServiceFactory factory = ServiceFactory.getInstance();
        IService studentService = factory.getService(ServiceType.STUDENT);

        User user = (User) request.getSession().getAttribute(ParameterUtil.USER);
        String type = request.getParameter(ParameterUtil.LIST);

        List<Course> allCourses = (List<Course>) request.getSession().getAttribute(ParameterUtil.ALL_COURSES);
        List<Course> signedCourses = (List<Course>) request.getSession().getAttribute(ParameterUtil.SIGNED_COURSES);

        try {
            switch (type) {
                case ParameterUtil.ALL:
                    int allOffset = (Integer) request.getSession().getAttribute(ParameterUtil.ALL_OFFSET);
                    allCourses.addAll(studentService.getAllCoursesList(allOffset, ParameterUtil.ROW_COUNT_PER_QUERY));
                    request.getSession().setAttribute(ParameterUtil.ALL_OFFSET,
                            allOffset + ParameterUtil.ROW_COUNT_PER_QUERY);
                    break;
                case ParameterUtil.SIGNED:
                    int signedOffset = (Integer) request.getSession().getAttribute(ParameterUtil.SIGNED_OFFSET);
                    signedCourses.addAll(studentService.getSignedCoursesList(user.getId(), signedOffset, ParameterUtil.ROW_COUNT_PER_QUERY));

                    request.getSession().setAttribute(ParameterUtil.SIGNED_OFFSET,
                            signedOffset + ParameterUtil.ROW_COUNT_PER_QUERY);
                    break;
            }

            request.getSession().setAttribute(ParameterUtil.ALL_COURSES, allCourses);
            request.getSession().setAttribute(ParameterUtil.SIGNED_COURSES, signedCourses);
            request.getSession().setAttribute(ParameterUtil.PATH, PageUtil.STUDENT_INDEX_PAGE);

            return PageUtil.STUDENT_INDEX_PAGE;
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}
