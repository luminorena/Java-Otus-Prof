package ru.otus;

import java.sql.Connection;
import java.sql.SQLException;

public class ItemsServiceProxy {
    ItemService itemService = new ItemService();
    Connection connection = null;

    public void addNewItemsTran() {
        try {
            connection = PostgresDataSource.getInstance().getConnection();
            connection.setAutoCommit(false);
            itemService.addNewItems();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException r) {
                r.printStackTrace();
            } finally {
                PostgresDataSource.getInstance().closeConnection();
            }
        }
    }

    public void increasePriceAndUpdateTran() {
        try {
            connection = PostgresDataSource.getInstance().getConnection();
            connection.setAutoCommit(false);
            itemService.getItemsWithIncreasedPrice();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException r) {
                r.printStackTrace();
            } finally {
                PostgresDataSource.getInstance().closeConnection();
            }
        }
    }
}
