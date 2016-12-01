package com.pechonick.registration.dao.impl;

import com.pechonick.registration.pool.ConnectionPool;
import com.pechonick.registration.dao.exception.DaoException;
import com.pechonick.registration.entity.common.Role;
import com.pechonick.registration.entity.common.User;
import com.pechonick.registration.pool.exception.ConnectionPoolException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends DefaultDao {

    private static final String ADD_USER_QUERY = "INSERT INTO elective.users (u_login, u_password, u_role) VALUES(?, ?, ?)";
    private static final String GET_USER_QUERY = "SELECT * FROM elective.users WHERE u_login=?";
    private static final String UPDATE_USER_QUERY = "UPDATE elective.common_info SET ci_firstname=?,ci_secondname=?,ci_thirdname=?,ci_university=?, " +
            "ci_faculty=?,ci_department=?,ci_birth=?,ci_email=?,ci_phone=? WHERE u_id=?";

    @Override
    public void addUser(User user) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(ADD_USER_QUERY);

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole().name());

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
    public User getUser(String login) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(GET_USER_QUERY);

            preparedStatement.setString(1,login);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User(resultSet.getString(3), resultSet.getString(4),
                        Role.valueOf(resultSet.getString(2)));

                user.setId(resultSet.getInt(1));
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

        return user;
    }

    @Override
    public void updateUser(User user) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(UPDATE_USER_QUERY);

            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getSecondname());
            preparedStatement.setString(3, user.getThirdname());
            preparedStatement.setString(4, user.getUniversity());
            preparedStatement.setString(5, user.getFaculty());
            preparedStatement.setString(6, user.getDepartment());
            preparedStatement.setDate(7, user.getBirthDate());
            preparedStatement.setString(8, user.getEmail());
            preparedStatement.setString(9, user.getPhone());
            preparedStatement.setInt(10, user.getId());

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
}
