package com.pechonick.registration.controller.helper;

import com.pechonick.registration.command.Command;
import com.pechonick.registration.command.CommandName;
import com.pechonick.registration.command.impl.admin.*;
import com.pechonick.registration.command.impl.common.*;
import com.pechonick.registration.command.impl.student.*;
import com.pechonick.registration.command.impl.teacher.*;

import java.util.HashMap;
import java.util.Map;

public final class CommandHelper {
    private Map<CommandName, Command> commands = new HashMap<>();
	
	public CommandHelper(){
        commands.put(CommandName.LOGIN, new LoginCommand());
		commands.put(CommandName.REGISTRATION, new RegistrationCommand());
		commands.put(CommandName.CHANGE_LOCALE, new ChangeLocalCommand());
        commands.put(CommandName.GET_LOGIN_PAGE, new GetLoginPageCommand());
        commands.put(CommandName.GET_REGISTRATION_PAGE, new GetRegistratinPageCommand());
		commands.put(CommandName.LOGOUT, new LogoutCommand());

		commands.put(CommandName.GET_ADMIN_INDEX_INFO, new GetAdminIndexInfoCommand());
		commands.put(CommandName.GET_ADMIN_COURSE_INFO, new GetAdminCourseInfoCommand());
		commands.put(CommandName.GET_CREATE_COURSE_FORM, new GetCreateCourseFormCommand());
		commands.put(CommandName.GET_EDIT_COURSE_FORM, new GetEditCourseFormCommand());
		commands.put(CommandName.APPOINT_TEACHER_FOR_THE_COURSE, new AppointTeacherForTheCourseCommand());
		commands.put(CommandName.CREATE_COURSE, new CreateCourseCommand());
		commands.put(CommandName.EDIT_COURSE, new EditCourseCommand());
		commands.put(CommandName.START_COURSE, new StartCourseCommand());
		commands.put(CommandName.CANCEL_COURSE, new CancelCourseCommand());
		commands.put(CommandName.ADMIN_GET_MORE_COURSES, new AdminGetMoreRecordsCommand());

		commands.put(CommandName.GET_USER_INFO, new GetUserInfoCommand());
		commands.put(CommandName.GET_EDIT_USER_INFO_FORM, new GetEditUserFormCommand());

		commands.put(CommandName.GET_STUDENT_INDEX_INFO, new GetStudentIndexInfoCommand());
		commands.put(CommandName.GET_STUDENT_COURSE_INFO, new GetStudentCourseInfoCommand());
		commands.put(CommandName.APPLY_FOR_COURSE, new ApplyForCourseCommand());
		commands.put(CommandName.EDIT_STUDENT_INFO, new EditStudentInfoCommand());
		commands.put(CommandName.STUDENT_GET_MORE_COURSES, new StudentGetMoreCoursesCommand());

		commands.put(CommandName.GET_TEACHER_INDEX_INFO, new GetTeacherIndexInfoCommand());
		commands.put(CommandName.GET_TEACHER_COURSE_INFO, new GetTeacherCourseInfoCommand());
		commands.put(CommandName.EDIT_TEACHER_INFO, new EditTeacherInfoCommand());
		commands.put(CommandName.GET_ADD_REVIEW_FORM, new GetAddReviewFormCommand());
		commands.put(CommandName.GET_STUDENT_INFO, new GetStudentInfoCommand());
		commands.put(CommandName.ADD_STUDENT_REVIEW, new AddStudentReviewCommand());
		commands.put(CommandName.TEACHER_GET_MORE_COURSES, new TeacherGetMoreCoursesCommand());
	}

	public Command getCommand(String commandName){
		Command command;
		CommandName key;
		
		commandName = commandName.replace('-', '_').toUpperCase();
		key = CommandName.valueOf(commandName);
		
		command = commands.get(key);
		
		if(command == null){
			command = new UnknownCommand();
		}
		
		return command;		
	}

}
