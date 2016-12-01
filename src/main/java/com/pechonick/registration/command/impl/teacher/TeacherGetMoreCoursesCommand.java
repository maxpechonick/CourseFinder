package com.pechonick.registration.command.impl.teacher;

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

public class TeacherGetMoreCoursesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ServiceFactory factory = ServiceFactory.getInstance();
        IService teacherService = factory.getService(ServiceType.TEACHER);

        User user = (User) request.getSession().getAttribute(ParameterUtil.USER);

        List<Course> signedCourses = (List<Course>) request.getSession().getAttribute(ParameterUtil.SIGNED_COURSES);

        try {

            int signedOffset = (Integer) request.getSession().getAttribute(ParameterUtil.SIGNED_OFFSET);
            List<Course> add = teacherService.getCoursesListByTeacherId(user.getId(), signedOffset,
                    ParameterUtil.ROW_COUNT_PER_QUERY);
            signedCourses.addAll(add);
            request.getSession().setAttribute(ParameterUtil.SIGNED_OFFSET,
                    signedOffset + ParameterUtil.ROW_COUNT_PER_QUERY);

            request.getSession().setAttribute(ParameterUtil.SIGNED_COURSES, signedCourses);
            request.getSession().setAttribute(ParameterUtil.PATH, PageUtil.TEACHER_INDEX_PAGE);

            return PageUtil.TEACHER_INDEX_PAGE;
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}
