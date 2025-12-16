package Hospital.DataBase.Repository;

import Hospital.Appointments.Appointment;
import Hospital.Appointments.AppointmentFactory;
import Hospital.DataBase.Config.ConnectionToDB;
import Hospital.DataBase.DAO.AppointmentDAO;
import Hospital.Staff.Patient;
import Hospital.Staff.StaffMember;
import Hospital.Staff.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository extends ConnectionToDB implements AppointmentDAO {

    Connection connection = null;

//    Statement statement = null;
//    PreparedStatement preparedStatement = null;
//    CallableStatement callableStatement = null;

    public AppointmentRepository() {
        this.connection = getConnection();
    }


    @Override
    public void create(Appointment appointment) {
        String sql = "INSERT INTO `hospital`.`appointment` " +
                "(`name`, `description`, `day`, `month`, `hour`, `minute`, " +
                "`result`, `diagnose`, `id_staff`, `id_patient`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        ResultSet generatedKeys = null;
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, appointment.getApp_name());
            statement.setString(2, appointment.getApp_description());
            statement.setInt(3, appointment.getApp_day());
            statement.setInt(4, appointment.getApp_month());
            statement.setInt(5, appointment.getApp_hour());
            statement.setInt(6, appointment.getApp_minute());

            statement.setString(7, appointment.getAppointment_result());
            statement.setString(8, appointment.getAppointment_diagnose());

            if (appointment.getStaffMember() != null) {
                statement.setInt(9, appointment.getStaffMember().getId());
            } else {
                statement.setNull(9, java.sql.Types.INTEGER);
            }

            if (appointment.getPatient() != null) {
                statement.setInt(10, appointment.getPatient().getId());
            } else {
                statement.setNull(10, java.sql.Types.INTEGER);
            }

            statement.executeUpdate();

            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                appointment.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (generatedKeys != null) {
                try { generatedKeys.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }

    }

    @Override
    public void update(Appointment appointment) {
        String sql = "UPDATE `hospital`.`appointment` SET " +
                "`name` = ?, `description` = ?, `day` = ?, `month` = ?, `hour` = ?, `minute` = ?, " +
                "`result` = ?, `diagnose` = ?, `id_staff` = ?, `id_patient` = ? " +
                "WHERE `id_appointment` = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, appointment.getApp_name());
            statement.setString(2, appointment.getApp_description());
            statement.setInt(3, appointment.getApp_day());
            statement.setInt(4, appointment.getApp_month());
            statement.setInt(5, appointment.getApp_hour());
            statement.setInt(6, appointment.getApp_minute());
            statement.setString(7, appointment.getAppointment_result());
            statement.setString(8, appointment.getAppointment_diagnose());

            statement.setInt(9, appointment.getStaffMember().getId());
            statement.setInt(10, appointment.getPatient().getId());

            statement.setInt(11, appointment.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM `hospital`.`appointment` WHERE `id_appointment` = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Appointment findById(int id) {
        return null;
    }

    @Override
    public List<Appointment> findAll(int patientId) {
        List<Appointment> appointments = new ArrayList<>();

        PatientRepository patientRepo = new PatientRepository();
        Patient patient = patientRepo.findById(patientId);

        if (patient == null) {
            System.out.println("Patient with ID " + patientId + " not found.");
            return appointments;
        }

        UserRepository userRepo = new UserRepository();
        User patientUser = userRepo.findById(patient.getUser().getId());
        patient.setUser(patientUser);

        StaffMemberRepository staffRepo = new StaffMemberRepository();

        String query = "SELECT * FROM `hospital`.`appointment` WHERE `id_patient` = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, patientId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int staffId = resultSet.getInt("id_staff");
                    StaffMember staffMember = staffRepo.findById(staffId);

                    if (staffMember != null) {
                        User staffUser = userRepo.findById(staffMember.getUser().getId());
                        staffMember.setUser(staffUser);
                    }

                    Appointment appointment = new AppointmentFactory().createAppointment(resultSet.getString("name"));
                    appointment.setId(resultSet.getInt("id_appointment"));
                    appointment.setApp_name(resultSet.getString("name"));
                    appointment.setApp_description(resultSet.getString("description"));
                    appointment.setApp_day(resultSet.getInt("day"));
                    appointment.setApp_month(resultSet.getInt("month"));
                    appointment.setApp_hour(resultSet.getInt("hour"));
                    appointment.setApp_minute(resultSet.getInt("minute"));
                    appointment.setAppointment_result(resultSet.getString("result"));
                    appointment.setAppointment_diagnose(resultSet.getString("diagnose"));

                    appointment.setStaffMember(staffMember);
                    appointment.setPatient(patient);

                    appointments.add(appointment);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return appointments;
    }

}
