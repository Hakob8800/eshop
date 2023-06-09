 package manager;

import db.DBConnectionProvider;
import model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ProductManager {
    Connection connection = DBConnectionProvider.getInstance().getConnection();
    CategoryManager categoryManager = new CategoryManager();
    private int res;

    public void add(Product product) {
        String query = "insert into product (name,description,price,quantity,category_id) values (?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setInt(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.setInt(5, product.getCategory().getId());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if(generatedKeys.next()){
                product.setId(generatedKeys.getInt(1));
            }
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
        String query = "delete from product where id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query,id)) {
            ps.execute();
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
        String query = "update product set name = ?, description = ?, price = ?, quantity = ?, category_id = ? " +
                "where id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, productNewData[0]);
            ps.setString(2, productNewData[1]);
            ps.setInt(3, Integer.parseInt(productNewData[2]));
            ps.setInt(4, Integer.parseInt(productNewData[3]));
            ps.setInt(5, Integer.parseInt(productNewData[4]));
            ps.setInt(6, id);
            ps.executeUpdate();
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
