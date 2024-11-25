package ru.flamexander.db.interaction.lesson;

import ru.flamexander.db.interaction.hometask.DbMigrator;

import java.sql.SQLException;

public class MockChatServer {
    public static void main(String[] args) {
        DataSource dataSource = null;
        try {
            System.out.println("Сервер чата запущен");
            dataSource = new DataSource("jdbc:h2:file:./db;MODE=PostgreSQL");
            dataSource.connect();

            UsersDao usersDao = new UsersDao(dataSource, new DbMigrator(dataSource));
            usersDao.init();
            // вставка данных
            usersDao.save(new User(null, "A", "A", "A"));
            AbstractRepository<User> usersRepository = new AbstractRepository<User>(dataSource, User.class);
            usersRepository.save(new User(null, "B", "B", "B"));
            System.out.println(usersDao.getAllUsers());
            // обновление данных
            usersRepository.updateRecord("C!", "C!", "C!", 115L);
            // поиск одной записи
            usersRepository.printOneRecord(115L);
            // поиск всех записей
            usersRepository.printAllData();
            // удаление одной записи
            usersRepository.deleteOneRecord(114L);

            System.out.println(usersDao.getAllUsers());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (dataSource != null) {
                dataSource.close();
            }
            System.out.println("Сервер чата завершил свою работу");
        }
    }
}
