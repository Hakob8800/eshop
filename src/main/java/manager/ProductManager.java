package manager;

import db.DBConnectionProvider;
import model.Category;
import model.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    Connection connection = DBConnectionProvider.getInstance().getConnection();
    CategoryManager categoryManager = new CategoryManager();
    private int res;

    public void add(Product product) {
        try (Statement statement = connection.createStatement()) {
            String query = "insert into product (name,description,price,quantity,category_id) values ('%s','%s','%d',%d,%d)";
            statement.executeUpdate(String.format(query, product.getName(), product.getDescription(), product.getPrice(),
                    product.getQuantity(), product.getCategory().getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String query = "select * from product";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                products.add(new Product(resultSet.getInt("id"), resultSet.getNString("name"),
                        resultSet.getNString("description"), resultSet.getInt("price"),
                        resultSet.getInt("quantity"),
                        categoryManager.getById(resultSet.getInt("category_id"))));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public void deleteById(int id) {
        try (Statement statement = connection.createStatement()) {
            String query = "delete from product where id = %d";
            statement.executeUpdate(String.format(query, id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Product getById(int id) {
        Product product = null;
        try (Statement statement = connection.createStatement()) {
            String query = "SELECT * from product where id = %d";
            ResultSet resultSet = statement.executeQuery(String.format(query, id));
            if (resultSet.next()) {
                product = new Product(resultSet.getInt("id"), resultSet.getNString("name"),
                        resultSet.getString("description"), resultSet.getInt("price"),
                        resultSet.getInt("quantity"), categoryManager.getById(resultSet.getInt("category_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public void upDate(int id, String[] productNewData) {
        try (Statement statement = connection.createStatement()) {
            String query = "update product set name = '%s', description = '%s', price = %d, quantity = %d, category_id = %d " +
                    "where id = %d";
            statement.executeUpdate(String.format(query, productNewData[0], productNewData[1], Integer.parseInt(productNewData[2]),
                    Integer.parseInt(productNewData[3]), Integer.parseInt(productNewData[4]), id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int sum() {
        int res = 0;
        try (Statement statement = connection.createStatement()) {
            String query = "SELECT COUNT(id) FROM product";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                res = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public int maxPrice() {
        res = 0;
        try (Statement statement = connection.createStatement()) {
            String query = "SELECT MAX(price) FROM product";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                res = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public int minPrice() {
        res = 0;
        try (Statement statement = connection.createStatement()) {
            String query = "SELECT MIN(price) FROM product";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                res = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public int avgPrice() {
        res = 0;
        try (Statement statement = connection.createStatement()) {
            String query = "SELECT AVG(price) FROM product";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                res = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
