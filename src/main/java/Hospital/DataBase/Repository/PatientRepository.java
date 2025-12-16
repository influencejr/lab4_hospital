package Hospital.DataBase.Repository;

import Hospital.DataBase.Config.ConnectionToDB;
import Hospital.DataBase.DAO.PatientDAO;
import Hospital.Staff.Patient;
import Hospital.Staff.User;

import java.sql.*;
import java.util.List;

public class PatientRepository extends ConnectionToDB implements PatientDAO {

    Connection connection;

    public PatientRepository() {
        this.connection = getConnection();
    }


    @Override
    public void create(Patient patient) {
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;

        String sql = "INSERT INTO `hospital`.`patient` (`id_user`, `role`, `description`) VALUES (?, ?, ?)";

        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, patient.getUser().getId());
            statement.setString(2, patient.getRole());
            statement.setString(3, patient.getDescription());

            statement.executeUpdate();

            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                patient.setId(generatedKeys.getInt(1));
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
    public void update(Patient obj) {
    }

    @Override
    public void delete(Patient obj) {
    }

    @Override
    public Patient findById(int id) {
        String sql = "SELECT * FROM `hospital`.`patient` WHERE `id_patient` = ?";
        Patient patient = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    patient = new Patient();
                    patient.setId(resultSet.getInt("id_patient"));
                    patient.setRole(resultSet.getString("role"));
                    patient.setDescription(resultSet.getString("description"));

                    User tempUser = new User();
                    tempUser.setId(resultSet.getInt("id_user"));
                    patient.setUser(tempUser);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patient;
    }

    @Override
    public List<Patient> findAll() {
        return List.of();
    }

}
