package Hospital.DataBase.Repository;

import Hospital.DataBase.Config.ConnectionToDB;
import Hospital.DataBase.DAO.UserDAO;
import Hospital.Staff.User;

import java.sql.*;
import java.util.List;

public class UserRepository extends ConnectionToDB implements UserDAO {

    Connection connection;

    public UserRepository() {
        this.connection = getConnection();
    }

    @Override
    public void create(User user) {
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;

        String sql = "INSERT INTO `hospital`.`user` " +
                "(`first_name`, `last_name`, `username`, `password`, `email`) " +
                "VALUES (?, ?, ?, ?, ?)";

        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getEmail());

            statement.executeUpdate();

            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (generatedKeys != null) {
                    generatedKeys.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE `hospital`.`user` SET " +
                "`first_name` = ?, `last_name` = ?, `username` = ?, `password` = ?, `email` = ? " +
                "WHERE `id_user` = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getEmail());
            statement.setInt(6, user.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM `hospital`.`user` WHERE `id_user` = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(int id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;

        String sql = "SELECT * FROM `hospital`.`user` WHERE `id_user` = ?";

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id_user"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return user;
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }





}

