package com.pechonick.registration.command.impl.common;

import com.pechonick.registration.command.Command;
import com.pechonick.registration.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;


public class UnknownCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		return null;
	}

}
