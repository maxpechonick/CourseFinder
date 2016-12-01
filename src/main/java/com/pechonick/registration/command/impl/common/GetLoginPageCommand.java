package com.pechonick.registration.command.impl.common;

import com.pechonick.registration.command.Command;
import com.pechonick.registration.command.exception.CommandException;
import com.pechonick.registration.command.ParameterUtil;
import com.pechonick.registration.controller.PageUtil;

import javax.servlet.http.HttpServletRequest;

public class GetLoginPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        request.getSession().setAttribute(ParameterUtil.PATH, PageUtil.INDEX_PAGE);

        return PageUtil.INDEX_PAGE;
    }
}

