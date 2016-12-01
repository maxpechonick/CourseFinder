package com.pechonick.registration.listener;

import com.pechonick.registration.pool.ConnectionPool;
import com.pechonick.registration.pool.exception.ConnectionPoolException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitServletContextListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {
            ConnectionPool.getInstance().initPoolData();
        } catch (ConnectionPoolException e) {
            e.printStackTrace(); // Будет заменено на логер
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().dispose();
        } catch (ConnectionPoolException e) {
            e.printStackTrace(); // Будет заменено на логер
        }
    }
}
