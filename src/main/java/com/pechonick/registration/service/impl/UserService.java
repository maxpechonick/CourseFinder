package com.pechonick.registration.service.impl;

import com.pechonick.registration.dao.DaoFactory;
import com.pechonick.registration.dao.DaoType;
import com.pechonick.registration.dao.IDao;
import com.pechonick.registration.dao.exception.DaoException;
import com.pechonick.registration.entity.common.User;
import com.pechonick.registration.service.exception.ServiceException;

public class UserService extends DefaultService {

    private static final String REGISTER_ERROR_MESSAGE = "Unable to register user: ";
    private static final String LOGIN_ERROR_MESSAGE = "Unable to login user: ";
    private static final String CHECK_USER_ERROR_MESSAGE = "Unable to check user: ";
    private static final String INVALID_DATA_MESSAGE = "Invalid login or password";

    @Override
    public void registerUser(User user) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        IDao userDao = factory.getDao(DaoType.USER);

        try {
            userDao.addUser(user);
        } catch (DaoException e) {
            throw new ServiceException(REGISTER_ERROR_MESSAGE + user.getLogin(), e);
        }

    }

    @Override
    public boolean isUserExist(String login) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        IDao userDao = factory.getDao(DaoType.USER);

        try {
            User user = userDao.getUser(login);

            if (user == null) {
                return false;
            }

        } catch (DaoException e) {
            throw new ServiceException(CHECK_USER_ERROR_MESSAGE + login);
        }
        return true;
    }

    @Override
    public User loginUser(String login, String password) throws ServiceException {
        if (!Validator.loginValidator(login, password)){
            throw new ServiceException(INVALID_DATA_MESSAGE);
        }

        DaoFactory factory = DaoFactory.getInstance();
        IDao userDao = factory.getDao(DaoType.USER);

        try {
            return userDao.getUser(login);
        } catch (DaoException e) {
            throw new ServiceException(LOGIN_ERROR_MESSAGE + login, e);
        }
    }

    static class Validator{
        public static boolean loginValidator(String login, String password){

            if(login.isEmpty()){
                return false;
            }

            if(password.isEmpty()){
                return false;
            }

            return true;
        }
    }

}

