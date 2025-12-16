package Hospital.DataBase.Repository;

import Hospital.DataBase.Config.ConnectionToDB;
import Hospital.DataBase.DAO.StaffMemberDAO;
import Hospital.Staff.StaffMember;
import Hospital.Staff.User;

import java.sql.*;
import java.util.List;

public class StaffMemberRepository extends ConnectionToDB implements StaffMemberDAO {

    Connection connection;

    public StaffMemberRepository() {
        this.connection = getConnection();
    }


    @Override
    public void create(StaffMember staffMember) {
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;

        String sql = "INSERT INTO `hospital`.`staff_member` (`id_user`, `role`, `description`) VALUES (?, ?, ?)";

        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, staffMember.getUser().getId());
            statement.setString(2, staffMember.getRole());
            statement.setString(3, staffMember.getDescription());

            statement.executeUpdate();

            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                staffMember.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (generatedKeys != null) generatedKeys.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void update(StaffMember staffMember) {
        String sql = "UPDATE `hospital`.`staff_member` SET " +
                "`id_user` = ?, `role` = ?, `description` = ? " +
                "WHERE `id_staff` = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, staffMember.getUser().getId());
            statement.setString(2, staffMember.getRole());
            statement.setString(3, staffMember.getDescription());
            statement.setInt(4, staffMember.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM `hospital`.`staff_member` WHERE `id_staff` = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public StaffMember findById(int id) {
        String sql = "SELECT * FROM `hospital`.`staff_member` WHERE `id_staff` = ?";
        StaffMember staffMember = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    staffMember = new StaffMember();

                    staffMember.setId(resultSet.getInt("id_staff"));

                    staffMember.setRole(resultSet.getString("role"));
                    staffMember.setDescription(resultSet.getString("description"));

                    User tempUser = new User();
                    tempUser.setId(resultSet.getInt("id_user"));
                    staffMember.setUser(tempUser);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return staffMember;
    }

    @Override
    public List<StaffMember> findAll() {
        return List.of();
    }

}
