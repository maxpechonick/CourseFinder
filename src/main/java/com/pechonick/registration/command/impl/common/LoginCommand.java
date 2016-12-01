package com.pechonick.registration.command.impl.common;

import com.pechonick.registration.command.Command;
import com.pechonick.registration.command.exception.CommandException;
import com.pechonick.registration.command.ParameterUtil;
import com.pechonick.registration.controller.PageUtil;
import com.pechonick.registration.entity.course.Course;
import com.pechonick.registration.entity.common.User;
import com.pechonick.registration.service.IService;
import com.pechonick.registration.service.ServiceFactory;
import com.pechonick.registration.service.ServiceType;
import com.pechonick.registration.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoginCommand implements Command {

	public String execute(HttpServletRequest request) throws CommandException {
		String page;
		
		try {
            ServiceFactory factory = ServiceFactory.getInstance();
            IService userService = factory.getService(ServiceType.USER);
            User user = userService.loginUser(request.getParameter(ParameterUtil.LOGIN),
                    request.getParameter(ParameterUtil.PASSWORD));

            HttpSession session = request.getSession();
            if (user != null) {
                page = PageUtil.formUserPage(user.getRole());
                session.setAttribute(ParameterUtil.USER, user);
                setUserInfo(user, request);
            } else {
                page = PageUtil.INDEX_PAGE;
                request.setAttribute(ParameterUtil.ERROR, ParameterUtil.LOGIN_MESSAGE);
            }
            session.setAttribute(ParameterUtil.PATH, page);

        } catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		return page;
	}

    private void setUserInfo(User user, HttpServletRequest request) throws ServiceException {

        ServiceFactory factory = ServiceFactory.getInstance();

        switch (user.getRole()) {
            case ADMIN:
                IService courseService = factory.getService(ServiceType.STUDENT);
                List<Course> coursess = courseService.getAllCoursesList(0, ParameterUtil.ROW_COUNT_PER_QUERY);
                request.getSession().setAttribute(ParameterUtil.ALL_OFFSET, ParameterUtil.ROW_COUNT_PER_QUERY);
                request.getSession().setAttribute(ParameterUtil.ALL_COURSES, coursess);
                break;
            case STUDENT:
                IService service = factory.getService(ServiceType.STUDENT);
                List<Course> allCourses = service.getAllCoursesList(0,
                        ParameterUtil.ROW_COUNT_PER_QUERY);
                List<Course> signedCourses = service.getSignedCoursesList(user.getId(),
                        0, ParameterUtil.ROW_COUNT_PER_QUERY);
                request.getSession().setAttribute(ParameterUtil.ALL_COURSES, allCourses);
                request.getSession().setAttribute(ParameterUtil.SIGNED_COURSES, signedCourses);
                request.getSession().setAttribute(ParameterUtil.ALL_OFFSET, ParameterUtil.ROW_COUNT_PER_QUERY);
                request.getSession().setAttribute(ParameterUtil.SIGNED_OFFSET, ParameterUtil.ROW_COUNT_PER_QUERY);
                break;
            case TEACHER:
                IService teacherService = factory.getService(ServiceType.TEACHER);
                List<Course> leadCourses = teacherService.getCoursesListByTeacherId(user.getId(), 0,
                        ParameterUtil.ROW_COUNT_PER_QUERY);
                request.getSession().setAttribute(ParameterUtil.SIGNED_OFFSET, ParameterUtil.ROW_COUNT_PER_QUERY);
                request.getSession().setAttribute(ParameterUtil.SIGNED_COURSES, leadCourses);
                break;
        }
    }

}
