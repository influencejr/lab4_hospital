package Hospital.Appointments;

import Hospital.Staff.Patient;
import Hospital.Staff.StaffMember;

/*
* Клас для створення призначення "Операція"
*/
public class DoctorOperation extends Appointment {

    private StaffMember staffMember;
    private Patient patient;


    /*
     * Конструктор без параметрів з встановленою назвою призначення
     */
    public DoctorOperation() {
        this.setApp_name("Операція");
    }

    /*
     * Конструктор з усіма параметрами і заздалегідь встановленою назвою
     * @param appointment_description Опис призначення, наприклад: "Прийом у терапевта через кашель".
     * @param appointment_day День призначення
     * @param appointment_month Місяць призначення
     * @param appointment_hour Година призначення
     * @param appointment_minutes Хвилини призначення
     * @param appointment_doctor_name Ім'я лікаря, до якого зробили призначення
     * @param appointment_doctor_email Електронна пошта лікаря, до якого зробили призначення
     */
    public DoctorOperation(String appointment_description,
                           int appointment_day,
                           int appointment_month,
                           int appointment_hour,
                           int appointment_minutes,
                           Patient patient,
                           StaffMember staffMember) {
        this.setApp_name("Операція");
        this.setId(this.getId());
        this.setApp_description(appointment_description);
        this.setApp_day(appointment_day);
        this.setApp_month(appointment_month);
        this.setApp_hour(appointment_hour);
        this.setApp_minute(appointment_minutes);
        this.setPatient(patient);
        this.setStaffMember(staffMember);

        // Збільшуємо id на 1 для всіх видів призначень.
    }

    @Override
    public String toString() {
        return "\nНазва призначення: " + getApp_name()
                + "\nОпис призначення: " + getApp_description()
                + "\nДень, місяць, година:хвилина призначення: " + getApp_day() + ", " + getApp_month() + ", " + getApp_hour() + ":" + getApp_minute()
                + "\nІм'я та пошта лікаря: " + staffMember.getUser().getFirstName() + ", " + staffMember.getUser().getEmail()
                + "\nid призначення: " + getId();
    }
}
