package ru.otus;

import java.sql.Connection;
import java.sql.SQLException;

public class ItemsServiceProxy {
    ItemService itemService = new ItemService();
    Connection connection = null;

    protected void addNewItemsTran() {
        try {
            this.connection = PostgresDataSource.getInstance().getConnection();
            this.connection.setAutoCommit(false);
            itemService.addNewItems();
            this.connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (this.connection != null) {
                    this.connection.rollback();
                }
            } catch (SQLException r) {
                r.printStackTrace();
            } finally {
                PostgresDataSource.getInstance().closeConnection();
            }
        }
    }

    protected void increasePriceAndUpdateTran() {
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
