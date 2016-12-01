package com.pechonick.registration.command.impl.common;

import com.pechonick.registration.command.Command;
import com.pechonick.registration.command.exception.CommandException;
import com.pechonick.registration.command.ParameterUtil;
import com.pechonick.registration.controller.PageUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.removeAttribute(ParameterUtil.USER);
        }

        request.getSession().setAttribute(ParameterUtil.PATH, PageUtil.INDEX_PAGE);
        request.setAttribute(ParameterUtil.SUCCESS, ParameterUtil.LOGOUT_MESSAGE);

        return PageUtil.INDEX_PAGE;
    }
}

