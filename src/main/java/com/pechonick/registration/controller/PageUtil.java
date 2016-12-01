package com.pechonick.registration.controller;

import com.pechonick.registration.entity.common.Role;

public final class PageUtil {

    private PageUtil(){}
	
	public static final String INDEX_PAGE = "/index.jsp";
	public static final String REGISTER_PAGE = "/registration.jsp";
	public static final String ERROR_PAGE = "/error.jsp";

    public static final String ADMIN_INDEX_PAGE = "/WEB-INF/jsp/admin/admin_index.jsp";
    public static final String ADMIN_COURSE_INFO_PAGE = "/WEB-INF/jsp/admin/admin_course_info.jsp";
    public static final String COURSE_FORM_PAGE = "/WEB-INF/jsp/admin/course_form.jsp";

    public static final String USER_INFO_PAGE = "/WEB-INF/jsp/common/user_info.jsp";
    public static final String EDIT_USER_INFO_PAGE = "/WEB-INF/jsp/common/edit_user_info.jsp";

    public static final String STUDENT_COURSE_INFO_PAGE = "/WEB-INF/jsp/student/student_course_info.jsp";
    public static final String STUDENT_INDEX_PAGE = "/WEB-INF/jsp/student/student_index.jsp";

    public static final String TEACHER_COURSE_INFO_PAGE = "/WEB-INF/jsp/teacher/teacher_course_info.jsp";
    public static final String TEACHER_INDEX_PAGE = "/WEB-INF/jsp/teacher/teacher_index.jsp";
    public static final String ADD_REVIEW_PAGE = "/WEB-INF/jsp/teacher/add_review.jsp";
    public static final String STUDENT_INFO_PAGE = "/WEB-INF/jsp/teacher/student_info.jsp";

    public static String formUserPage(Role role) {
        String roleName = role.name().toLowerCase();

        return "WEB-INF/jsp/" + roleName + "/" + roleName + "_index.jsp";
    }
}
