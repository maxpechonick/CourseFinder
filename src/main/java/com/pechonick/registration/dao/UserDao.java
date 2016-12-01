package com.pechonick.registration.dao;

import com.pechonick.registration.dao.exception.DaoException;
import com.pechonick.registration.entity.common.User;

public interface UserDao {

    void addUser(User user) throws DaoException;

    User getUser(String login) throws DaoException;

    void updateUser(User user) throws DaoException;
}
