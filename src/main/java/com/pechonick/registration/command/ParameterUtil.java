package com.pechonick.registration.command;

import com.pechonick.registration.entity.common.User;
import com.pechonick.registration.entity.course.Course;
import com.pechonick.registration.entity.course.Review;
import com.pechonick.registration.entity.student.Student;
import com.pechonick.registration.entity.teacher.Teacher;
import com.pechonick.registration.entity.teacher.TeacherPosition;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.Date;

public final class ParameterUtil {

    private ParameterUtil() {

    }

    public static final int ROW_COUNT_PER_QUERY = 2;

    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    public static final String PATH = "path";
    public static final String LOCAL = "local";
    public static final String USER = "user";
    public static final String ALL_COURSES = "allCourses";
    public static final String SIGNED_COURSES = "signedCourses";
    public static final String COURSE = "course";
    public static final String COURSE_ID = "courseId";
    public static final String TEACHER = "teacher";
    public static final String SELECTED_TEACHER = "selectedTeacher";
    public static final String TEACHER_ID = "teacherId";
    public static final String IS_SIGNED = "isSigned";
    public static final String STUDENT = "student";
    public static final String STUDENT_ID = "studentId";
    public static final String FIRSTNAME = "firstname";
    public static final String SECONDNAME = "secondname";
    public static final String THIRDNAME = "thirdname";
    public static final String UNIVERSITY = "university";
    public static final String FACULTY = "faculty";
    public static final String DEPARTMENT = "department";
    public static final String BIRT_DATE = "birthDate";
    public static final String EMAIL = "email";
    public static final String EXP_DATE = "expDate";
    public static final String PHONE = "phone";
    public static final String USER_ID = "userId";
    public static final String USER_INFO = "userInfo";
    public static final String POSITION = "position";
    public static final String EXPERIENCE = "experience";
    public static final String STUDENT_LIST = "studentList";
    public static final String TEACHERS_LIST = "teachersList";
    public static final String COURSE_NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String START_DATE = "startDate";
    public static final String END_DATE = "endDate";
    public static final String COMMAND_NAME = "commandName";
    private static final String REVIEW_ID = "reviewId";
    private static final String MARK = "mark";
    private static final String TEXT = "text";
    public static final String LIST = "list";
    public static final String ALL = "all";
    public static final String SIGNED = "signed";
    public static final String ALL_OFFSET = "allOffset";
    public static final String SIGNED_OFFSET = "signedOffset";
    public static final String REVIEW = "review";

    public static final String CREATE_MESSAGE = "create-message";
    public static final String EXIST_MESSAGE = "exist-message";
    public static final String LOGIN_MESSAGE = "login-message";
    public static final String LOGOUT_MESSAGE = "logout-message";

    public static void formUser(User user, HttpServletRequest request) throws UnsupportedEncodingException {

        user.setId(Integer.parseInt(request.getParameter(ParameterUtil.USER_ID)));
        user.setFirstname(decodeGetParameter(request.getParameter(ParameterUtil.FIRSTNAME)));
        user.setSecondname(decodeGetParameter(request.getParameter(ParameterUtil.SECONDNAME)));
        user.setThirdname(decodeGetParameter(request.getParameter(ParameterUtil.THIRDNAME)));
        user.setUniversity(decodeGetParameter(request.getParameter(ParameterUtil.UNIVERSITY)));
        user.setFaculty(decodeGetParameter(request.getParameter(ParameterUtil.FACULTY)));
        user.setDepartment(decodeGetParameter(request.getParameter(ParameterUtil.DEPARTMENT)));
        user.setBirthDate(Date.valueOf(request.getParameter(ParameterUtil.BIRT_DATE)));
        user.setEmail(decodeGetParameter(request.getParameter(ParameterUtil.EMAIL)));
        user.setPhone(decodeGetParameter(request.getParameter(ParameterUtil.PHONE)));

        if (user instanceof Student) {
            Student student = (Student) user;
            student.setCourse(Integer.parseInt(request.getParameter(ParameterUtil.COURSE)));
            student.setExpDate(Integer.parseInt(request.getParameter(ParameterUtil.EXP_DATE)));
        }

        if (user instanceof Teacher) {
            Teacher teacher = (Teacher) user;
            teacher.setPosition(TeacherPosition.valueOf(request.getParameter(ParameterUtil.POSITION)));
            teacher.setExperience(Integer.parseInt(request.getParameter(ParameterUtil.EXPERIENCE)));
        }

    }

    public static void formCourse(Course course, HttpServletRequest request) throws UnsupportedEncodingException {
        course.setId(Integer.parseInt(request.getParameter(ParameterUtil.COURSE_ID)));
        course.setName(decodeGetParameter(request.getParameter(ParameterUtil.COURSE_NAME)));
        course.setDescription(decodeGetParameter(request.getParameter(ParameterUtil.DESCRIPTION)));
        course.setTeacherId(Integer.parseInt(request.getParameter(ParameterUtil.TEACHER_ID)));
        course.setStartDate(Date.valueOf(request.getParameter(ParameterUtil.START_DATE)));
        course.setEndDate(Date.valueOf(request.getParameter(ParameterUtil.END_DATE)));
    }

    public static void formReview(Review review, HttpServletRequest request) throws  UnsupportedEncodingException {
        review.setStudentId(Integer.parseInt(request.getParameter(ParameterUtil.STUDENT_ID)));
        review.setCourseId(Integer.parseInt(request.getParameter(ParameterUtil.COURSE_ID)));
        review.setMark(Integer.parseInt(request.getParameter(ParameterUtil.MARK)));
        review.setText(decodeGetParameter(request.getParameter(ParameterUtil.TEXT)));
    }

    private static String decodeGetParameter(String parameter) throws UnsupportedEncodingException {
        return new String(parameter.getBytes("ISO-8859-1"),"UTF8");
    }
}
