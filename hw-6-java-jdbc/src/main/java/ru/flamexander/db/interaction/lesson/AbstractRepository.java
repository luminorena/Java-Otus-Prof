package ru.flamexander.db.interaction.lesson;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AbstractRepository<T> {
    private DataSource dataSource;
    private PreparedStatement psInsert;
    private List<Field> cachedFields;
    private RepositoryIdField annotation;

    public AbstractRepository(DataSource dataSource, Class<T> cls) {
        this.dataSource = dataSource;
        this.prepareInsert(cls);
        this.findAll(cls);
        this.deleteById(1, cls);
        this.findById(2, cls);
        this.update("users", cls);

    }

    private static String getId() {
        return getMethodFields(User.class).get(0);
    }

    private static String getLogin() {
        return getMethodFields(User.class).get(1);
    }

    private static String getPassword() {
        return getMethodFields(User.class).get(2);
    }

    private static String getNickname() {
        return getMethodFields(User.class).get(3);
    }

    public void save(T entity) {
        try {
            for (int i = 0; i < cachedFields.size(); i++) {
                psInsert.setObject(i + 1, cachedFields.get(i).get(entity));
            }
            psInsert.executeUpdate();
        } catch (Exception e) {
            //throw new ORMException("Данные не сохранены: " + entity);
            e.printStackTrace();
        }
    }

    private void prepareInsert(Class cls) {
        if (!cls.isAnnotationPresent(RepositoryTable.class)) {
            throw new ORMException("Класс не предназначен для создания репозитория, не хватает аннотации @RepositoryTable");
        }
        String tableName = ((RepositoryTable) cls.getAnnotation(RepositoryTable.class)).title();
        StringBuilder query = new StringBuilder("'insert into ");
        query.append(tableName).append(" (");
        query.append(getLogin()).append(", ")
                .append(getNickname()).append(", ")
                .append(getPassword()).append(", ");
        query.setLength(query.length() - 2);
        query.append(") values (");
        query.append("?, ".repeat(getMethodFields(cls).size() - 1));
        query.setLength(query.length() - 2);
        query.append(");'");
        try {
            psInsert = dataSource.getConnection().prepareStatement(query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new ORMException("Не удалось проинициализировать репозиторий для класса " + cls.getName());
        }
    }


    private static List<String> getMethodFields(Class cls) {
        List<String> fieldsLst = new ArrayList<>();
        Field[] fields = cls.getDeclaredFields();

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


    void findAll(Class cls) {
        if (!cls.isAnnotationPresent(RepositoryTable.class)) {
            throw new ORMException("Класс не предназначен для создания репозитория, не хватает аннотации @RepositoryTable");
        }
        String tableName = ((RepositoryTable) cls.getAnnotation(RepositoryTable.class)).title();
        StringBuilder query = new StringBuilder("'select * from ").append(tableName).append(";'");
        try {
            psInsert = dataSource.getConnection().prepareStatement(query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            // throw new ORMException("Не удалось проинициализировать репозиторий для класса " + cls.getName());
        }
    }

    void findById(long id, Class cls) {
        if (!cls.isAnnotationPresent(RepositoryTable.class)) {
            throw new ORMException("Класс не предназначен для создания репозитория, не хватает аннотации @RepositoryTable");
        }
        String tableName = ((RepositoryTable) cls.getAnnotation(RepositoryTable.class)).title();
        StringBuilder query = new StringBuilder("'select * from " + tableName);
        query.append(" where ").append(getId()).append(" = ?;'");
        try {
            psInsert = dataSource.getConnection().prepareStatement(query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            // throw new ORMException("Не удалось проинициализировать репозиторий для класса " + cls.getName());
        }
    }

    void deleteById(long id, Class cls) {
        if (!cls.isAnnotationPresent(RepositoryTable.class)) {
            throw new ORMException("Класс не предназначен для создания репозитория, не хватает аннотации @RepositoryTable");
        }
        String tableName = ((RepositoryTable) cls.getAnnotation(RepositoryTable.class)).title();
        StringBuilder query = new StringBuilder("'delete from " + tableName);
        query.append(" where ").append(getId()).append(" = ?;'");
        try {
            psInsert = dataSource.getConnection().prepareStatement(query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new ORMException("Не удалось проинициализировать репозиторий для класса " + cls.getName());
        }
    }

    void update(String entity, Class cls) {
        if (!cls.isAnnotationPresent(RepositoryTable.class)) {
            throw new ORMException("Класс не предназначен для создания репозитория, не хватает аннотации @RepositoryTable");
        }
        entity = ((RepositoryTable) cls.getAnnotation(RepositoryTable.class)).title();
        StringBuilder query = new StringBuilder("'update ");
        query.append(entity).append(" set ").append(getPassword()).append(" = ?, ")
                .append(getLogin()).append(" = ?, ")
                .append(getNickname()).append(" = ?;'");
        try {
            psInsert = dataSource.getConnection().prepareStatement(query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            //  throw new ORMException("Не удалось проинициализировать репозиторий для класса " + cls.getName());
        }
    }


}
