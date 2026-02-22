package com.lpu.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL="jdbc:mysql://localhost:3306/MASTERING_JDBC";
    private static final String USERNAME="root";
    private static final String PASSWORD="Svarunkumar@09";


    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL,USERNAME,PASSWORD);
    }

}
