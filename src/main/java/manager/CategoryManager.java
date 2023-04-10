package manager;

import db.DBConnectionProvider;
import model.Category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void add(Category category) {
        String query = "insert into category(name) values('%s')";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(String.format(query, category.getName()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * from category");
            while (resultSet.next()) {
                Category category = new Category(resultSet.getInt("id"), resultSet.getNString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public void upDate(int id, String newName) {
        String query = "update category set name = '%s' where id = %d";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(String.format(query, newName, id));
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
        try (Statement statement = connection.createStatement()) {
            String query = "SELECT * from category where id = %d";
            ResultSet resultSet = statement.executeQuery(String.format(query, id));
            if (resultSet.next()) {
                category = new Category(resultSet.getInt("id"), resultSet.getNString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }
}
