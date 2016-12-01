package com.pechonick.registration.controller;

import com.pechonick.registration.command.Command;
import com.pechonick.registration.command.exception.CommandException;
import com.pechonick.registration.command.ParameterUtil;
import com.pechonick.registration.controller.helper.CommandHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	
	private static final String COMMAND_NAME = "command";
       
	private final CommandHelper commandHelper = new CommandHelper();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commandName;
		Command command;
		String page;

        try {
            commandName = request.getParameter(COMMAND_NAME);
            command = commandHelper.getCommand(commandName);
            page = command.execute(request);
        } catch (CommandException e) {
            e.printStackTrace();
            page = PageUtil.ERROR_PAGE;
            request.setAttribute(ParameterUtil.ERROR, e.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(request.getContextPath() + page);
        dispatcher.forward(request, response);

    }

}
