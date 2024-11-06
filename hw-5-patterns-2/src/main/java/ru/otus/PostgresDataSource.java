package ru.otus;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgresDataSource {
    private static PostgresDataSource instance;
    private Connection connection;

    private PostgresDataSource() {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("credentials.properties"));
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            String databaseUrl = props.getProperty("databaseUrl");
            connection = DriverManager.getConnection(databaseUrl, user, password);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized PostgresDataSource getInstance() {
        if (instance == null) {
            instance = new PostgresDataSource();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
