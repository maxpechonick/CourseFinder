package com.pechonick.registration.service;

import com.pechonick.registration.entity.common.User;
import com.pechonick.registration.service.exception.ServiceException;

public interface UserService {

    void registerUser(User user) throws ServiceException;

    User loginUser(String login, String password) throws ServiceException;

    boolean isUserExist(String login) throws ServiceException;


}
