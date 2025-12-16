package Hospital.DataBase.DAO;

import Hospital.Appointments.Appointment;

import java.util.List;

public interface AppointmentDAO extends GeneralDAO<Appointment> {
    // Шукаємо всі призначення за ID пацієнта
    List<Appointment> findAll(int id);

}
