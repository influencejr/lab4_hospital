package Hospital.Appointments;

/*
* Клас для створення призначення "Операція"
*/
public class DoctorOperation extends Appointment {

    /*
     * Конструктор без параметрів з встановленою назвою призначення
     */
    public DoctorOperation() {
        this.setAppointment_name("Операція");
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
                           String patient_name,
                           String patient_email,
                           String appointment_doctor_name,
                           String appointment_doctor_email) {
        this.setAppointment_name("Операція");
        this.setId(this.getId());
        this.setAppointment_description(appointment_description);
        this.setAppointment_day(appointment_day);
        this.setAppointment_month(appointment_month);
        this.setAppointment_hour(appointment_hour);
        this.setAppointment_minute(appointment_minutes);
        this.setPatient_name(patient_name);
        this.setPatient_email(patient_email);
        this.setAppointment_doctor_name(appointment_doctor_name);
        this.setAppointment_doctor_email(appointment_doctor_email);

        // Збільшуємо id на 1 для всіх видів призначень.
        increment_id();
    }

    @Override
    public String toString() {
        return "\nНазва призначення: " + getAppointment_name()
                + "\nОпис призначення: " + getAppointment_description()
                + "\nДень, місяць, година:хвилина призначення: " + getAppointment_day() + ", " + getAppointment_month() + ", " + getAppointment_hour() + ":" + getAppointment_minute()
                + "\nІм'я та пошта лікаря: " + getAppointment_doctor_name() + ", " + getAppointment_doctor_email()
                + "\nid призначення: " + getId();
    }
}
