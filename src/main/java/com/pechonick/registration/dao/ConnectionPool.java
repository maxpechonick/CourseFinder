package com.pechonick.registration.dao;


import org.apache.commons.dbcp.cpdsadapter.DriverAdapterCPDS;
import org.apache.commons.dbcp.datasources.SharedPoolDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public final class ConnectionPool {
    //private static final String URL = "jdbc:mysql://mysql33657-authorization.mycloud.by/registration";
    private static final String URL = "jdbc:mysql://localhost:3306/elective";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USERNAME = "root";
    //private static final String PASSWORD = "RYQxut4hE7";
    private static final String PASSWORD = "root";
    private static final int MAX_TOTAL = 10;

    private static DataSource dataSource;

    private ConnectionPool() {

    }

    static {
        DriverAdapterCPDS cpds = new DriverAdapterCPDS();
        try {
            cpds.setDriver(DRIVER);
            cpds.setUrl(URL);
            cpds.setUser(USERNAME);
            cpds.setPassword(PASSWORD);

            SharedPoolDataSource pool = new SharedPoolDataSource();
            pool.setConnectionPoolDataSource(cpds);
            pool.setMaxActive(MAX_TOTAL);
            dataSource = pool;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection takeConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
