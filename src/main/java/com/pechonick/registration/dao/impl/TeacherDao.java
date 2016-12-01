package com.pechonick.registration.dao.impl;

import com.pechonick.registration.pool.ConnectionPool;
import com.pechonick.registration.dao.exception.DaoException;
import com.pechonick.registration.entity.teacher.Teacher;
import com.pechonick.registration.entity.teacher.TeacherPosition;
import com.pechonick.registration.pool.exception.ConnectionPoolException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDao extends DefaultDao {

    private static final String GET_TEACHER_BY_COURSE_ID_QUERY = "SELECT u_id, ci_firstname, ci_secondname, ci_thirdname, ci_university, ci_faculty, ci_phone, ci_email " +
            "FROM common_info JOIN courses on common_info.u_id = courses.t_id WHERE courses.crs_id = ?";
    private static final String GET_TEACHER_QUERY = "SELECT * FROM common_info JOIN teachers ON common_info.u_id = teachers.t_id " +
            "WHERE teachers.t_id = ?";
    private static final String DELETE_TEACHER_QUERY = "DELETE FROM users WHERE u_id = ?";
    private static final String UPDATE_TEACHER_QUERY = "UPDATE teachers SET t_position=?,t_experience=? WHERE t_id = ?";
    private static final String CREATE_TEACHER_QUERY = "INSERT INTO teachers VALUES(NULL,?,?)";
    private static final String GET_TEACHERS_QUERY = "SELECT u_id, ci_firstname, ci_secondname, ci_thirdname, ci_university, ci_faculty " +
            "FROM common_info JOIN teachers on common_info.u_id = teachers.t_id";

    @Override
    public Teacher getTeacherByCourseId(int courseId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Teacher teacher = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(GET_TEACHER_BY_COURSE_ID_QUERY);

            preparedStatement.setInt(1, courseId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                teacher = new Teacher();
                teacher.setId(resultSet.getInt(1));
                teacher.setFirstname(resultSet.getString(2));
                teacher.setSecondname(resultSet.getString(3));
                teacher.setThirdname(resultSet.getString(4));
                teacher.setUniversity(resultSet.getString(5));
                teacher.setFaculty(resultSet.getString(6));
                teacher.setEmail(resultSet.getString(7));
                teacher.setPhone(resultSet.getString(8));

            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            try {
                ConnectionPool.getInstance().closeConnection(connection, preparedStatement, resultSet);
            } catch (ConnectionPoolException e) {
                throw new DaoException(e.getMessage(), e);
            }
        }

        return teacher;
    }

    @Override
    public Teacher getTeacher(int teacherId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Teacher teacher = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(GET_TEACHER_QUERY);

            preparedStatement.setInt(1, teacherId);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                teacher = new Teacher();
                teacher.setId(resultSet.getInt(1));
                teacher.setFirstname(resultSet.getString(2));
                teacher.setSecondname(resultSet.getString(3));
                teacher.setThirdname(resultSet.getString(4));
                teacher.setUniversity(resultSet.getString(5));
                teacher.setFaculty(resultSet.getString(6));
                teacher.setDepartment(resultSet.getString(7));
                teacher.setBirthDate(resultSet.getDate(8));
                teacher.setEmail(resultSet.getString(9));
                teacher.setPhone(resultSet.getString(10));
                teacher.setPosition(TeacherPosition.valueOf(resultSet.getString(12)));
                teacher.setExperience(resultSet.getInt(13));
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            try {
                ConnectionPool.getInstance().closeConnection(connection, preparedStatement, resultSet);
            } catch (ConnectionPoolException e) {
                throw new DaoException(e.getMessage(), e);
            }
        }

        return teacher;
    }

    @Override
    public void deleteTeacher(int teacherId) throws DaoException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(DELETE_TEACHER_QUERY);

            preparedStatement.setInt(1, teacherId);

            preparedStatement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            try {
                ConnectionPool.getInstance().closeConnection(connection, preparedStatement);
            } catch (ConnectionPoolException e) {
                throw new DaoException(e.getMessage(), e);
            }
        }
    }

    @Override
    public void updateTeacher(Teacher teacher) throws DaoException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(UPDATE_TEACHER_QUERY);

            preparedStatement.setString(1, teacher.getPosition().name());
            preparedStatement.setInt(2, teacher.getExperience());
            preparedStatement.setInt(3, teacher.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            try {
                ConnectionPool.getInstance().closeConnection(connection, preparedStatement);
            } catch (ConnectionPoolException e) {
                throw new DaoException(e.getMessage(), e);
            }
        }
    }

    @Override
    public void createTeacher(Teacher teacher) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(CREATE_TEACHER_QUERY);

            preparedStatement.setString(1, teacher.getPosition().name());
            preparedStatement.setInt(2, teacher.getExperience());

            preparedStatement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            try {
                ConnectionPool.getInstance().closeConnection(connection, preparedStatement);
            } catch (ConnectionPoolException e) {
                throw new DaoException(e.getMessage(), e);
            }
        }
    }

    @Override
    public List<Teacher> getTeachersList() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Teacher> teachers = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(GET_TEACHERS_QUERY);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(resultSet.getInt(1));
                teacher.setFirstname(resultSet.getString(2));
                teacher.setSecondname(resultSet.getString(3));
                teacher.setThirdname(resultSet.getString(4));
                teacher.setUniversity(resultSet.getString(5));
                teacher.setFaculty(resultSet.getString(6));

                teachers.add(teacher);
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            try {
                ConnectionPool.getInstance().closeConnection(connection, preparedStatement, resultSet);
            } catch (ConnectionPoolException e) {
                throw new DaoException(e.getMessage(), e);
            }
        }

        return teachers;
    }
}
