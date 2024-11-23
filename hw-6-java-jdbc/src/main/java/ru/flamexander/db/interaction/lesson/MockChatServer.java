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
            usersDao.save(new User(null, "A", "A", "A"));
          //  System.out.println(usersDao.getAllUsers());
            AbstractRepository<User> usersRepository = new AbstractRepository<>(dataSource, User.class);
            usersRepository.save(new User(null, "B", "B", "B"));
            usersRepository.findById(1, User.class);

          //  System.out.println(usersDao.getAllUsers());

//            AuthenticationService authenticationService = new AuthenticationService(usersDao);
//            UsersStatisticService usersStatisticService = new UsersStatisticService(usersDao);
//            BonusService bonusService = new BonusService(dataSource);
//            bonusService.init();

//            authenticationService.register("A", "A", "A");
            // Основная работа сервера чата
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
