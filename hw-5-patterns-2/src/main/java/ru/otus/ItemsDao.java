package ru.otus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemsDao {

    protected void createItem(Item item) throws SQLException {
        String sql = "INSERT INTO items (title, price) VALUES (?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = PostgresDataSource.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, item.getTitle());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            preparedStatement.close();
        }
    }

    protected Item getItem(int id) throws SQLException {
        String sql = "SELECT * FROM items WHERE id = ?";
        Item item = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Connection connection = PostgresDataSource.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                item = new Item(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            preparedStatement.close();
            resultSet.close();
        }
        return item;
    }


    protected List<Item> getAllItems() throws SQLException {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Connection connection = PostgresDataSource.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getDouble("price"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            preparedStatement.close();
            resultSet.close();
        }
        return items;
    }


    protected void updateItem(Item item) throws SQLException {
        String sql = "UPDATE items SET title = ?, price = ? WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = PostgresDataSource.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, item.getTitle());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.setInt(3, item.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            preparedStatement.close();

        }
    }


    protected void deleteItem(int id) throws SQLException {
        String sql = "DELETE FROM items WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = PostgresDataSource.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            }
         catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            preparedStatement.close();
        }
    }
}
