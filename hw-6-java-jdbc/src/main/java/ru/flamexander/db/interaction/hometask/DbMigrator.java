package ru.flamexander.db.interaction.hometask;

import ru.flamexander.db.interaction.lesson.DataSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Objects;


public class DbMigrator {
    private DataSource dataSource;
    ClassLoader cl = DbMigrator.class.getClassLoader();

    public DbMigrator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String migrate(String fileName) {
        Connection connection;
        String str = null;
        try {
            connection = dataSource.getConnection();
            InputStream is = cl.getResourceAsStream(fileName);
            Statement statement = connection.createStatement();
            str = new String(Objects.requireNonNull(is).readAllBytes());
            try {
                statement.executeUpdate(str);
            } catch (SQLException e) {
                return "";
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return str;
    }

    public void flywayImitator(String fileName) throws SQLException {
        Connection connection;
        String sql = "INSERT INTO public.migrations_history (query_text) VALUES (?)";
        String migratorSql = "create table if not exists public.migrations_history (id bigserial primary key, query_text varchar(300) unique )";
      //  String migratorSql = "drop table public.users;";
        dataSource.getStatement().executeUpdate(migratorSql);

        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            InputStream is = cl.getResourceAsStream(fileName);

            if (is == null) {
                System.out.println("File not found: " + fileName);
                return;
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                StringBuilder contentBuilder = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    contentBuilder.append(line).append("\n");
                }

                String[] strArray = contentBuilder.toString().split(";");

                preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

                for (String str : strArray) {
                    if (!str.trim().isEmpty()) {
                        preparedStatement.setString(1, str.trim() + ";");
                        int affectedRows;
                        try {
                            affectedRows = preparedStatement.executeUpdate();
                        } catch (SQLException s) {
                            return;
                        }
                        if (affectedRows > 0) {
                            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                                if (generatedKeys.next()) {
                                   generatedKeys.getLong(1);
                                } else {
                                    throw new SQLException("Creating migration failed, no ID obtained.");
                                }
                            }
                        }
                    }
                }

            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}



