package ru.flamexander.db.interaction.lesson;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class AbstractRepository<T> {
    private DataSource dataSource;
    private PreparedStatement psInsert;
    private static Field[] fields;
    private String tableName;
    private int id;


    public AbstractRepository(DataSource dataSource, Class<T> cls) {
        this.dataSource = dataSource;
        this.fields = cls.getDeclaredFields();
        this.tableName = ((RepositoryTable) cls.getAnnotation(RepositoryTable.class)).title();
        this.prepareInsert();
        this.findAll();
        this.deleteById(id);
        this.findById(id);
        this.update(id);
        if (!cls.isAnnotationPresent(RepositoryTable.class)) {
            throw new ORMException("Класс не предназначен для создания репозитория, не хватает аннотации @RepositoryTable");
        }


    }

    private static String getId() {
        return getMethodFields().get(0);
    }

    private static String getLogin() {
        return getMethodFields().get(1);
    }

    private static String getPassword() {
        return getMethodFields().get(2);
    }

    private static String getNickname() {
        return getMethodFields().get(3);
    }

    public void printAllData() {
        try {
            Statement statement = dataSource.getStatement();
            ResultSet resultSet = statement.executeQuery(findAll().toString());
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String nickname = resultSet.getString("nickname");
                System.out.println("id: " + id + ", login: " + login + ", password: " + password + ", nickname: " + nickname);
            }
            resultSet.close();
            psInsert.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printOneRecord(long idParam) {

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(findById(idParam).toString());
            preparedStatement.setLong(1, idParam);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("id: " + resultSet.getInt("id") +
                        ", login: " + resultSet.getString("login") +
                        ", password: " + resultSet.getString("password") +
                        ", nickname: " + resultSet.getString("nickname"));
            } else {
                System.out.println("Записей с id " + idParam + " не найдено");
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteOneRecord(long idParam) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteById(idParam).toString());
            preparedStatement.setLong(1, idParam);

            int i = preparedStatement.executeUpdate();
            System.out.println(i == 0 ? "Записей с id " + idParam + " не найдено" : "Запись с id " + idParam + " удалена");

            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateRecord(String login, String password, String nickName, long idParam) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(update(idParam).toString());

            preparedStatement.setObject(1, login);
            preparedStatement.setObject(2, password);
            preparedStatement.setObject(3, nickName);
            preparedStatement.setLong(4, idParam);

            int i = preparedStatement.executeUpdate();
            System.out.println(i == 0 ? "Записей с id " + idParam + " не найдено" : "Запись с id " + idParam + " обновлена");

            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void save(T entity) {
        try {
            List<String> methodFields = getMethodFields();
            for (int i = 1; i < methodFields.size(); i++) {
                String fieldName = methodFields.get(i);
                Object value = null;

                switch (fieldName) {
                    case "id":
                        value = ((User) entity).getId();
                        break;
                    case "login":
                        value = ((User) entity).getLoginParam();
                        break;
                    case "password":
                        value = ((User) entity).getPassword();
                        break;
                    case "nickname":
                        value = ((User) entity).getNickname();
                        break;
                    default:
                        throw new IllegalArgumentException("Геттер для поля " + fieldName + " не найден");
                }

                psInsert.setObject(i, value);
            }

            psInsert.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    private void prepareInsert() {
        StringBuilder query = new StringBuilder("insert into ");
        query.append(tableName).append(" (");
        query.append(getLogin()).append(", ")
                .append(getNickname()).append(", ")
                .append(getPassword()).append(", ");
        query.setLength(query.length() - 2);
        query.append(") values (");
        query.append("?, ".repeat(getMethodFields().size() - 1));
        query.setLength(query.length() - 2);
        query.append(");");
        try {
            psInsert = dataSource.getConnection().prepareStatement(query.toString());
        } catch (SQLException e) {
            throw new ORMException("Не удалось проинициализировать репозиторий, неверно составленный SQL запрос");
        }
    }


    private static List<String> getMethodFields() {
        List<String> fieldsLst = new ArrayList<>();
        for (Field field : fields) {
            if (field.isAnnotationPresent(RepositoryField.class) && !field.isAnnotationPresent(RepositoryIdField.class)) {
                RepositoryField annotation = field.getAnnotation(RepositoryField.class);
                String fieldName = !annotation.value().isEmpty() ? annotation.value() : field.getName();
                fieldsLst.add(fieldName);
            }
            if (field.isAnnotationPresent(RepositoryIdField.class)) {
                fieldsLst.add(field.getName());
            }
        }
        return fieldsLst;


    }


    private StringBuilder findAll() {
        StringBuilder query = new StringBuilder("select * from ").append(tableName).append(";");
        try {
            psInsert = dataSource.getConnection().prepareStatement(query.toString());
        } catch (SQLException e) {
            throw new ORMException("Не удалось проинициализировать репозиторий, неверно составленный SQL запрос");
        }
        return query;
    }

    private StringBuilder findById(long id) {
        StringBuilder query = new StringBuilder("select * from " + tableName);
        query.append(" where ").append(getId()).append(" = ?;");
        try {
            psInsert = dataSource.getConnection().prepareStatement(query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ORMException("Не удалось проинициализировать репозиторий, неверно составленный SQL запрос");
        }
        return query;
    }

    private StringBuilder deleteById(long id) {
        StringBuilder query = new StringBuilder("delete from " + tableName);
        query.append(" where ").append(getId()).append(" = ?;");
        try {
            psInsert = dataSource.getConnection().prepareStatement(query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ORMException("Не удалось проинициализировать репозиторий, неверно составленный SQL запрос");
        }
        return query;
    }

    private StringBuilder update(long id) {
        StringBuilder query = new StringBuilder("UPDATE users SET ")
                .append(getPassword()).append(" = ?, ")
                .append(getLogin()).append(" = ?, ")
                .append(getNickname()).append(" = ? ")
                .append("WHERE ").append(getId()).append(" = ?;");
        return query;
    }


}
