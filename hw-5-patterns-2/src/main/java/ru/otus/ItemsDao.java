package ru.otus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemsDao {

    public void createItem(Item item) {
        String sql = "INSERT INTO items (title, price) VALUES (?, ?)";
        try {
            Connection connection = PostgresDataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, item.getTitle());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Item getItem(int id) {
        String sql = "SELECT * FROM items WHERE id = ?";
        Item item = null;
        try {
            Connection connection = PostgresDataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                item = new Item(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }


    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items";
        try {
            Connection connection = PostgresDataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getDouble("price"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }


    public void updateItem(Item item) {
        String sql = "UPDATE items SET title = ?, price = ? WHERE id = ?";
        try {
            Connection connection = PostgresDataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, item.getTitle());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.setInt(3, item.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteItem(int id) {
        String sql = "DELETE FROM items WHERE id = ?";
        try (Connection connection = PostgresDataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
