package com.pechonick.registration.command.impl.common;

import com.pechonick.registration.command.Command;
import com.pechonick.registration.command.exception.CommandException;
import com.pechonick.registration.command.ParameterUtil;
import com.pechonick.registration.controller.PageUtil;
import com.pechonick.registration.entity.common.Role;
import com.pechonick.registration.entity.common.User;
import com.pechonick.registration.service.IService;
import com.pechonick.registration.service.ServiceFactory;
import com.pechonick.registration.service.ServiceType;
import com.pechonick.registration.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) throws CommandException {

		try {
			ServiceFactory factory = ServiceFactory.getInstance();
			IService userService = factory.getService(ServiceType.USER);

            User user = new User(request.getParameter(ParameterUtil.LOGIN),
                    request.getParameter(ParameterUtil.PASSWORD),Role.STUDENT);

			if (!userService.isUserExist(user.getLogin())) {
				userService.registerUser(user);
				request.setAttribute(ParameterUtil.SUCCESS, ParameterUtil.CREATE_MESSAGE);
			} else {
				request.setAttribute(ParameterUtil.ERROR, ParameterUtil.EXIST_MESSAGE);
			}

		} catch (ServiceException e) {
			throw new CommandException(e);
		}

        request.getSession().setAttribute(ParameterUtil.PATH, PageUtil.REGISTER_PAGE);

		return PageUtil.REGISTER_PAGE;
	}

}
