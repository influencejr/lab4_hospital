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

        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO `hospital`.`appointment` (`name`, `description`, `day`, `month`, `hour`, `minute`, `result`, `diagnose`, `id_staff`, `id_patient`) " +
                    "VALUES ('"+appointment.getApp_name()+"', '"+appointment.getApp_description()+"', '"+appointment.getApp_day()+"', '"+appointment.getApp_month()+"', '"+appointment.getApp_hour()+"', '"+appointment.getApp_minute()+"', '"+appointment.getAppointment_result()+"', '"+appointment.getAppointment_result()+"', '"+appointment.getStaffMember().getId()+"', '"+appointment.getPatient().getId()+"');");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    @Override
    public void update(Appointment obj) {
    }

    @Override
    public void delete(Appointment obj) {
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
