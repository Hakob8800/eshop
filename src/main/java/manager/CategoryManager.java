package manager;

import db.DBConnectionProvider;
import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void add(Category category) {
        try (PreparedStatement ps = connection.prepareStatement("insert into category(name) values(?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, category.getName());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                category.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * from category");
            while (resultSet.next()) {
                Category category = new Category(resultSet.getInt("id"), resultSet.getString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public void upDate(int id, String newName) {
        try (PreparedStatement ps = connection.prepareStatement("update category set name = ? where id = ?")) {
            ps.setString(1, newName);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        String query = "delete from category where id = %d";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(String.format(query, id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Category getById(int id) {
        Category category = null;
        String query = "SELECT * from category where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query, id)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                category = new Category(resultSet.getInt("id"), resultSet.getNString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }
}
