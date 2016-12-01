package com.pechonick.registration.command;

import com.pechonick.registration.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
	String execute(HttpServletRequest request) throws CommandException;
}
