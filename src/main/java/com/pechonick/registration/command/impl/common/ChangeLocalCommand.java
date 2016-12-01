package com.pechonick.registration.command.impl.common;

import com.pechonick.registration.command.Command;
import com.pechonick.registration.command.exception.CommandException;
import com.pechonick.registration.command.ParameterUtil;
import com.pechonick.registration.controller.PageUtil;

import javax.servlet.http.HttpServletRequest;

public class ChangeLocalCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		request.getSession(true).setAttribute(ParameterUtil.LOCAL, request.getParameter(ParameterUtil.LOCAL));
		Object path = request.getSession().getAttribute(ParameterUtil.PATH);
		
        return path == null ? PageUtil.INDEX_PAGE : (String) path;
	}

	
}
