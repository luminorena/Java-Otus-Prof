package ru.flamexander.db.interaction.lesson;

import ru.flamexander.db.interaction.hometask.DbMigrator;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BonusService {
    private DataSource dataSource;
    private DbMigrator dbMigrator;

    public BonusService(DataSource dataSource, DbMigrator dbMigrator) {
        this.dataSource = dataSource;
        this.dbMigrator = dbMigrator;
    }

    public void init() throws SQLException {
//        int query = dataSource.getStatement().executeUpdate(
//                dbMigrator.migrate("dbinit.sql"));
//        ResultSet resultSet = dataSource
//                .getStatement()
//                .executeQuery(String.valueOf(query));
//        resultSet.close();
        dataSource.getStatement().executeUpdate(
                dbMigrator.migrate("dbinit.sql"));

    }

    public void createBonus(String login, int amount) {
        // dataSource.getStatement()...
    }
}
